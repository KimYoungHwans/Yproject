package com.spring.model;

import java.util.List;

public class ReplyPageDTO {
	
	List<ReplyDTO> list;
	
	ProductPageDTO pageInfo;

	public List<ReplyDTO> getList() {
		return list;
	}

	public void setList(List<ReplyDTO> list) {
		this.list = list;
	}

	public ProductPageDTO getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(ProductPageDTO pageInfo) {
		this.pageInfo = pageInfo;
	}

	@Override
	public String toString() {
		return "ReplyPageDTO [list=" + list + ", pageInfo=" + pageInfo + "]";
	}
	
	
	

}
