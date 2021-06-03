package com.pearl.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class AdminBoardVO {
	private Long boardNum;
	private String boardTitle;
	private String boardcontent;
	private Long memNum;
	private String memName, memEmail;
	private Date boardDate;
	private int picNum;
	private String picUuid, picPath, picName, picTail, picClass;
	private Long postNum;
}
