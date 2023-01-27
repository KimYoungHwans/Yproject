package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.model.MemberVO;
import com.spring.model.OrderDTO;
import com.spring.model.OrderPageDTO;
import com.spring.model.ProductCriteria;
import com.spring.model.ProductPageDTO;
import com.spring.service.MemberService;
import com.spring.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberService memberService;
	

	
	@GetMapping("/order/{memberId}")
	public String orderPageGET(@PathVariable("memberId")String memberId, OrderPageDTO opd, Model model) {
		
		model.addAttribute("orderList", orderService.getproductInfo(opd.getOrders()));
		model.addAttribute("memberInfo", memberService.getMemberInfo(memberId));
		
		
		
		return "/order";
	}
	
	@PostMapping("/order")
	public String orderPagePost(OrderDTO od , HttpServletRequest request) {
		System.out.println("od::::" + od);
		
		orderService.order(od);
		
		MemberVO member = new MemberVO();
		member.setMemberId(od.getMemberId());
		
		HttpSession session = request.getSession();
		
		try {
			MemberVO memberLogin = memberService.memberLogin(member);
			memberLogin.setMemberPw("");
			session.setAttribute("member", memberLogin);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/main";
	}
	
	//주문 현황 페이지
	@GetMapping("/orderList")
	public String orderListGET(ProductCriteria pcri, Model model) {
		
		List<OrderDTO> list = orderService.getOrderList(pcri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list",list);
			model.addAttribute("pageMaker",new ProductPageDTO(pcri, orderService.getOrderTotal(pcri)));
		}else {
			model.addAttribute("listCheck", "empty");
		}
		
		return "/orderList";
	}
}
