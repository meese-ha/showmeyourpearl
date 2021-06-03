package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.ReplyVO;

@Mapper
public interface ReplyMapper {
	List<ReplyVO> list(int boardNum);
	ReplyVO get(int memNum);
	int getCount(int boardNum);
	int insert(ReplyVO vo);
	int update(ReplyVO vo);
	int delete(int replyNum);
}
