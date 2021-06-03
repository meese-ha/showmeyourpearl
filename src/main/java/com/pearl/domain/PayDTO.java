package com.pearl.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PayDTO {
	private int payTotal, payDona;
	private Long fundNum, memNum, payNum; 
	private String payCardNum, payAddress, payCvc, payCardExpire;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date payTime;
	
	private List<ItemDTO> item;
}
