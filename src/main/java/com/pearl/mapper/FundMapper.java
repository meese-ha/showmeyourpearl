package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.FundVO;
import com.pearl.domain.GalleryVO;
import com.pearl.domain.PicDTO;
import com.pearl.domain.RewardVO;

@Mapper
public interface FundMapper {
	List<FundVO> getList(FundVO vo);
	int selectTotalCount(FundVO vo);
	FundVO get(Long fundNum);
	Long insert(FundVO vo);
	int update(FundVO vo);
	int delete(Long fundNum);
//	void insertPic(List<PicDTO> list) throws Exception;
//	void insertItem(List<RewardVO> rwList);
//	List<PicDTO> getPicList(Long fundNum);
}
