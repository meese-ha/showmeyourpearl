package com.pearl.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.AdminFundVO;
import com.pearl.domain.FundVO;
import com.pearl.domain.GalleryVO;
import com.pearl.mapper.MainMapper;

import lombok.Setter;


@Service
public class MainServiceImpl implements MainService{
	
	@Setter(onMethod_ = @Autowired)
	private MainMapper mapper;


	@Override
	public List<GalleryVO> list(GalleryVO vo) {
		return mapper.list(vo);
	}

	@Override
	public List<AdminFundVO> fundlist() {
		return mapper.fundlist();
	}


	@Override
	public GalleryVO galleryget(Long boardNum) {
		return mapper.galleryget(boardNum);
	}


	@Override
	public FundVO get(Long fundNum) {
		return mapper.get(fundNum);
	}
	
}
