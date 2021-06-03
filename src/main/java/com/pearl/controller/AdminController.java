package com.pearl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.AdminFundVO;
import com.pearl.domain.AdminPaymentVO;
import com.pearl.domain.BoardVO;
import com.pearl.domain.MemberVO;
import com.pearl.domain.SearchVO;
import com.pearl.service.AdminService;
import com.pearl.service.MemberService;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService memberService;
	
	@Setter(onMethod_ = @Autowired)
	private AdminService adminService;

	
	   @RequestMapping("/member")
	   public ModelAndView member(@ModelAttribute("vo") MemberVO vo) {
	      ModelAndView mv = new ModelAndView("admin/member");
	      vo.setAmount(10);
	      List<MemberVO> tist = adminService.test(vo);
	      mv.addObject("list", tist);
	      return mv;
	   }
	   
	   @RequestMapping("/adminmem")
	   public ModelAndView adminmem(@ModelAttribute("vo") MemberVO vo) {
	      ModelAndView mv = new ModelAndView("admin/adminmem");
	      vo.setAmount(10);
	      List<MemberVO> tist = adminService.adminmem(vo);
	      mv.addObject("list", tist);
	      return mv;
	   }
	
	   @RequestMapping("/post")
	   public ModelAndView post(@ModelAttribute("vo") BoardVO vo) {
		   ModelAndView mv = new ModelAndView("admin/post");
		   vo.setAmount(10);
		   List<BoardVO> paging= adminService.boardpage(vo);
		   mv.addObject("list", paging);
		   return mv;
	   }

	   @RequestMapping("/fund")
	   public ModelAndView fund(@ModelAttribute("vo") AdminFundVO vo) {
		   ModelAndView mv = new ModelAndView("admin/fund");
		   vo.setAmount(10);
		   List<AdminFundVO> fundpage= adminService.fundpage(vo);
		   mv.addObject("list", fundpage);
		   return mv;
	   }
	   
	   @RequestMapping("/payment")
	   public ModelAndView payment(@ModelAttribute("vo") AdminPaymentVO vo) {
		   ModelAndView mv = new ModelAndView("admin/payment");
			vo.setAmount(10);
		   List<AdminPaymentVO> paymentpage= adminService.paymentpage(vo);
		   mv.addObject("list", paymentpage);
		   return mv;
	   }

	   @RequestMapping("/member/profile")
	   public ModelAndView profile(Long memNum) {
		ModelAndView mv = new ModelAndView("admin/profile");
		MemberVO vo = memberService.getProfile(memNum);
		vo.setMemPass("");
		mv.addObject("vo", vo);
		return mv;
	}
	   
	 // @PostMapping("/adminupdate") 
	 //  public String adminupdayeForm(MemberVO vo) {
	//	memberService.update(vo); 
	//	return "redirect:/admin/member/profile";
	//	} 
	  

	@GetMapping("/profileModify")
	public ModelAndView profileModify(Long memNum) {
		ModelAndView mv = new ModelAndView("admin/profileModify");
		MemberVO vo = memberService.getProfile(memNum);
		vo.setMemPass("");
		mv.addObject("vo", vo);
		
		return mv;
	}
	
	@PostMapping("/member/profileModify")
	public ModelAndView profileModifySend(MemberVO vo) {
		ModelAndView mv = new ModelAndView("redirect:/admin/member/profile?memNum=" + vo.getMemNum());
		memberService.update(vo);
		return mv;
	}
	
	@PostMapping("/modifyForm/delete")
	public ModelAndView modifyForm( Long memNum[]) { 
		ModelAndView mv = new ModelAndView("redirect:/admin/member");
		adminService.memberDelete(memNum);
		return mv;
	}
	
	@RequestMapping("/member/delete")
	public ModelAndView delete(Model model, Long memNum[]) { 
		ModelAndView mv = new ModelAndView("redirect:/admin/member");
		if(memNum.length > 0 ) {
			adminService.memberDelete(memNum);
		} else {
			mv.setViewName("redirect:/admin/member");
		}
		return mv;
	}
	
	@RequestMapping("/adminmem/delete")
	public ModelAndView adminmem(Model model, Long memNum[]) {
		ModelAndView mv = new ModelAndView("redirect:/admin/adminmem");
		if(memNum.length > 0 ) {
			adminService.memberDelete(memNum);
		} else {
			mv.setViewName("redirect:/admin/adminmem");
		}
		return mv;
	}

	
	@RequestMapping("/post/delete")
	public ModelAndView postDelete(Model model, Long boardNum[]) {
		ModelAndView mv = new ModelAndView("redirect:/admin/post");
		if(boardNum.length > 0 ) {
			adminService.postDelete(boardNum);
		} else {
			mv.setViewName("redirect:/admin/post");
		}
		return mv;
	}
	
	@RequestMapping("/fund/delete")
	public ModelAndView fundDelete(Model model, Long fundNum[]) {
		ModelAndView mv = new ModelAndView("redirect:/admin/fund");
		if(fundNum.length > 0 ) {
			adminService.fundDelete(fundNum);
		} else {
			mv.setViewName("redirect:/admin/fund");
		}
		return mv;
	}
	
	@RequestMapping("/payment/delete")
	public ModelAndView paymentDelete(Model model, Long payNum[]) {
		ModelAndView mv = new ModelAndView("redirect:/admin/payment");
		if(payNum.length > 0 ) {
			adminService.paymentDelete(payNum);
		} else {
			mv.setViewName("redirect:/admin/payment");
		}
		return mv;
	}
	
	@ResponseBody
	@PostMapping("/searchPayment")
	public ResponseEntity<List<AdminPaymentVO>> searchPaymentList(String searchType, Long searchValue) {
		SearchVO vo = new SearchVO(searchType, searchValue);
		System.out.print(vo);
		List<AdminPaymentVO> list = adminService.searchPaymentList(vo);
		return new ResponseEntity<List<AdminPaymentVO>>(list,HttpStatus.OK);
	}

}
