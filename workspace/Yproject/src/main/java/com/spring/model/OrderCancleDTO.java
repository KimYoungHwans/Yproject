package com.spring.model;

public class OrderCancleDTO {
	
	private String memberId;
	
	private String orderId;
	
	private String productKeyword;
	
	private int productAmount;
	
	private int productPageNum;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductKeyword() {
		return productKeyword;
	}

	public void setProductKeyword(String productKeyword) {
		this.productKeyword = productKeyword;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	public int getProductPageNum() {
		return productPageNum;
	}

	public void setProductPageNum(int productPageNum) {
		this.productPageNum = productPageNum;
	}

	@Override
	public String toString() {
		return "OrderCancleDTO [memberId=" + memberId + ", orderId=" + orderId + ", productKeyword=" + productKeyword
				+ ", productAmount=" + productAmount + ", productPageNum=" + productPageNum + "]";
	}
	
	
}
