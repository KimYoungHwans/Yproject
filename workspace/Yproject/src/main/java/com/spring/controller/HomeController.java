package com.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ctc.wstx.util.StringUtil;
import com.spring.model.ProductCriteria;
import com.spring.model.ProductPageDTO;
import com.spring.model.ProductVO;
import com.spring.service.AttachService;
import com.spring.service.ProductService;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */

@Slf4j
@Controller
public class HomeController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AttachService attachService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "/home";
	}
	//메인 페이지 이동
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void mainPageGET(ProductCriteria pcri, Model model) throws Exception{
	log.info("메인페이지 접속 " );
		List list = productService.productGetList(pcri);
		if(!list.isEmpty()) {
        	model.addAttribute("list",list); // 제품 존재
        	log.info("list >>>>>>>>>>>>>>>> :: " + list);
        }else {
        	model.addAttribute("listCheck", "empty"); //제품 미존재
        }
		//페이지 이동 인터페이스 데이터
        int total = productService.productGetTotal(pcri);
        
        ProductPageDTO pageMaker = new ProductPageDTO(pcri, total);
       
        model.addAttribute("pageMaker", pageMaker);

        
        log.info("pageMaker >>>>>>>>>>>>>" + pageMaker);
		
		model.addAttribute("cate1", productService.getCateCode1());
		model.addAttribute("cate2", productService.getCateCode2());
		model.addAttribute("filter_info", productService.getCateInfoList(pcri));
		
	}
			

	
	@GetMapping("/search")
	public String searchProductGet(ProductCriteria pcri , Model model) throws Exception {
		
		
		List<ProductVO> list = productService.productGetList(pcri);
		if(!list.isEmpty()) {
			model.addAttribute("list",list);
			log.info("list : " + list);
		}else {
			model.addAttribute("listcheck", "empty");
			return "search";
		}
		model.addAttribute("pageMaker", new ProductPageDTO(pcri, productService.productGetTotal(pcri)));
		
		model.addAttribute("filter_info", productService.getCateInfoList(pcri));
		return "search";
	}
	
	@GetMapping("/list")
	public void productGetList(Model model, ProductCriteria pcri) throws Exception {
		log.info("productGetList>>>>>");
		
		model.addAttribute("list", productService.productGetList(pcri));
	}
	
	@GetMapping("/productDetail")
	public void productGetInfoGET(int productId, ProductCriteria pcri, Model model) throws Exception {
		model.addAttribute("pcri", pcri);
		
		model.addAttribute("productInfo", productService.productGetDetail(productId));
		
	}
}
