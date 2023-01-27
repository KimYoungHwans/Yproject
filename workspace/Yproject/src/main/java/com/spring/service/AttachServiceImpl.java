package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.AttachMapper;
import com.spring.model.AttachImageVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AttachServiceImpl implements AttachService {

	@Autowired
	private AttachMapper attachMapper;
	
	//이미지 변환
	@Override
	public List<AttachImageVO> getAttachList(int productId){
		log.info("getAttachList>>>>>>>>>>>");
		return attachMapper.getAttachList(productId);
	}
}
