package com.pearl.domain;

import com.pearl.paging.Criteria;
import com.pearl.paging.PaginationInfo;

import lombok.Data;

@Data
public class CommonDTO extends Criteria {

	private PaginationInfo pagiInfo;
}
