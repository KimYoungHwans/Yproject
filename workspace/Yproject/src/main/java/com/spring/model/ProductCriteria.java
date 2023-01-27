package com.spring.model;

import java.util.Arrays;

public class ProductCriteria {
	
	//현재 페이지 번호
	private int productPageNum;
	
	//페이지 표시 개수
	private int productAmount;
	
	//상품 리스트
	private String[] productArr;
	
	//검색타입
	private String type;
	//상품코드
	private String cateCode;
	
	//검색키워드
	private String productKeyword;
	
	//상품 번호 (댓글 기능)
	private int productId;
	
	
	public ProductCriteria(int productPageNum, int productAmount) {
		
		this.productPageNum = productPageNum;
		this.productAmount = productAmount;
		
	}
	
	public ProductCriteria() {
		this(1,10);
	}
	

	public String[] getTypeArr() {
		return cateCode == null? new String[] {}:cateCode.split("");
	}

	public int getProductPageNum() {
		return productPageNum;
	}

	public void setProductPageNum(int productPageNum) {
		this.productPageNum = productPageNum;
		
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
		
	}

	
	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getProductKeyword() {
		return productKeyword;
	}

	public void setProductKeyword(String productKeyword) {
		this.productKeyword = productKeyword;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String[] getProductArr() {
		return productArr;
	}

	public void setProductArr(String[] productArr) {
		this.productArr = productArr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ProductCriteria [productPageNum=" + productPageNum + ", productAmount=" + productAmount
				+ ", productArr=" + Arrays.toString(productArr) + ", type=" + type + ", cateCode=" + cateCode
				+ ", productKeyword=" + productKeyword + ", productId=" + productId + "]";
	}

	

	
	



}
