package com.spring.model;

import java.util.List;

public class OrderPageItemDTO {
	
	// jsp로 전달받기
	private int productId;
	
	private int productCount;
	
	//DB에서 꺼내오기
	
	private String productName;
	
	private int productPrice;
	
	//만들어서 전달 할 값
	private int salePrice;
	
	private int totalPrice;
	
	private int point;
	
	private int totalPoint;
	
	//상품 이미지
	private List<AttachImageVO> imageList;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
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
	
	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public void initSaleTotal() {
		this.totalPrice = this.productPrice*this.productCount;
		this.point = (int)(Math.floor(this.productPrice*0.05));
		this.totalPoint = this.point * this.productCount;
		
	}

	public List<AttachImageVO> getImageList() {
		return imageList;
	}

	public void setImageList(List<AttachImageVO> imageList) {
		this.imageList = imageList;
	}

	@Override
	public String toString() {
		return "OrderPageItemDTO [productId=" + productId + ", productCount=" + productCount + ", productName="
				+ productName + ", productPrice=" + productPrice + ", salePrice=" + salePrice + ", totalPrice="
				+ totalPrice + ", point=" + point + ", totalPoint=" + totalPoint + ", imageList=" + imageList + "]";
	}

	

	
	
	
	
	

}
