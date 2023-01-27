package com.spring.service;

import java.util.List;

import com.spring.model.Criteria;
import com.spring.model.ProductCriteria;
import com.spring.model.ProductVO;

public interface MenuService {

	
	//상품 검색 
	public List<ProductVO> getMenuList(ProductCriteria pcri);
	
	//상품 총 갯수
	public int menuGetTotal(ProductCriteria pcri);
	
	
}
