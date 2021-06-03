package com.pearl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pearl.domain.MemberVO;
import com.pearl.service.UserDetailServiceImpl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController { 
	
	@Setter(onMethod_ =@Autowired)
	private UserDetailServiceImpl user;
	
	@GetMapping("/login")
	public void login(String error, Model model) {
		model.addAttribute("error",error);
	}
	
	@PostMapping("/join")
	public String insertForm(MemberVO vo, RedirectAttributes rttr) {
		log.info("VO1>>>>>"+vo);
		String email = vo.getMemEmail();
		if(!user.emailCheck(email)) {
			rttr.addFlashAttribute("result","joinFail");
		}else {
			rttr.addFlashAttribute("result","joinSuccess");
			log.info("VO2>>>>>"+vo);
			user.joinUser(vo);
		}
		return "redirect:/login";
	}
	
	@RequestMapping("/denied")
	public void dednied() {}
	
}
