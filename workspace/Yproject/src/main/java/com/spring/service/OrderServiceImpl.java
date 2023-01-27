package com.spring.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mapper.AttachMapper;
import com.spring.mapper.CartMapper;
import com.spring.mapper.MemberMapper;
import com.spring.mapper.OrderMapper;
import com.spring.mapper.ProductMapper;
import com.spring.model.AttachImageVO;
import com.spring.model.CartDTO;
import com.spring.model.MemberVO;
import com.spring.model.OrderCancleDTO;
import com.spring.model.OrderDTO;
import com.spring.model.OrderItemDTO;
import com.spring.model.OrderPageItemDTO;
import com.spring.model.ProductCriteria;



@Service
public class OrderServiceImpl implements OrderService{

	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private MemberMapper  memberMapper;
	
	@Override
	public List<OrderPageItemDTO> getproductInfo(List<OrderPageItemDTO> orders){
		List<OrderPageItemDTO> result = new ArrayList<OrderPageItemDTO>();
		
		for(OrderPageItemDTO ord : orders) {
			
			OrderPageItemDTO productInfo = orderMapper.getProductInfo(ord.getProductId());
		
			productInfo.setProductCount(ord.getProductCount());
			
			productInfo.initSaleTotal();
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(productInfo.getProductId());
			
			productInfo.setImageList(imageList);
			
			result.add(productInfo);
		}
		return result;
	}
	
	@Override
	@Transactional
	public void order(OrderDTO ord) {
		
		//사용할 데이터 가져오기
			//회원 정보
			MemberVO member = memberMapper.getMemberInfo(ord.getMemberId());
			//주문 정보
			List<OrderItemDTO> ords = new ArrayList<>();
			for(OrderItemDTO oit : ord.getOrders()) {
				OrderItemDTO orderItem = orderMapper.getOrderInfo(oit.getProductId());
				
				//수량 셋팅 
				orderItem.setProductCount(oit.getProductCount());	
				//기본 정보 셋팅
				orderItem.initSaleTotal();
				//리스트 객체 추가
				ords.add(orderItem);
			}
			// OrderDTO 셋팅
			ord.setOrders(ords);
			ord.getOrderPriceInfo();
			
			
			//DB 주문, 주문상품 넣기
			//orderId 만들기 및 OrderDTO객체 orderId 에 저장
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddmm");
			String orderId = member.getMemberId() + format.format(date);
			ord.setOrderId(orderId);
			
			//db넣기
			orderMapper.enrollOrder(ord);
			for(OrderItemDTO oit : ord.getOrders()) {
				oit.setOrderId(orderId);
				orderMapper.enrollOrderItem(oit);
			}
			
			//비용 포인트 변동 적용
			
				int calManey = member.getMoney();
				calManey -= ord.getOrderFinalSalePrice();
				member.setMoney(calManey);
				
				//포인트차감, 포인트 증가
				int calPoint = member.getPoint();
				calPoint = calPoint - ord.getUsePoint() + ord.getOrderSavePoint(); //기존 포인트 - 사용 포인트 + 획득 포인트
				member.setPoint(calPoint);
				
				//변동 돈 적용
				orderMapper.deductMoney(member);
				
				//장바구니 제거
					for(OrderItemDTO oit : ord.getOrders()) {
						
						CartDTO dto = new CartDTO();
						dto.setMemberId(ord.getMemberId());
						dto.setProductId(oit.getProductId());
						
						cartMapper.deleteOrderCart(dto);
	}
					
					
	}

	//주문 상품 리스트
	
	@Override
	public List<OrderDTO> getOrderList(ProductCriteria pcri){
		
		return orderMapper.getOrderList(pcri);
	}
	
	//주문 총 갯수
	@Override
	public int getOrderTotal(ProductCriteria pcri) {
		return orderMapper.getOrderTotal(pcri);
	}
	
	//주문 취소
	@Override
	@Transactional	
	public void orderCancle (OrderCancleDTO dto) {
		//주문, 주문상품 객체
		
		//회원
			MemberVO member = memberMapper.getMemberInfo(dto.getMemberId());
			
		//주문 상품
			List <OrderItemDTO> ords = orderMapper.getOrderItemInfo(dto.getOrderId());
			for(OrderItemDTO ord : ords) {
				ord.initSaleTotal();
			}
			
		//주문 
			OrderDTO orw = orderMapper.getOrder(dto.getOrderId());
			orw.setOrders(ords);
			
			orw.getOrderPriceInfo();
//			
		//주문상품 취소 DB
			orderMapper.orderCancle(dto.getOrderId());
			
		//돈, 포인트 변환
			
			//돈
			int calMoney = member.getMoney();
			calMoney += orw.getOrderFinalSalePrice();
			member.setMoney(calMoney);
			
			//포인트
			int calPoint = member.getPoint();
			calPoint = calPoint + orw.getUsePoint() - orw.getOrderSavePoint();
			member.setPoint(calPoint);
			
			
			//DB적용
			orderMapper.deductMoney(member);
	}
}
