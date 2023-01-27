package com.spring.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.mapper.AttachMapper;
import com.spring.model.AttachImageVO;
import com.spring.model.ProductCriteria;
import com.spring.model.ProductPageDTO;
import com.spring.model.ProductVO;
import com.spring.model.ReplyDTO;
import com.spring.service.ProductService;
import com.spring.service.ReplyService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/")
public class MenuController {

	
	
	@Autowired
	private ProductService productService;
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		log.info("MenuController >>>>> getImage() >>>>>> " + fileName);
		
		File file = new File("c:\\upload\\" + fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Conetent-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	 //이미지 정보 반환
    @GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AttachImageVO>>getAttachList (int productId){
    	log.info("getAttachList>>>>>>>>>>>>>>>>>>" + productId);
    	return new ResponseEntity<List<AttachImageVO>>(attachMapper.getAttachList(productId), HttpStatus.OK);
    }
    
    
	//상품검색
		@GetMapping("/searchkkkk")
		public String searchProductGET(ProductCriteria pcri, Model model) throws Exception {
			
			log.info("cri : " + pcri);
			
		List<ProductVO> list = productService.productGetList(pcri);
		log.info("pre list : " + list);
		if(!list.isEmpty()) {
			model.addAttribute("list",list);
			log.info("list : " + list);
			
		} else {
			model.addAttribute("listcheck", "empty");
			return "search";
		}
		
		model.addAttribute("pageMaker", new ProductPageDTO(pcri, productService.productGetTotal(pcri)));
		return "search";
		}
		
		//제품 상세
		@GetMapping("/productDetail/{productId}")
		public String productDetailGET(@PathVariable("productId")int productId, Model model) {
			log.info("productDetailGET().......");
			model.addAttribute("productIntro", productService.getProductInfo(productId));
			
			return "/productDetail";
		}
		
		
		//리뷰 쓰기
		@GetMapping("/replyEnroll/{memberId}")
		public String replyEnrollWindowGET(@PathVariable("memberId")String memberId, int productId, Model model) {
					
					ProductVO product = productService.getProductIdName(productId);
					model.addAttribute("productIntro", product);
					model.addAttribute("memberId", memberId);
					
					return "/replyEnroll";
					
				}
				
		//리뷰 수정 팝업창
		@GetMapping("/replyUpdate")
		public String replyUpdateWindowGET(ReplyDTO dto, Model model) {
			ProductVO product = productService.getProductIdName(dto.getProductId());
			model.addAttribute("productIntro", product);
			model.addAttribute("replyInfo", replyService.getUpdateReply(dto.getReplyId()));
			model.addAttribute("memberId", dto.getMemberId());
					
			return "/replyUpdate";
		}
		
}
