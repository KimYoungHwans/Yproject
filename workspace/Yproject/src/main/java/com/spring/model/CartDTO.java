package com.spring.model;

import java.util.List;

public class CartDTO {
	
	
	private int cartId;
	
	private String memberId;
	
	private int productId;
	
	private int productCount;
	
	private String productName;
	
	private int productPrice;
	
	private int totalPrice;
	
	private int point;
	
	private int totalPoint;
	
	//상품 이미지
	private List<AttachImageVO> imageList;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}
	
	public void initSaletotal() {
		this.totalPrice = this.productPrice*this.productCount;
		this.point = (int)(Math.floor(this.productPrice*0.05));
		this.totalPoint =this.point * this.productCount;
	}
	
	

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}

	public List<AttachImageVO> getImageList() {
		return imageList;
	}

	public void setImageList(List<AttachImageVO> imageList) {
		this.imageList = imageList;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "CartDTO [cartId=" + cartId + ", memberId=" + memberId + ", productId=" + productId + ", productCount="
				+ productCount + ", productName=" + productName + ", productPrice=" + productPrice + ", totalPrice="
				+ totalPrice + ", point=" + point + ", totalPoint=" + totalPoint + ", imageList=" + imageList + "]";
	}

	

	
	
	
	
}
