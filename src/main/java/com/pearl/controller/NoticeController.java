package com.pearl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.BoardVO;
import com.pearl.service.NoticeService;

import lombok.Setter;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {
	
	@Setter(onMethod_ = @Autowired)
	private NoticeService service;
	
	@RequestMapping("/list")
	public ModelAndView list(Model model) {
		ModelAndView mv = new ModelAndView("notice/list");
		mv.addObject("list", service.list());
		
		return mv;
	}

	@RequestMapping("/read")
	public ModelAndView read(int boardNum, Model model) {
		ModelAndView mv = new ModelAndView("notice/read");
		mv.addObject("notice", service.read(boardNum));
		
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/write")
	public ModelAndView writePage() {
		ModelAndView mv = new ModelAndView("notice/write");
		
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/write")
	public ModelAndView writeSend(BoardVO vo) {
		ModelAndView mv = new ModelAndView("redirect:/notice/list");
		service.insert(vo);
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/modify")
	public ModelAndView modifyPage(int boardNum, Model model) {
		ModelAndView mv = new ModelAndView("notice/modify");
		mv.addObject("notice", service.read(boardNum));
		
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/modify")
	public ModelAndView modifySend(BoardVO vo) {
		ModelAndView mv = new ModelAndView("redirect:/notice/list");
		service.update(vo);
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/delete")
	public ModelAndView delete(int boardNum, Model model) {
		ModelAndView mv = new ModelAndView("redirect:/notice/list");
		service.delete(boardNum);
		
		return mv;
	}
}
