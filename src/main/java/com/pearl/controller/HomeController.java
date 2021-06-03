package com.pearl.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.AdminFundVO;
import com.pearl.domain.GalleryVO;
import com.pearl.service.MainService;

import lombok.Setter;

@Controller

public class HomeController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Setter(onMethod_ = @Autowired)
	private MainService mainservice;

	@RequestMapping("/")
	public ModelAndView main(@ModelAttribute("vo") GalleryVO vo, Model model) {
		ModelAndView mv = new ModelAndView("main");
		List<GalleryVO> gallery = mainservice.list(vo);
		List<AdminFundVO> fund = mainservice.fundlist();
		mv.addObject("list", gallery);
		mv.addObject("fund", fund);
		return mv;
	}

	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		File file = new File("c:\\pearl\\"+fileName);
		log.info("file>>>>>>>>"+file);
		ResponseEntity<byte[]> result = null;
		HttpHeaders header = new HttpHeaders();
		try {
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(
					FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
