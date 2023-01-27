package com.spring.service;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.model.AttachImageVO;
import com.spring.model.ProductCriteria;
import com.spring.model.ProductVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ProductServiceTests {

	@Autowired
	private ProductService service;
	/*
	//상품 이미지 등록 테스트
		@Test
		public void productEnrollTests()  {
			ProductVO pro = new ProductVO();
			
			pro.setProductName("service테스트");
			pro.setCategoryId("01");
			pro.setProductPrice("2222");
			pro.setProductInfo("메뉴소개");
			
			List<AttachImageVO> imageList = new ArrayList<AttachImageVO>();
			
			AttachImageVO image1 = new AttachImageVO();
			AttachImageVO image2 = new AttachImageVO();
			
			image1.setFileName("test Image 1");
			image1.setUploadPath("test image 1");
			image1.setUuid("test1111");
			
			image2.setFileName("test image2");
			image2.setUploadPath("test image2");
			image2.setUuid("test2222");
			
			imageList.add(image1);
			imageList.add(image2);
			
			pro.setImageList(imageList);
			
			//service.productEnroll(pro);
			
			System.out.println("등록된 VO : " + pro);
		
		} 
		*/
	@Test
	public void productEnrollTest() throws Exception{
		ProductVO product = new ProductVO();
		
		product.setCategoryId("02");
		product.setProductName("촉촉한 초콜릿 생크림 케익");
		product.setProductPrice("6500");
		product.setProductInfo("초콜릿 크림이 가득 들어가있는 초코케익");
		product.setCateCode("cake");
		service.productEnroll(product);
		
	
	}
	
	@Test
	public void getProductInfoTest() {
		int productId = 2221;
		
		ProductVO productInfo = service.getProductInfo(productId);
		
		System.out.println("===걸과 == ");
		System.out.println("전체 ㅣ " + productInfo);
		System.out.println("productId : " + productInfo.getProductId());
		System.out.println("이미지 정보 : " + productInfo.getImageList().isEmpty());
	}
	
	//제품 상세
//	@Test
//	public void productGetDetailTest() throws Exception{
//		
//		int productId = 20;
//		
//		log.info("product........." + service.productGetDetail(productId));
//	}
	
//	@Test
//	public void productModifyTest() throws Exception {
//		ProductVO product = new ProductVO();
//		
//		product.setProductId(1);
//		
//		product.setProductName("(service)수정");
//		
//		product.setProductPrice("500000");
//		
//		product.setCategoryId("01");
//		
//		product.setProductInfo("service 이용 수정");
//		
//		service.productModify(product);
//		
//		System.out.println("===================수정완료===================");
//	}
	@Test
	public void productGetListTests() throws Exception {
		ProductCriteria pcri = new ProductCriteria();
		
		List list = service.productGetList(pcri);
		list.forEach(product -> log.info("" + product));
	}
	
	
	@Test
	public void getCateInfoListTest1() {
		ProductCriteria pcri = new ProductCriteria();
		
		String keyword = "111111";
		String cateCode = "202000";
		
		
		pcri.setProductKeyword(keyword);
		pcri.setCateCode(cateCode);
		
		System.out.println("List<CateFilterDTO> :::: "+ service.getCateInfoList(pcri));
	}
	
	@Test
	public void getCateInfoListTest2() {
		ProductCriteria pcri = new ProductCriteria();
		String productKeyword = "이미지";
		String cateCode = "103000";
		
		pcri.setProductKeyword(productKeyword);
		pcri.setCateCode(cateCode);
		
		System.out.println("List<CateFilterDTO> : " + service.getCateInfoList(pcri));
	}
	
	@Test
	public void getCateInfoListTest3() {
		ProductCriteria pcri = new ProductCriteria();
		
		String keyword = "수정";
		
		pcri.setProductKeyword(keyword);
		System.out.println("List<CateFilterDTO> :::: "+ service.getCateInfoList(pcri));
	}
}
