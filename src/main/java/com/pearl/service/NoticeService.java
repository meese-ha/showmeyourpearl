package com.pearl.service;

import java.util.List;

import com.pearl.domain.BoardVO;

public interface NoticeService {
	List<BoardVO> list(); 
	BoardVO read(int bNum); 
	int insert(BoardVO vo); 
	int update(BoardVO vo); 
	int delete(int bNum); 
}
