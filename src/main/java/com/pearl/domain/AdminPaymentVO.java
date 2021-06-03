package com.pearl.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class AdminPaymentVO extends CommonDTO {
	private Long payNum;
	private Long memNum;
	private Long fundNum;
	private Date payTime;
	private int payTotal;
	
}
