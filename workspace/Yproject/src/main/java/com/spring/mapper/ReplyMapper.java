package com.spring.mapper;

import java.util.List;

import com.spring.model.ProductCriteria;
import com.spring.model.ReplyDTO;
import com.spring.model.UpdateReplyDTO;

public interface ReplyMapper {
	
	//댓글등록
	public int enrollReply(ReplyDTO dto);
	
	//댓글 존재 체크
	public Integer checkReply(ReplyDTO dto);
	
	//댓글 페이징
	public List<ReplyDTO> getReplyList(ProductCriteria pcri);

	//댓글 총 갯수(페이징)
	public int getReplyTotal(int productId);
	
	//댓글 수정
	public int updateReply(ReplyDTO dto);
	
	//댓글 한개 정보
	public ReplyDTO getUpdateReply(int replyId);
	
	//댓글 삭제
	public int deleteReply (int replyId);
	
	//댓글 평점 구하기
	public Double getRatingAverage(int productId);
	
	//폄점 평균 구하기
	public int updateRating(UpdateReplyDTO dto);
	
	
}
