package com.pearl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pearl.domain.FundVO;
import com.pearl.domain.MemberVO;
import com.pearl.domain.RewardVO;

public interface FundService {
	List<FundVO> getList(FundVO vo);
	FundVO get(Long fundNum);
	MemberVO artist(Long memNum);
	FundVO getPay(FundVO vo);
	void insert(FundVO vo);
	int update(FundVO vo);
	int delete(Long fundNum);
}
