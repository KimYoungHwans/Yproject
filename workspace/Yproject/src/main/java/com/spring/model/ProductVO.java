package com.spring.model;



import java.util.Date;
import java.util.List;




public class ProductVO {
	
	/*상품 아이디*/
	private int productId;
	//상품 이름
	private String productName;
	
	//카테고리 아이디
	private String categoryId;
	//상품 가격
	private String productPrice;
	//상품 소개
	private String productInfo;
	//등록 날짜
	private Date regDate;
	//수정 날짜
	private Date updateDate;
	//카테고리 이름
	private String cateName;

	//productType
	private String productType;
	//카테고리코드
	private String cateCode;
	
	//경로
	private String uploadPath;
	//uuid
	private String uuid;
	//파일이름
	private String fileName;
	

	//이미지 정보
	private List<AttachImageVO> imageList;
	//상위 카테고리
	private String cateParent;
	
	
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCateParent() {
		return cateParent;
	}
	public void setCateParent(String cateParent) {
		this.cateParent = cateParent;
	}
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
	public String getCategoryId() {
		return categoryId;
	}
	
	
	
	
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
		
		if(categoryId.equals("01")) {
			this.cateName = "음료";
		} else {
			this.cateName = "푸드";
		}
	}
		
	
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getCateCode() {
		return cateCode;
	}
	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}
	
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	

	public List<AttachImageVO> getImageList() {
		return imageList;
	}	
	public void setImageList(List<AttachImageVO> imageList) {
		this.imageList = imageList;
	}
	@Override
	public String toString() {
		return "ProductVO [productId=" + productId + ", productName=" + productName + ", categoryId=" + categoryId
				+ ", productPrice=" + productPrice + ", productInfo=" + productInfo + ", regDate=" + regDate
				+ ", updateDate=" + updateDate + ", cateName=" + cateName + ", productType=" + productType
				+ ", cateCode=" + cateCode + ", uploadPath=" + uploadPath + ", uuid=" + uuid + ", fileName=" + fileName
				+ ", imageList=" + imageList + ", cateParent=" + cateParent + "]";
	}
	
	
	
	
	
	
	
	
}
