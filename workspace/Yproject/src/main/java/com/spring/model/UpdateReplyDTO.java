package com.spring.model;

public class UpdateReplyDTO {
	
	private int productId;
	
	private double ratingAvg;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getRatingAvg() {
		return ratingAvg;
	}

	public void setRatingAvg(double ratingAvg) {
		this.ratingAvg = ratingAvg;
	}

	@Override
	public String toString() {
		return "UpdateReplyDTO [productId=" + productId + ", ratingAvg=" + ratingAvg + ", getProductId()="
				+ getProductId() + ", getRatingAvg()=" + getRatingAvg() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}
