package com.pearl.domain;

import java.util.List;

import lombok.Data;

@Data
public class RewardVO {
	private String rwrdName;
	private Long fundNum;
	private int rwrdNum, rwrdPrice, rwrdCnt;
}
