package com.spring.mapper;

import java.util.List;

import com.spring.model.Criteria;
import com.spring.model.ProductCriteria;

import com.spring.model.ProductVO;

public interface MenuMapper {

	

	//상품목록
	public List<ProductVO> getMenuList(ProductCriteria pcri);

	//상품 갯수
	public int MenuGetTotal(ProductCriteria pcri);
	
	//상품 카테고리 아이디
	public String[] getCategoryIdList(String keyword);
	
	//상품 조회 페이지
	public ProductVO productGetDetail(int productId);
}
