package com.spring.service;

import java.util.List;

import com.spring.model.AttachImageVO;
import com.spring.model.CateFilterDTO;
import com.spring.model.CateVO;
import com.spring.model.ProductCriteria;
import com.spring.model.ProductVO;

public interface ProductService {

	
	//상품 등록
	public void productEnroll(ProductVO product) throws Exception;
	
	//상품 목록
	public List<ProductVO> productGetList(ProductCriteria pcri) throws Exception;

	//상품 총 수
	public int productGetTotal(ProductCriteria pcri) throws Exception;

	//상품 디테일
	public ProductVO productGetDetail(int productId) throws Exception;
	
	//상품 정보 수정
	public int productModify(ProductVO product) throws Exception;
	
	
	//상품 정보 삭제
	public int productDelete(int productId);
	
	//삭제 상품 이미지 정보 얻기
	public List<AttachImageVO> getAttachInfo(int productId);
	
	//카테고리 리스트
	public List<CateVO> cateList();
	
	//음료 카테고리 리스트
	public List<ProductVO> getCateCode1();
	
	//푸드 카테고리 리스트
	public List<ProductVO> getCateCode2();
	
	// 상품 정보
	public ProductVO getProductInfo(int ProductId);
	
	//검색결과 카테고리 필터 정보
	public List<CateFilterDTO> getCateInfoList(ProductCriteria pcri);
	
	//상품 Id 이름
	public ProductVO getProductIdName(int productId); 
	
}
