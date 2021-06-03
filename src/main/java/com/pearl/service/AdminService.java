package com.pearl.service;

import java.util.List;

import com.pearl.domain.AdminBoardVO;
import com.pearl.domain.AdminFundVO;
import com.pearl.domain.AdminPaymentVO;
import com.pearl.domain.BoardVO;
import com.pearl.domain.MemberVO;
import com.pearl.domain.SearchVO;

public interface AdminService {
	List<AdminBoardVO> boardList();
	List<AdminFundVO> fundList();
	List<AdminPaymentVO> paymentList();
	List<AdminPaymentVO> searchPaymentList(SearchVO vo);
	List<MemberVO> test(MemberVO vo);
	List<MemberVO> adminmem(MemberVO vo);
	List<BoardVO> boardpage(BoardVO vo);
	List<AdminFundVO> fundpage(AdminFundVO vo);
	List<AdminPaymentVO> paymentpage(AdminPaymentVO vo);
	int fundDelete(Long arrFundNum[]);
	int memberDelete(Long arrMemNum[]);
	int postDelete(Long arrBoardNum[]);
	int paymentDelete(Long arrPayNum[]);
}
