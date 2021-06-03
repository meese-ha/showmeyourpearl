package com.pearl.service;

import java.util.List;

import com.pearl.domain.BoardVO;
import com.pearl.domain.GalleryVO;
import com.pearl.domain.MemberVO;
import com.pearl.domain.MyPageVO;
import com.pearl.domain.SubscribeVO;


public interface MyPageService {
	List<MyPageVO> donaList(Long memNum);
	List<MyPageVO> myfundList(Long memNum);
	int subscribe(SubscribeVO subscribe);
	int unsubscribe(SubscribeVO subscribe);
	Long editdelete(Long memNum);
	List<SubscribeVO> subList(Long memNum);
	List<MemberVO> mySubList(Long memNum);
	List<GalleryVO> myGallery(Long memNum);
}
