package com.spring.model;

import java.util.Date;
import java.util.List;

public class OrderDTO {
	
	//주문번호
	private String orderId;
	
	//주문 회원 아이디
	private String memberId;
	
	//주문상태
	private String orderState;
	
	//주문 상품
	private List<OrderItemDTO> orders;
	
	//사용 포인트
	private int usePoint;
	
	//주문 날짜
	private Date orderDate;
	
	//DB테이블 존재 하지 않는 데이터
	
	//판매가 (모든 상품 비용)
	
	private int orderSalePrice;
	
	//적립 포인트
	private int orderSavePoint;
	
	//최종 판매비용
	private int orderFinalSalePrice;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public List<OrderItemDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderItemDTO> orders) {
		this.orders = orders;
	}

	public int getUsePoint() {
		return usePoint;
	}

	public void setUsePoint(int usePoint) {
		this.usePoint = usePoint;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderSalePrice() {
		return orderSalePrice;
	}

	public void setOrderSalePrice(int orderSalePrice) {
		this.orderSalePrice = orderSalePrice;
	}

	public int getOrderSavePoint() {
		return orderSavePoint;
	}

	public void setOrderSavePoint(int orderSavePoint) {
		this.orderSavePoint = orderSavePoint;
	}

	public int getOrderFinalSalePrice() {
		return orderFinalSalePrice;
	}

	public void setOrderFinalSalePrice(int orderFinalSalePrice) {
		this.orderFinalSalePrice = orderFinalSalePrice;
	}

	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", memberId=" + memberId + ", orderState=" + orderState + ", usePoint="
				+ usePoint + ", orderDate=" + orderDate + ", orderSalePrice=" + orderSalePrice + ", orderSavePoint="
				+ orderSavePoint + ", orderFinalSalePrice=" + orderFinalSalePrice + "]";
	}
	
	public void getOrderPriceInfo() {
		//상품 비용 & 적립포인트
		for(OrderItemDTO order : orders) {
			orderSalePrice += order.getTotalPrice();
			orderSavePoint += order.getTotalSavePoint();
			
		}
		
		//최종 비용 (상품비용 - 사용포인트)
		orderFinalSalePrice = orderSalePrice - usePoint;
	}
	

}
