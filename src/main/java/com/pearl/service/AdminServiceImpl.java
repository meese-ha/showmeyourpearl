package com.pearl.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.AdminBoardVO;
import com.pearl.domain.AdminFundVO;
import com.pearl.domain.AdminPaymentVO;
import com.pearl.domain.BoardVO;
import com.pearl.domain.MemberVO;
import com.pearl.domain.SearchVO;
import com.pearl.mapper.AdminMapper;
import com.pearl.paging.PaginationInfo;

import lombok.Setter;

@Service
public class AdminServiceImpl implements AdminService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Setter(onMethod_ = @Autowired)
	private AdminMapper mapper;

	@Override
	public List<AdminBoardVO> boardList() {
		return mapper.boardList();
	}

	@Override
	public List<AdminFundVO> fundList() {
		return mapper.fundList();
	}
	
	@Override
	public List<AdminPaymentVO> paymentList() {
		return mapper.paymentList();
	}
	
	@Override
	public List<AdminPaymentVO> searchPaymentList(SearchVO vo) {
		return mapper.searchPaymentList(vo);
	}

	@Override
	public int fundDelete(Long[] arrFundNum) {
		return mapper.fundDelete(arrFundNum);
	}

	@Override
	public int memberDelete(Long[] arrMemNum) {
		return mapper.memberDelete(arrMemNum);
	}

	@Override
	public int postDelete(Long[] arrBoardNum) {
		return mapper.postDelete(arrBoardNum);
	}

	@Override
	public int paymentDelete(Long[] arrPayNum) {
		return mapper.paymentDelete(arrPayNum);
	}

	@Override
	public List<MemberVO> test(MemberVO vo) {
		List<MemberVO> test = Collections.emptyList();
		int count = mapper.selectTotalCount(vo);
		log.info("list count:"+count);
		PaginationInfo pagiInfo = new PaginationInfo(vo);
		pagiInfo.setTotalCount(count);
		
		vo.setPagiInfo(pagiInfo);
		
		if(count>0) {
			test=mapper.test(vo);
		}
		return test;
	}

	@Override
	public List<MemberVO> adminmem(MemberVO vo) {
		List<MemberVO> adminmem = Collections.emptyList();
		int count = mapper.selectTotalCount5(vo);
		log.info("list count:"+count);
		PaginationInfo pagiInfo = new PaginationInfo(vo);
		pagiInfo.setTotalCount(count);
		
		vo.setPagiInfo(pagiInfo);
		
		if(count>0) {
			adminmem=mapper.adminmem(vo);
		}
		return adminmem;
	}
	@Override
	public List<BoardVO> boardpage(BoardVO vo) {
		List<BoardVO> boardpage = Collections.emptyList();
		int count = mapper.selectTotalCount2(vo);
		log.info("list count:"+count);
		PaginationInfo pagiInfo = new PaginationInfo(vo);
		pagiInfo.setTotalCount(count);
		
		vo.setPagiInfo(pagiInfo);
		
		if(count>0) {
			boardpage=mapper.boardpage(vo);
		}
		return boardpage;
	}

	@Override
	public List<AdminFundVO> fundpage(AdminFundVO vo) {
		List<AdminFundVO> fundpage = Collections.emptyList();
		int count = mapper.selectTotalCount3(vo);
		log.info("list count:"+count);
		PaginationInfo pagiInfo = new PaginationInfo(vo);
		pagiInfo.setTotalCount(count);
		
		vo.setPagiInfo(pagiInfo);
		
		if(count>0) {
			fundpage=mapper.fundpage(vo);
		}
		return fundpage;
	}

	@Override
	public List<AdminPaymentVO> paymentpage(AdminPaymentVO vo) {
		List<AdminPaymentVO> paymentpage = Collections.emptyList();
		int count = mapper.selectTotalCount4(vo);
		log.info("list count:"+count);
		PaginationInfo pagiInfo = new PaginationInfo(vo);
		pagiInfo.setTotalCount(count);
		
		vo.setPagiInfo(pagiInfo);
		
		if(count>0) {
			paymentpage=mapper.paymentpage(vo);
		}
		return paymentpage;
	}



}
