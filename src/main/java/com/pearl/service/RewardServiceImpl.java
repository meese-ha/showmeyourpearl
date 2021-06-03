package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.FundVO;
import com.pearl.domain.RewardVO;
import com.pearl.mapper.RewardMapper;

import lombok.Setter;

@Service
public class RewardServiceImpl implements RewardService{
	
	@Setter(onMethod_ = @Autowired)
	private RewardMapper mapper;

	@Override
	public List<RewardVO> getListReward(Long fundNum) {
		// TODO Auto-generated method stub
		return mapper.getListReward(fundNum);
	}

	@Override
	public int insertReward(RewardVO rwvo, FundVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertReward(rwvo);
	}

	@Override
	public int updateReward(RewardVO rwvo) {
		// TODO Auto-generated method stub
		return mapper.updateReward(rwvo);
	}

	@Override
	public int deleteReward(int rwrdNum) {
		// TODO Auto-generated method stub
		return mapper.deleteReward(rwrdNum);
	}

	
	
}
