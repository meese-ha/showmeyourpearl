package com.pearl.service;

import java.util.List;

import com.pearl.domain.EmotionVO;


public interface EmotionService {
	int emotionInsert(EmotionVO vo);
	EmotionVO getEmo(EmotionVO vo);
	int updateEmo(EmotionVO vo);
	List<EmotionVO> emoCount(int boardNum);
}
