package com.pearl.service;

import java.util.List;

import com.pearl.domain.ReplyVO;


public interface ReplyService {
	List<ReplyVO> list(int boardNum);
	ReplyVO get(int memNum);
	int getCount(int boardNum);
	int insert(ReplyVO vo);
	int update(ReplyVO vo);
	int delete(int replyNum);
}
