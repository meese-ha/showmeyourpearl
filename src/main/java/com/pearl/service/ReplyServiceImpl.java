package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.GalleryVO;
import com.pearl.domain.PictureVO;
import com.pearl.domain.ReplyVO;
import com.pearl.mapper.PictureMapper;
import com.pearl.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	ReplyMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	PictureMapper picMapper;
	
	@Override
	public List<ReplyVO> list(int boardNum) {
		List<ReplyVO> replyList = mapper.list(boardNum);
		for(int i=0; i<replyList.size();i++) {
			ReplyVO reply = replyList.get(i);
			PictureVO pic = picMapper.getProfile(reply.getMemNum());
			if(pic!=null) {
				String picName = pic.getPicPath()+"/"+pic.getPicUuid()+'_'+pic.getPicName()+"."
							+pic.getPicTail();
				reply.setProfile(picName);
			}
		}
		return replyList;
	}

	@Override
	public ReplyVO get(int memNum) {
		return mapper.get(memNum);
	}
	
	@Override
	public int getCount(int boardNum) {
		return mapper.getCount(boardNum);
	}

	@Override
	public int insert(ReplyVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int update(ReplyVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int delete(int replyNum) {
		return mapper.delete(replyNum);
	}

}
