package com.spring.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mapper.AttachMapper;
import com.spring.model.AttachImageVO;
import com.spring.model.OrderCancleDTO;
import com.spring.model.ProductCriteria;
import com.spring.model.ProductPageDTO;
import com.spring.model.ProductVO;
import com.spring.service.OrderService;
import com.spring.service.ProductService;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/admin")
@Log4j
public class AdminController {
	@Autowired
	private AttachMapper attachMapper;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	 /* 관리자 메인 페이지 이동 */
    @RequestMapping(value="main", method = RequestMethod.GET)
    public void adminMainGET() throws Exception{
        
        log.info("관리자 페이지 이동");
        
    }
    
  //상품 등록
    
    @RequestMapping("/productEnroll")
    public String productEnrollPOST(ProductVO product , RedirectAttributes rttr) throws Exception{
    	
    	log.info("admin>>>>productEnroll : " + product);
    	productService.productEnroll(product);
    	
    	rttr.addFlashAttribute("enroll_result", product.getProductName());
    	return "redirect:/admin/productManage";
    }
	
 
    /* 상품 등록 페이지 접속 */
    @RequestMapping(value = "productEnroll", method = RequestMethod.GET)
    public void goodsManageGET(Model model) throws Exception{
        log.info("상품 등록 페이지 접속");
        ObjectMapper objm = new ObjectMapper();
        List list = productService.cateList();
        String cateList = objm.writeValueAsString(list);
        
        model.addAttribute("cateList", cateList);
        
        
    }
    /*
    
    /* 상품 관리 페이지 접속 */
    @RequestMapping(value = "productManage", method = RequestMethod.GET)
    public void productManageGET(ProductCriteria pcri, Model model) throws Exception{
        log.info("상품 등록 페이지 접속   " + pcri);
        
        //상품 목록 데이터 출력
        List list = productService.productGetList(pcri);
        
        if(!list.isEmpty()) {
        	model.addAttribute("list",list); // 제품 존재
        	
        }else {
        	model.addAttribute("listCheck", "empty"); //제품 미존재
        }
        
        //페이지 이동 인터페이스 데이터
        int total = productService.productGetTotal(pcri);
        
        ProductPageDTO pageMaker = new ProductPageDTO(pcri, total);
        
        model.addAttribute("pageMaker", pageMaker);
    
    }
    //상품 상세 페이지
    @GetMapping({"/productDetail","productModify"})
    public void productGetInfoGET(int productId, ProductCriteria pcri, Model model) throws Exception{
    	
    	log.info("productDetail....................."+ productId);
    	
    	//카테고리 리스트 데이터
    	ObjectMapper mapper = new ObjectMapper();
    	model.addAttribute("cateList", mapper.writeValueAsString(productService.cateList()));
    	
    	//상품 관리 페이지
    	model.addAttribute("pcri",pcri);
    	
    	//선택 상품 관리
    	model.addAttribute("productIntro",productService.productGetDetail(productId));
    	
    }
    

    //이미지 정보 반환
    @GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AttachImageVO>>getAttachList (int productId){
    	log.info("getAttachList>>>>>>>>>>>>>>>>>>" + productId);
    	return new ResponseEntity<List<AttachImageVO>>(attachMapper.getAttachList(productId), HttpStatus.OK);
    }
    //이미지 출력 -----------------
    @GetMapping("/display")
    public ResponseEntity<byte[]> getImage(String fileName){
    	File file = new File("c:\\upload\\"+ fileName);
    	
    	ResponseEntity<byte[]> result = null;
    	
    	try {
    		HttpHeaders header = new HttpHeaders();
    		
    		header.add("Content-type", Files.probeContentType(file.toPath()));
    		result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
    	}catch(IOException e)	{
    		e.printStackTrace();
    	}
    	return result;
    }
    
