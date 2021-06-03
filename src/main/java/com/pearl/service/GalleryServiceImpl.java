package com.pearl.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.domain.BoardVO;
import com.pearl.domain.GalleryVO;
import com.pearl.domain.MemberVO;
import com.pearl.domain.PictureVO;
import com.pearl.mapper.GalleryMapper;
import com.pearl.mapper.PictureMapper;
import com.pearl.paging.PaginationInfo;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GalleryServiceImpl implements GalleryService{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Setter(onMethod_ = @Autowired)
	private GalleryMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private PictureMapper picMapper;

	@Transactional
	@Override
	public List<GalleryVO> list(GalleryVO vo) {
		List<GalleryVO> list = Collections.emptyList();
		int count = mapper.selectTotalCount(vo);
		log.info("list count:"+count);
		PaginationInfo pagiInfo = new PaginationInfo(vo);
		pagiInfo.setTotalCount(count);
		
		vo.setPagiInfo(pagiInfo);
		
		if(count>0) {
			list=mapper.list(vo);
			for(int i=0; i<list.size();i++) {
				GalleryVO gal = list.get(i);
				PictureVO pic = picMapper.getPic(gal.getBoardNum());
				gal.setPicture(pic);
			}
		}
		return list;
	}

	@Override
	public GalleryVO read(int boardNum) {
		GalleryVO vo = mapper.read(boardNum);
		vo.setPicture(picMapper.getPic(vo.getBoardNum()));
		return vo;
	}
	
	@Override
	public MemberVO readWriter(Long memNum) {
		MemberVO writer = mapper.readWriter(memNum);
		PictureVO pic = picMapper.getProfile(memNum);
		writer.setProfile(pic);
		return writer;
	}

	@Transactional
	@Override
	public void insert(GalleryVO vo) {
		mapper.insert(vo);
		if(vo.getPicture()==null) {
			return;
		}
		PictureVO picture = vo.getPicture();
		log.info(">>>>>>>>PostNum:"+vo.getBoardNum());
		String picPath = picture.getPicPath();
		log.info(">>>>>>>>picPath:"+picPath.split("\\\\")[0]+"%5C"+picPath.split("\\\\")[1]+"%5C"+picPath.split("\\\\")[2]);
		picture.setPicPath(
				picPath.split("\\\\")[0]+"%5C"+picPath.split("\\\\")[1]+"%5C"+picPath.split("\\\\")[2]);
		picture.setPostNum(vo.getBoardNum());
		picture.setPicClass("c");
		picMapper.insertPic(picture);
	}

	@Override
	public int update(GalleryVO vo) {
		int result = mapper.update(vo);
		if(vo.getPicture()==null) {
			return result;
		}
		PictureVO picture = vo.getPicture();
		log.info(">>>>>>>>PostNum:"+vo.getBoardNum());
		String picPath = picture.getPicPath();
		log.info(">>>>>>>>picPath:"+picPath.split("\\\\")[0]+"%5C"+picPath.split("\\\\")[1]+"%5C"+picPath.split("\\\\")[2]);
		picture.setPicPath(
				picPath.split("\\\\")[0]+"%5C"+picPath.split("\\\\")[1]+"%5C"+picPath.split("\\\\")[2]);
		picture.setPostNum(vo.getBoardNum());
		picture.setPicClass("c");
		picMapper.updatePic(picture);
		return result;
	}

	@Override
	public int delete(int boardNum) {
		return mapper.delete(boardNum);
	}
	
	@Override
	public List<Long> nowFunding(){
		return mapper.nowFunding();
	}
	
	@Override
	public Long nowFund(Long memNum) {
		return mapper.nowFund(memNum);
	}

}
