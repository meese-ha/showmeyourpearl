package com.pearl.service;

import java.util.List;

import com.pearl.domain.MemberVO;

public interface MemberService {
	List<MemberVO> list();
	MemberVO get(MemberVO vo);
	int insert(MemberVO vo);
	int update(MemberVO vo);
	int updateLevel(MemberVO vo);
	int delete(Long memNum);
	MemberVO getProfile(Long memNum);
	boolean checkName(String memName);
}
