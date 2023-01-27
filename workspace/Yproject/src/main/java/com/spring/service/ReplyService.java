package com.spring.service;

import com.spring.model.ProductCriteria;
import com.spring.model.ReplyDTO;
import com.spring.model.ReplyPageDTO;

public interface ReplyService {
	
	//댓글 등록
	
	public int enrollReply(ReplyDTO dto);
	
	//댓글 존재 체크
	public String checkReply(ReplyDTO dto);
	
	
	//댓글 페이징
	public ReplyPageDTO replyList(ProductCriteria pcri);
	
	//댓글 수정
	public int updateReply(ReplyDTO dto);
	
	//댓글 한개 정보
	public ReplyDTO getUpdateReply(int replyId);
	
	//댓글 삭제
	public int deleteReply(ReplyDTO dto);

}
