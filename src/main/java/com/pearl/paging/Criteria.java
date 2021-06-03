package com.pearl.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class Criteria {

	private int currentPage, amount, pageAmount;
	private String searchKeyword, searchType;
	
	public Criteria() {
		this.currentPage=1;
		this.amount=12;
		this.pageAmount=10;
	}
	
	public String makeQueryString(int pageNo) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("currentPage", pageNo)
				.queryParam("amount", amount)
				.queryParam("pageAmount", pageAmount)
				.queryParam("searchType", searchType)
				.queryParam("searchKeyword", searchKeyword)
				.build()
				.encode();

		return uriComponents.toUriString();
	}
}
