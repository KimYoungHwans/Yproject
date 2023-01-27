package com.spring.mapper;

import java.util.List;

import org.eclipse.jdt.internal.compiler.env.ISourceType;
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
public class ProductMapperTests {

	@Autowired
	private ProductMapper mapper;
	
	
	//상품 Id 리스트 요청
	@Test
	public void getProductId() {
		String productKeyword ="프라푸치노";
		String[] list = mapper.getProductIdList(productKeyword);
		System.out.println("결과 : : " + list.toString());
		
		for(String id : list) {
			System.out.println("개별 결과 :  " + id);
		}
	}
	/*
	@Test
	public void productEnrollTest() throws Exception{
		
		ProductVO pro = new ProductVO();
		
		pro.setCategoryId("01");
		pro.setProductName("아메리카노");
		pro.setProductPrice("4400");
		pro.setProductInfo("기본적인 아메리카노.");
		pro.setCateCode("coffee");
		
		mapper.productEnroll(pro);
	}
	*/
	
//	@Test
//	public void checkImageListTest() {
//		mapper.checkFileList();
//	}
//	
//	//지정상품 이미지 삭제 테스트
//	@Test
//	public void deleteImageAllTests() {
//		
//		int productId = 342;
//		mapper.deleteImageAll(productId);
//	}
//	
	
	
	
	
//	/*상품 등록 테스트*/
//	
	
	
	@Test
	public void productEnroll() throws Exception{
		ProductVO product = new ProductVO();
		
		
		product.setCategoryId("01");
		product.setProductName("test");
		product.setProductPrice("5400");
		product.setProductInfo("testtest");
		product.setCateCode("202000");
		
	
		
	System.out.println("Before productVO : " + product);
		
		mapper.productEnroll(product);
		
	System.out.println("After ProductVO : " + product);
	}
	
//	
//상품 목록 테스트
	
	@Test
	public void productGetListTest() throws Exception{
		
		ProductCriteria pcri = new ProductCriteria(); // 5페이지 & 10개 행 표시
		
		pcri.setProductKeyword("test");
		
		List list = mapper.productGetList(pcri);
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println("result......" + i + " : " + list.get(i));
		}
		
		//상품 총 갯수
		int result = mapper.productGetTotal(pcri);
		System.out.println("result.......:::::" + result);
	}

	
	//페이징
//	@Test
//	public void productGetListTest() throws Exception{
//		ProductCriteria pcri = new ProductCriteria(2,5);
//		List<ProductVO> list = mapper.productGetList(pcri);
//		
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println("list" + i + ">>>>>>>>>>>>>>>" + list.get(i));
//		}
//		
//	}
	
	/*
	//카테고리 별 검색
	@Test
	public void getCateCode1Test() throws Exception{
		ProductVO pvo = new ProductVO();
		
		System.out.println();
	}
	*/
	/*
	//상품 총 수
	@Test
	public void productGetTotalTest() throws Exception{
		
		ProductCriteria pcri = new ProductCriteria();
		pcri.setProductKeyword("프라푸치노");
		
		int total = mapper.productGetTotal(pcri);
		
		System.out.println("total =============== " + total);
	}
	*/
	/*
	//디테일 조회
	@Test
	public void productGetDetailTest() {
		
		int productId = 555;
		
		ProductVO product = mapper.productGetDetail(productId);
		
		System.out.println("product------------" + product);
	}
	
	@Test
	public void productModifyTest() {
		ProductVO product = new ProductVO();
		
		product.setProductId(1680);
		System.out.println("수정 전 : " + mapper.productGetDetail(product.getProductId()));
	
		product.setProductName("이름수정");
		product.setProductPrice("500");
		product.setCategoryId("200000");
		product.setProductInfo("상품 안내 수정");
		product.setCateCode("202000");
		
		mapper.productModify(product);
		System.out.println("수정 완료 ====== " + mapper.productGetDetail(product.getProductId()));
	}
	*/
//	//삭제 테스트
//	@Test
//	public void productDeleteTest() {
//		int productId = 7;
//		
//		int result = mapper.productDelete(productId);
//		
//		if(result ==1 ) {
//			System.out.println("제품 삭제");
//		}
//	}
//	//이미지 등록test
//	@Test
//	public void imageEnrollTest() {
//		AttachImageVO vo = new AttachImageVO();
//		
//		vo.setProductId(137);
//		vo.setFileName("test등록");
//		vo.setUploadPath("test");
//		vo.setUuid("test2");
//		
//		mapper.imageEnroll(vo);
//	}
	/*
	//지정 상품 이미지 정보 얻기
	@Test
	public void getAttachInfoTest() {
		int productId = 343;
		
		List<AttachImageVO> list = mapper.getAttachInfo(productId);
		
		System.out.println("list : " + list);
	}
	*/
	/*
	//이미지 등록
	@Test
	public void imageEnrollTest() {
		AttachImageVO vo = new AttachImageVO();
		
		 vo.setProductId(1682);
		 vo.setFileName("test");
		 vo.setUploadPath("test");
		 vo.setUuid("test3");
		 
		 mapper.imageEnroll(vo);
	}
	
	*/
	
	/*
	//카테고리 리스트 test
	
	@Test
	public void cateListTest() throws Exception{
		System.out.println("cateList()..........." + mapper.cateList());

}


	 */
	
	@Test
	public void productGetListTest1() {
		ProductCriteria pcri = new ProductCriteria();
		
		
		String type = "C";
		
	String productKeyword = ""	;
	String cateCode = "103000";
	
	pcri.setType(type);
	pcri.setProductKeyword(productKeyword);
	pcri.setProductArr(mapper.getProductIdList(productKeyword));
	pcri.setCateCode(cateCode);
	
	
	List<ProductVO> list = mapper.productGetList(pcri);
	
	System.out.println("pcri ::::: " + pcri);
	System.out.println("list ::::" + list);
	
	
	}
	
	
	
	@Test
	public void getCateInfoTest1() {
		
		ProductCriteria pcri = new ProductCriteria();
		
		String productKeyword = "111111";
		String cateCode ="202000";
		
		pcri.setProductKeyword(productKeyword);
		pcri.setCateCode(cateCode);
		
		
		mapper.getCateInfo(pcri);
		
		System.out.println(pcri);
		
	}
	
	@Test
	public void getProductInfo() {
		int productId = 2066;
		ProductVO productInfo = mapper.getProductInfo(productId);
		System.out.println("productInfo::::::" + productInfo);
	}
}
