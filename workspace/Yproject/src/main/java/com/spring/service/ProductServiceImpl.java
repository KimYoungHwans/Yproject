package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.mapper.AttachMapper;
import com.spring.mapper.ProductMapper;
import com.spring.model.AttachImageVO;
import com.spring.model.CateFilterDTO;
import com.spring.model.CateVO;
import com.spring.model.ProductCriteria;
import com.spring.model.ProductPageDTO;
import com.spring.model.ProductVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private AttachMapper attachMapper;
	@Autowired
	private ProductService productService;
	
	
	
	//상품 등록
	@Transactional
	@Override
	public void productEnroll(ProductVO product)  {
		
		productMapper.productEnroll(product);
		
		if(product.getImageList() == null || product.getImageList().size() <= 0 ) {
			log.info("Product::::"+product);
			return;
		}
		product.getImageList().forEach(attach ->{
			attach.setProductId(product.getProductId());
			productMapper.imageEnroll(attach);
		});

	}
	
	//상품 검색
	@Override
	public List<ProductVO> productGetList(ProductCriteria pcri) throws Exception{
		
		
		List<ProductVO> list = productMapper.productGetList(pcri);
		
		list.forEach(product ->{
		int productId = product.getProductId();
		List<AttachImageVO> imageList = attachMapper.getAttachList(productId);
		product.setImageList(imageList);
		});
		return list;
	}
	
	//상품 총 수
	@Override
	public int productGetTotal(ProductCriteria pcri) throws Exception{
		log.info("(service)productGetTotal()........" + pcri);
		return productMapper.productGetTotal(pcri);
	}
	
	//상품 관리 페이지
	@RequestMapping(value="productManage", method=RequestMethod.GET)
	public void productManageGET(ProductCriteria pcri, Model model) throws Exception{
		
		List list = productService.productGetList(pcri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list",list);
			
		}else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		model.addAttribute("pageMaker", new ProductPageDTO(pcri, productService.productGetTotal(pcri)));
	}

	
	//상품 상세 페이지 
	@Override
	public ProductVO productGetDetail(int productId) throws Exception {
		log.info("productGetDetail --------------" + productId);
		return productMapper.productGetDetail(productId);
		
	}
	
	
	//정보 수정
	@Transactional
	@Override
	public int productModify(ProductVO product) throws Exception{
		log.info("(service) productModify....." + product);
		
		int result = productMapper.productModify(product);
		
		log.info("result::::::"+ result);
		
		if(result == 1 && product.getImageList() != null && product.getImageList().size() > 0 ) {
			productMapper.deleteImageAll(product.getProductId());
			
			product.getImageList().forEach(attach -> {
				attach.setProductId(product.getProductId());
				productMapper.imageEnroll(attach);
			});
		}
		return result;
	}
	
	
	
	
	
	//상품 정보 삭제
		@Override
		@Transactional
		public int productDelete(int productId) {
			log.info("productDelete >>>>>>>>>>>");
			
			productMapper.deleteImageAll(productId);
			return productMapper.productDelete(productId);
		}
	
	//삭제 상품 이미지정보 가져오기
	@Override
	public List<AttachImageVO> getAttachInfo(int productId){
		log.info("getAttachInfo........");
		return productMapper.getAttachInfo(productId);
		
	}
	
	@GetMapping("search")
	public String searchProductGET(ProductCriteria pcri, Model model) throws Exception {
		log.info("pcri : " + pcri);
		
		List<ProductVO> list = productService.productGetList(pcri);
		log.info("pre list : " +list);
		if(!list.isEmpty()) {
			model.addAttribute("list",list);
			log.info("list : " + list);
		}else {
			model.addAttribute("listcheck","empty");
			
			return "search";
			
		}
		model.addAttribute("ProductPageMaker", new ProductPageDTO(pcri, productService.productGetTotal(pcri)));
		return "search";
	}
	
	//음료 카테고리 리스트
	@Override
	public List<ProductVO> getCateCode1(){
		log.info("getCateCode1().......");
		return productMapper.getCateCode1();
	}
	@Override
	public List<ProductVO> getCateCode2(){
		log.info("getCateCode2().....");
		return productMapper.getCateCode2();
	}
	// 상품 정보
	
	@Override
	public ProductVO getProductInfo(int productId) {
		ProductVO productInfo = productMapper.getProductInfo(productId);
		productInfo.setImageList(productMapper.getAttachInfo(productId));
		
		return productInfo;
	}
	
	//카테고리 리스트
	@Override
	public List<CateVO> cateList() {
		log.info("(service) cateList......");
		
		return productMapper.cateList();
	}
	
	//검색결과 카테고리 필터 정보
	@Override
	public List<CateFilterDTO> getCateInfoList(ProductCriteria pcri){
		List<CateFilterDTO> filterInfoList = new ArrayList<CateFilterDTO>();

		
		String[] cateList = productMapper.getCateList(pcri);
		
		String tempCateCode = pcri.getCateCode();
		
		for(String cateCode : cateList) {
			pcri.setCateCode(cateCode);
			CateFilterDTO filterInfo = productMapper.getCateInfo(pcri);
			filterInfoList.add(filterInfo);
		}
		pcri.setCateCode(tempCateCode);
		
		
		return filterInfoList;
		
		
	}
		@Override
		public ProductVO getProductIdName(int productId) {
			
			return productMapper.getProductIdName(productId);
	}
}
