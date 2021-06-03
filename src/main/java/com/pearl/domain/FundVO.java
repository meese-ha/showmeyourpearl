package com.pearl.domain;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("fundVO")
public class FundVO extends CommonDTO{
	private Long fundNum;
	private Long memNum;
	private String memName;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date fundStartDate;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date fundEndDate;
	private int fundMoney, rwrdTotal;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date fundDday;
	private String fundTitle;
	private String fundIntro;
	private PictureVO pic;
	private int total, attend;
	private List<RewardVO> rwvo;
	private String fundNow;
}
