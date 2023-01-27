package com.spring.mapper;

import java.util.List;

import javax.sound.midi.Soundbank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.model.CartDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CartMapperTests {
	
	@Autowired
	private CartMapper mapper;
	
	@Test
	public void addCart() throws Exception {
		String memberId = "test";
		int productId = 2066;
		int count = 2;
		
		CartDTO cart = new CartDTO();
		cart.setMemberId(memberId);
		cart.setProductId(productId);
		cart.setProductCount(count);
		
		int result = 0;
		result = mapper.addCart(cart);
		System.out.println("결과 ::::::: " + result);
	}
	
	//카트 삭제 
	@Test
	public void deleteCartTest() {
		int cartId = 1;
		mapper.deleteCart(cartId);
		
	}
	
	//카트 수량 설정
	@Test
	
	public void modifyCartTest() {
		int cartId = 3;
		int count = 5;
		
		CartDTO cart = new CartDTO();
		cart.setCartId(cartId);
		cart.setProductCount(count);
		
		mapper.modifyCount(cart);
		
	}

	
	//카트 목록
	
	@Test
	public void getCartTest() {
		String memberId = "test";
		
		List<CartDTO> list  = mapper.getCart(memberId);
		for(CartDTO cart : list) {
			System.out.println(cart);
			cart.initSaletotal();
			System.out.println("init cart : " + cart);
		}
	}
	
	//카트 확인
	@Test
	public void checkCartTest() {
		
		String memberId = "test";
		int productId = 2066;
		
		CartDTO cart = new CartDTO();
		cart.setMemberId(memberId);
		cart.setProductId(productId);
		
		CartDTO resultCart = mapper.checkCart(cart);
		System.out.println("결과 ::::: " + resultCart);
	}
	
	@Test
	public void deleteOrderCart() {
		
		String memberId = "test12";
		int productId = 2066;
		
		CartDTO dto = new CartDTO();
		dto.setMemberId(memberId);
		dto.setProductId(productId);
		
		mapper.deleteOrderCart(dto);
		
	}
}
