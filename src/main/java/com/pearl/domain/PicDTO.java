package com.pearl.domain;

import lombok.Data;

@Data
public class PicDTO {
	private int idx;
	private Long fundNum; 
	private String originalFileName;
	private String storedFilePath;
	private Long fileSize;
}
