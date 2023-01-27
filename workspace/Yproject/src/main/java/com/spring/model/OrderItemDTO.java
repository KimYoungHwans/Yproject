package com.spring.model;

public class OrderItemDTO {
	
	//주문 번호
	private String orderId;
	
	//상품 번호
	private int productId;
	
	//주문 수량
	private int productCount;
	
	//orderItem 기본키
	private int orderItemId;
	
	//상품 한 개 가격
	private int productPrice;
	
	//상품 한개 구매시 획득 포인트
	private int savePoint;
	
	//DB테이블 존재 하지 않는 데이터
	
	//총 가격
	private int totalPrice;
	
	//총획득 포인트
	private int totalSavePoint;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getSavePoint() {
		return savePoint;
	}

	public void setSavePoint(int savePoint) {
		this.savePoint = savePoint;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalSavePoint() {
		return totalSavePoint;
	}

	public void setTotalSavePoint(int totalSavePoint) {
		this.totalSavePoint = totalSavePoint;
	}

	@Override
	public String toString() {
		return "OrderItemDTO [orderId=" + orderId + ", productId=" + productId + ", productCount=" + productCount
				+ ", orderItemId=" + orderItemId + ", productPrice=" + productPrice + ", savePoint=" + savePoint
				+ ", totalPrice=" + totalPrice + ", totalSavePoint=" + totalSavePoint + "]";
	}
	
	public void initSaleTotal() {
		this.totalPrice = this.productPrice * this.productCount;
		this.savePoint = (int)(Math.floor(this.productPrice*0.05));
		this.totalSavePoint = this.savePoint * this.productCount;
	}

}
