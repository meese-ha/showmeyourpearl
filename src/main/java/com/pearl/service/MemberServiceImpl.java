package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.MemberVO;
import com.pearl.mapper.MemberMapper;
import com.pearl.mapper.PictureMapper;

import lombok.Setter;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private PictureMapper picMapper;
	
	@Override
	public List<MemberVO> list() {
		return mapper.list();
	}

	@Override
	public MemberVO get(MemberVO vo) {
		return mapper.get(vo.getMemEmail());
	}

	@Override
	public int insert(MemberVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int update(MemberVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int updateLevel(MemberVO vo) {
		return mapper.updateLevel(vo);
	}

	@Override
	public int delete(Long memNum) {
		return mapper.delete(memNum);
	}

	@Override
	public MemberVO getProfile(Long memNum) {
		MemberVO vo = mapper.getProfile(memNum);
		vo.setProfile(picMapper.getProfile(vo.getMemNum()));
		return vo;
	}

	@Override
	public boolean checkName(String memName) {
		if(mapper.getByName(memName)==null) {
			return true;
		}
		return false;
	}




}
