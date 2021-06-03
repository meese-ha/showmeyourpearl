package com.pearl.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.CustomUser;
import com.pearl.domain.FundVO;
import com.pearl.domain.GalleryVO;
import com.pearl.domain.MemberVO;
import com.pearl.domain.PayDTO;
import com.pearl.domain.PictureVO;
import com.pearl.service.FundService;
import com.pearl.service.PayService;

import lombok.Setter;

@Controller
@RequestMapping("/fund/*")
public class FundController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Setter(onMethod_ = @Autowired)
	private FundService service;
	
	@Setter(onMethod_ = @Autowired)
	private PayService Pservice;
	
	@RequestMapping("/fundList")
	public ModelAndView list(@ModelAttribute("vo") FundVO vo) {
		ModelAndView mv = new ModelAndView("/fund/fundList");
		List<FundVO> fund = service.getList(vo);
		if(vo.getMemNum()!=null) mv.addObject("memNum", vo.getMemNum());
		mv.addObject("list", fund);
		return mv;
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/write")
	public ModelAndView fundWrite() {
		ModelAndView mv = new ModelAndView("fund/fundWrite");
		return mv;
	}
	
	@Transactional
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/writeFund")
	public ModelAndView fundWrite(FundVO vo,@AuthenticationPrincipal CustomUser user
			, @RequestParam("file") MultipartFile file) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/fund/fundList");
		log.info("RwVo>>>>>>>>>>"+vo.getRwvo());
		vo.setMemNum(user.getMember().getMemNum());
		vo.setPic(uploadPicture(file));
		service.insert(vo);
		return mv;
	}
	
	@PreAuthorize("principal.username == #memEmail")
	@GetMapping("/modify")
	public ModelAndView modify(Long fundNum,String memEmail) {
		ModelAndView mv = new ModelAndView("/fund/fundUpdate");
		FundVO detail = service.get(fundNum);
		MemberVO artist = service.artist(detail.getMemNum());
		mv.addObject("detail", detail);
		mv.addObject("artist", artist);
		mv.addObject("rewardList", detail.getRwvo());
		return mv;
	}
	
	@PreAuthorize("principal.username == #memEmail")
	@PostMapping("/modify")
	public ModelAndView modify(FundVO vo,String memEmail) {
		ModelAndView mv = new ModelAndView("redirect:/fund/get?fundNum="+ vo.getFundNum());		
		service.update(vo);		
		return mv;
	}
	
	@GetMapping("/get")
	public ModelAndView get(Long fundNum) {
		ModelAndView mv = new ModelAndView("/fund/fundGeting");
		FundVO detail = service.get(fundNum);
		MemberVO artist = service.artist(detail.getMemNum());
		mv.addObject("detail", detail);
		mv.addObject("artist", artist);
		mv.addObject("rewardList", detail.getRwvo());
		return mv;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/getPay")
	public ModelAndView get(FundVO vo) {
		ModelAndView mv = new ModelAndView("/fund/fundPay");
		log.info("fundVO>>>>>>>>>>>"+vo);
		vo = service.getPay(vo);
		MemberVO artist = service.artist(vo.getMemNum());
		log.info("fund>>>>>>>>>>"+vo.getRwvo());
		mv.addObject("fund", vo);
		mv.addObject("artist", artist);
		mv.addObject("reward", vo.getRwvo());
		return mv;
	}

	@PreAuthorize("principal.username == #memEmail")
	@RequestMapping("/delete")
	public String delete(Long fundNum,String memEmail) {
		service.delete(fundNum);
		return "redirect:/fund/fundList";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/pay")
	public ModelAndView fundPay(PayDTO dto, String Address, String Address2,
			@AuthenticationPrincipal CustomUser customUser) {
		ModelAndView mv = new ModelAndView("redirect:/fund/get?fundNum="+ dto.getFundNum());
		dto.setPayAddress(Address +" "+Address2);
		dto.setMemNum(customUser.getMember().getMemNum());
		log.info(">>>>>dto :"+dto);
		Pservice.insert(dto);
		return mv;
	}
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		return str.replace("-", File.separator); //separator:폴더와 폴더의 구분자
	}
	
	private PictureVO uploadPicture(MultipartFile file) {
		PictureVO picture = new PictureVO();
		String uploadFolder = "c:\\pearl";
		
		//저장 경로를 File객체에 담음. 파일이 아닌 디렉토리
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("uploadPath: "+uploadPath);
		if(uploadPath.exists()==false) uploadPath.mkdirs();
		
		log.info("uploadFile Name :" + file.getOriginalFilename());
		log.info("uploadFile Size :" + file.getSize());
		
		String uploadFileName = file.getOriginalFilename();
		
		//IE has file path
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
		log.info(uploadFileName);
		picture.setPicName(uploadFileName.substring(0, uploadFileName.lastIndexOf(".")));
		picture.setPicTail(uploadFileName.substring(uploadFileName.lastIndexOf(".")+1));
		
		UUID uuid = UUID.randomUUID();
		uploadFileName = uuid.toString()+"_"+uploadFileName;
		
		File saveFile = new File(uploadPath, uploadFileName);
		try {
			file.transferTo(saveFile);
			picture.setPicUuid(uuid.toString());
			picture.setPicPath(getFolder());
		} catch (Exception e) {
			log.error(e.getMessage());
		} //end catch
		
		return picture;
	}
}
