package com.pearl.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MyPageVO {
	private Long fundNum;
	private String fundTitle;
	private String fundIntro;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fundStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fundEndDate;
	private int fundMoney;
	private int payTotal;
	private Long payNum;
	private Long memNum;
	private String memName;
	private int artist;
	private int audience;
	private int nowtotal;
}
