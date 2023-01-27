package com.spring.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.model.ProductCriteria;
import com.spring.model.ProductVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MenuMapperTests {

	@Autowired
	private ProductMapper mapper;
	
	@Test
	public void ProductGetListTest() {
		ProductCriteria pcri = new ProductCriteria();
		
		System.out.println("pcri : " + pcri);
		
		List<ProductVO> list = mapper.getProductList(pcri);
		System.out.println("list : :::: "+ list);
		
		int productTotal = mapper.productGetTotal(pcri);
		System.out.println("total : :::: "+productTotal);
		
		
		
	}
	

	
	
	
	
}