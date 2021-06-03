package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.AdminBoardVO;
import com.pearl.domain.AdminFundVO;
import com.pearl.domain.AdminPaymentVO;
import com.pearl.domain.BoardVO;
import com.pearl.domain.MemberVO;
import com.pearl.domain.SearchVO;

@Mapper
public interface AdminMapper {
	List<AdminBoardVO> boardList();
	List<AdminFundVO> fundList();
	List<AdminPaymentVO> paymentList();
	List<AdminPaymentVO> searchPaymentList(SearchVO vo);
	List<MemberVO> test(MemberVO vo);
	List<MemberVO> adminmem(MemberVO vo);
	List<BoardVO> boardpage(BoardVO vo);
	List<AdminFundVO> fundpage(AdminFundVO vo);
	List<AdminPaymentVO> paymentpage(AdminPaymentVO vo);
	int selectTotalCount(MemberVO vo);
	int selectTotalCount2(BoardVO vo);
	int selectTotalCount3(AdminFundVO vo);
	int selectTotalCount4(AdminPaymentVO vo);
	int selectTotalCount5(MemberVO vo);
	int fundDelete(Long arrFundNum[]);
	int memberDelete(Long arrMemNum[]);
	int postDelete(Long arrBoardNum[]);
	int paymentDelete(Long arrPayNum[]);
}
