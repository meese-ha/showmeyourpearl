package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.BoardVO;
import com.pearl.domain.GalleryVO;
import com.pearl.domain.MemberVO;
import com.pearl.domain.MyPageVO;
import com.pearl.domain.SubscribeVO;

@Mapper
public interface MyPageMapper {
	List<MyPageVO> myfundList(Long memNum);
	List<MyPageVO> donaList(Long memNum);
	int subscribe(SubscribeVO subscribe);
	int unsubscribe(SubscribeVO subscribe);
	Long editdelete(Long memNum);
	List<SubscribeVO> subList(Long memNum);
	List<MemberVO> mySubList(Long memNum);
	List<GalleryVO> myGallery(Long memNum);
}
