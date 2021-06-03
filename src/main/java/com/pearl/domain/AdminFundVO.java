package com.pearl.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class AdminFundVO  extends CommonDTO{
	private int fundNum;
	private int memNum;
	private String memName, memEmail;
	private String nowtotal;
	private Date fundStartDate;
	private Date fundEndDate;
	private int fundMoney;
	private String fundTitle;
	private String fundIntro;
	private int picNum;
	private String picUuid, picPath, picName, picTail, picClass;
	private Long postNum;
}
