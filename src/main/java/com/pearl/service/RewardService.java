package com.pearl.service;

import java.util.List;

import com.pearl.domain.FundVO;
import com.pearl.domain.RewardVO;

public interface RewardService {
	List<RewardVO> getListReward(Long fundNum);
	int insertReward(RewardVO rwvo, FundVO vo);
	int updateReward(RewardVO rwvo);
	int deleteReward(int rwrdNum);
}
