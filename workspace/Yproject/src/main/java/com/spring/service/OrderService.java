package com.spring.service;

import java.util.List;

import com.spring.model.OrderCancleDTO;
import com.spring.model.OrderDTO;
import com.spring.model.OrderPageItemDTO;
import com.spring.model.ProductCriteria;

public interface OrderService {
	
	//주문 정보
	public List<OrderPageItemDTO> getproductInfo(List<OrderPageItemDTO> orders);
	

	//주문
	public void order(OrderDTO orw);
	
	// 주문 상품 리스트
	public List<OrderDTO> getOrderList(ProductCriteria pcri);
	
	//주문 총 갯수
	public int getOrderTotal (ProductCriteria pcri);
	
	
	//주문 취소
	
	public void orderCancle(OrderCancleDTO dto);
}
