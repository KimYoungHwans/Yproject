package com.spring.mapper;

import java.util.List;

import com.spring.model.AttachImageVO;
import com.spring.model.CateFilterDTO;
import com.spring.model.CateVO;
import com.spring.model.ProductCriteria;
import com.spring.model.ProductVO;

public interface ProductMapper {

	//상품 등록
		public void productEnroll(ProductVO product);
		
	//상품 목록
		public List<ProductVO> productGetList(ProductCriteria pcri);
	
	//상품 총 수 
		public int productGetTotal(ProductCriteria pcri);
		
	//상품 상세
		public ProductVO productGetDetail(int productId);

	// 상품 수정
	public int productModify(ProductVO product);
	
	//상품 삭제
	public int productDelete(int productId);
	
	//이미지 등록
	public void imageEnroll(AttachImageVO vo);
	
	//지정 상품 이미지 삭제
	public void deleteImageAll (int productId);
	
	//어제 날짜 이미지 리스트
		public List<AttachImageVO> checkFileList();
		
	// 지정 상품 이미지 정보 얻기
		public List<AttachImageVO> getAttachInfo(int productId);
		
	// 상품
		public String[] getProductIdList(String productKeyword);
		
	// 상품 검색
		public List<ProductVO> getProductList(ProductCriteria pcri);
	
	//음료 카테고리 리스트
			public List<ProductVO> getCateCode1();
	//푸드 카테고리 리스트 
			public List<ProductVO> getCateCode2();
		
	//검색 대상 카테고리 리스트
		public String[] getCateList(ProductCriteria pcri);
		
	// 카테고리 정보(검색 대상 갯수)
		public CateFilterDTO getCateInfo(ProductCriteria pcri);
	
	// 카테고리 리스트
		public List<CateVO> cateList();
		
	//상품 정보
		public ProductVO getProductInfo(int productId);
		
	//상품 id 이름
		public ProductVO getProductIdName (int productId);

	
}
