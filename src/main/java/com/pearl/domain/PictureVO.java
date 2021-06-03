package com.pearl.domain;

import lombok.Data;

@Data
public class PictureVO {

	private int picNum;
	private String picUuid, picPath, picName, picTail, picClass;
	private Long postNum;
	
}
