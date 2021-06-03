package com.pearl.domain;

import lombok.Data;

@Data
public class EmotionVO {
	private Long boardNum,memNum;
	private String emoExpress;
	private int emoCount;
}