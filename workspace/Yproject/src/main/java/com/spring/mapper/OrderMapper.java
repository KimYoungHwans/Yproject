package com.spring.mapper;

import java.util.List;

import com.spring.model.MemberVO;
import com.spring.model.OrderDTO;
import com.spring.model.OrderItemDTO;
import com.spring.model.OrderPageItemDTO;
import com.spring.model.ProductCriteria;

public interface OrderMapper {
	
	//주문 상품 정보
	
	public OrderPageItemDTO getProductInfo(int productId);
	
	//주문 상품 주문처리
	public OrderItemDTO getOrderInfo(int productId);
	
	
	//주문 테이블 등록
	public int enrollOrder (OrderDTO ord);
	
	//주문 아이템 테이블 등록
	public int enrollOrderItem(OrderItemDTO orid);
	
	//주문 금액 차감
	public int deductMoney(MemberVO member);
	
	//주문 상품 리스트
	public List<OrderDTO> getOrderList(ProductCriteria pcri);
	
	//주문 총 갯수
	public int getOrderTotal(ProductCriteria pcri);
	
	//주문 취소
	public int orderCancle (String orderId);
	
	//주문 상품 정보
	public List<OrderItemDTO> getOrderItemInfo(String orderId);
	
	//주문 정보
	public OrderDTO getOrder(String orderId);

}
