package com.pearl.domain;

import lombok.Data;

@Data
public class SearchVO {
	private String searchType;
	private Long searchValue;
	
	public SearchVO(String searchType, Long searchValue) {
		this.searchType = searchType;
		this.searchValue = searchValue;
	}
	
	
}
