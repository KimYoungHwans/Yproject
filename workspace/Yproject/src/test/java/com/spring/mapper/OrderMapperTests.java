package com.spring.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.model.CartDTO;
import com.spring.model.MemberVO;
import com.spring.model.OrderDTO;
import com.spring.model.OrderItemDTO;
import com.spring.model.ProductVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class OrderMapperTests {
	
	@Autowired
	private OrderMapper mapper;
	
	@Test
	public void getOrderInfoTest() {
		
		OrderItemDTO orderInfo = mapper.getOrderInfo(2071);
		System.out.println("result : " + orderInfo);
	}
	//star_order 테이블 등록
	@Test
	public void enrollOrderTest() {
		
		OrderDTO ord = new OrderDTO();
		List<OrderItemDTO> orders = new ArrayList();
		
		OrderItemDTO order1 = new OrderItemDTO();
		
		order1.setProductId(2071);
		order1.setProductCount(5);
		order1.setProductPrice(500000);
		order1.getTotalPrice();
		
		ord.setOrders(orders);
		
		ord.setOrderId("2");
		ord.setMemberId("test12");
		ord.setOrderState("주문확인중");
		ord.setUsePoint(100);
		ord.getOrderPriceInfo();
		
		mapper.enrollOrder(ord);
	}
	
	//star_itemorder 테이블 등록
	@Test
	public void enrollOrderItemTest() {
		
		OrderItemDTO oid = new OrderItemDTO();
		
		oid.setOrderId("2");
		
		oid.setOrderItemId(1);
		oid.setProductId(2066);
		oid.setProductCount(1);
		oid.setProductPrice(5500);
		oid.initSaleTotal();
		
		mapper.enrollOrderItem(oid);
		
	}
	
	//회원 돈, 포인트 정보 변경
	@Test
	public void deductMoneyTest() {
		
		MemberVO member = new MemberVO();
		
		member.setMemberId("test12");
		member.setMoney(500000);
		member.setPoint(100000);
		
		mapper.deductMoney(member);
		
	}
	
	

}
