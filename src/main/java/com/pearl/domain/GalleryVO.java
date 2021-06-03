package com.pearl.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class GalleryVO extends CommonDTO {
	private Long boardNum;
	private Long memNum;
	private String boardTitle;
	private String boardContent;
	private Date boardDate;
	private String boardType, memName, allign;
	
	private int replyCount;
	private boolean funding;
	
	private PictureVO picture;
	
	private int like,sad,angry,happy;
}
