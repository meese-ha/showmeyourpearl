package com.pearl.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.PictureVO;

@Mapper
public interface PictureMapper {
	
	public int insertPic(PictureVO picture);
	public PictureVO getPic(Long boardNum);
	public PictureVO getPicF(Long fundNum);
	public PictureVO getProfile(Long memNum);
	public void updatePic(PictureVO picture);
}
