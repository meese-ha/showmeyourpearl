package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.EmotionVO;
import com.pearl.mapper.EmotionMapper;

import lombok.Setter;

@Service
public class EmotionServiceImpl implements EmotionService{
	@Setter(onMethod_ = @Autowired)
	private EmotionMapper mapper;

	@Override
	public int emotionInsert(EmotionVO vo) {
		return mapper.emotionInsert(vo); 
	}

	@Override
	public EmotionVO getEmo(EmotionVO vo) {
		return mapper.getEmo(vo);
	}
	
	@Override
	public int updateEmo(EmotionVO vo) {
		return mapper.updateEmo(vo);
	}
	
	@Override
	public List<EmotionVO> emoCount(int boardNum) {
		return mapper.emoCount(boardNum);
	}
	
	
	
}
