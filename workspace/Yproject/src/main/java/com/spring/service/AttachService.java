package com.spring.service;

import java.util.List;

import com.spring.model.AttachImageVO;

public interface AttachService {
	
	//이미지 데이터 변환
	public List<AttachImageVO> getAttachList(int productId);

}
