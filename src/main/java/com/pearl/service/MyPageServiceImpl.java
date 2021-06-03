package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.GalleryVO;
import com.pearl.domain.MemberVO;
import com.pearl.domain.MyPageVO;
import com.pearl.domain.PictureVO;
import com.pearl.domain.SubscribeVO;
import com.pearl.mapper.MyPageMapper;
import com.pearl.mapper.PictureMapper;

import lombok.Setter;

@Service
public class MyPageServiceImpl implements MyPageService{

	@Setter(onMethod_ = @Autowired)
	private MyPageMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private PictureMapper picMapper;
	
	@Override
	public List<MyPageVO> donaList(Long memNum) {
		return mapper.donaList(memNum);
	}

	@Override
	public List<MyPageVO> myfundList(Long memNum) {
		return mapper.myfundList(memNum);
		
	}
	
	@Override
	public int subscribe(SubscribeVO subscribe) {
		return mapper.subscribe(subscribe);
	}
	
	@Override
	public int unsubscribe(SubscribeVO subscribe) {
		return mapper.unsubscribe(subscribe);
	}
	
	@Override
	public List<SubscribeVO> subList(Long memNum) {
		return mapper.subList(memNum);
	}
	
	@Override
	public List<MemberVO> mySubList(Long memNum) {
		return mapper.mySubList(memNum);
	}
	
	@Override
	public List<GalleryVO> myGallery(Long memNum){
		List<GalleryVO> list = mapper.myGallery(memNum);
		for(int i=0; i<list.size();i++) {
			GalleryVO gal = list.get(i);
			PictureVO pic = picMapper.getPic(gal.getBoardNum());
			gal.setPicture(pic);
		}
		return list;
	}

	@Override
	public Long editdelete(Long memNum) {
		return mapper.editdelete(memNum);
	}
}
