package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.AttachMapper;
import com.spring.mapper.MenuMapper;
import com.spring.model.AttachImageVO;
import com.spring.model.Criteria;
import com.spring.model.ProductCriteria;
import com.spring.model.ProductVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MenuServiceImpl implements MenuService {

	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	//상품목록
	@Override
	public List<ProductVO> getMenuList(ProductCriteria pcri) {
		log.info("MenuServiceImpl >>>>>> MenuList");
		List<ProductVO> list = menuMapper.getMenuList(pcri);
		log.info("list : " + list );
		
		list.forEach(product -> {
			int productId = product.getProductId();
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(productId);
			
			product.setImageList(imageList);
		});
		
		return list;
	}

	//총 갯수
	@Override
	public int menuGetTotal(ProductCriteria pcri) {
		log.info("menuGetTotal().....");
		
		return menuMapper.MenuGetTotal(pcri);
	}

	

	
	
	
}