    //상품 정보 수정
    @PostMapping("/productModify")
    public String productModifyPOST(ProductVO product, RedirectAttributes rttr)throws Exception{
    	log.info("productModifyPOS>>>>>>>>>>>>>" + product);
    	int result = productService.productModify(product);
    	rttr.addFlashAttribute("modify_result",result);
    	return "redirect:/admin/productManage";
    }
    
  
    
    
    //상품 삭제 
    @PostMapping("/productDelete")
    public String productDeletePOST(int productId, RedirectAttributes rttr) {
    	log.info("productDelete>>>>>>>>>>>>>>>>>>");
    	//삭제 이미지 가져오기
    	List<AttachImageVO> fileList = productService.getAttachInfo(productId);
    	
    	if(fileList != null) {
    		List<Path> pathList = new ArrayList();
    		
    		fileList.forEach(vo ->{
    			
    			//원본 이미지
    			Path path = Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
    			pathList.add(path);
    			
    			//섬네일 이미지
    			path = Paths.get("C:\\upload",vo.getUploadPath(), "s_" + vo.getUuid()+"_"+ vo.getFileName());
    			pathList.add(path);
    		});
    		
    		pathList.forEach(path -> {
    			path.toFile().delete();
    		});
    	}
    	
    	int result = productService.productDelete(productId);
    	rttr.addFlashAttribute("delete_result", result);
    	return "redirect:/admin/productManage";
    }
    /* 카테고리 등록 페이지 접속 */
    @RequestMapping(value = "categoryEnroll", method = RequestMethod.GET)
    public void authorEnrollGET() throws Exception{
        log.info("카테고리 등록 페이지 접속");
    }
    
    /* 카테고리 관리 페이지 접속 */
    @RequestMapping(value = "categoryManage", method = RequestMethod.GET)
    public void authorManageGET() throws Exception{
        log.info("카테고리 관리 페이지 접속");
    }
    
    
 
    //첨부 파일 업로드
    @PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {
    	log.info("uploadAjaxActionPOST>>>>>>>>>>>");
    	
    	//이미지 파일 체크
    	for(MultipartFile multipartFile: uploadFile) {
    		File checkfile = new File(multipartFile.getOriginalFilename());
    		String type = null;
    		
    		
    		try {
				type = Files.probeContentType(checkfile.toPath());
				log.info("MIME TYPE : " + type);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
    		if(!type.startsWith("image")) {
    			
    			List<AttachImageVO> list = null;
    			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    		}
    	}
    	
    	String uploadFolder = "C:\\upload";
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   

		Date date = new Date();
		
		String str = sdf.format(date);
		
		String datePath = str.replace("-", File.separator);
		//폴더 생성
		File uploadPath = new File(uploadFolder, datePath);
    

		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		//이미지 정보 담는 객체 생성
		List<AttachImageVO> list = new ArrayList();
		
		for(MultipartFile multipartFile : uploadFile) {
			
			//이미지 정보 객체
			AttachImageVO vo = new AttachImageVO();
			
			//파일 이름
			String uploadFileName = multipartFile.getOriginalFilename();
			vo.setFileName(uploadFileName);
			vo.setUploadPath(datePath);
			
			//uuid 적용
			String uuid = UUID.randomUUID().toString();
			vo.setUuid(uuid);
			
			uploadFileName = uuid + "_" + uploadFileName;
			
			//파일 위치, 파일 이름을 합친 File 객체
			File saveFile = new File (uploadPath, uploadFileName);
			//파일 저장
			try {
				multipartFile.transferTo(saveFile);
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);	
				
				BufferedImage bo_image = ImageIO.read(saveFile);
				
					//비율 
					double ratio = 3;
					//넓이 높이
					int width = (int) (bo_image.getWidth() / ratio);
					int height = (int) (bo_image.getHeight() / ratio);					
				
				
				Thumbnails.of(saveFile)
		        .size(width, height)
		        .toFile(thumbnailFile);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			list.add(vo);
		}
		ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list,HttpStatus.OK);
    
		return result;
		
    }
    
  //이미지 삭제
    @PostMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(String fileName){
    	
    	log.info("delete File>>>>>>>>>>" + fileName);
    	File file = null;
    	
    	try {
			/* 썸네일 파일 삭제 */
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			/* 원본 파일 삭제 */
			String originFileName = file.getAbsolutePath().replace("s_", "");
			
			log.info("originFileName : " + originFileName);
			
			file = new File(originFileName);
			
			file.delete();
			
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
			
		}
    	return new ResponseEntity<String>("success",HttpStatus.OK);
    }
    
    //주문 삭제
    @PostMapping("/orderCancle")
    public String orderCanclePOST(OrderCancleDTO dto) {
    	
    	orderService.orderCancle(dto);
    	
    	return "redirect:/orderList?productKeyword=" + dto.getProductKeyword() + "&productAmount=" + dto.getProductAmount() + "&productPageNum=" + dto.getProductPageNum();
    }
    
}