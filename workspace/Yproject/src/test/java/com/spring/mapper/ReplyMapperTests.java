package com.spring.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.model.ReplyDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

public class ReplyMapperTests {

	
	@Autowired
	private ReplyMapper mapper;
	
	@Test
	public void replyEnrollTest() {
		
		String id = "test12";
		int productId = 2066;
		double rating = 3.5;
		String content = "댓글 등록 테스트";
		
		ReplyDTO dto = new ReplyDTO();
		dto.setProductId(productId);
		dto.setMemberId(id);
		dto.setContent(content);
		dto.setRating(rating);
		
		mapper.enrollReply(dto);
		
	}
}
