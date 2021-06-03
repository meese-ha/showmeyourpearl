package com.pearl.service;

import java.util.List;

import com.pearl.domain.BoardVO;
import com.pearl.domain.GalleryVO;
import com.pearl.domain.MemberVO;
import com.pearl.paging.Criteria;


public interface GalleryService {
	List<GalleryVO> list(GalleryVO vo); 
	GalleryVO read(int boardNum); 
	MemberVO readWriter(Long memNum);
	void insert(GalleryVO vo); 
	int update(GalleryVO vo); 
	int delete(int boardNum);
	List<Long> nowFunding();
	Long nowFund(Long memNum);
}
