package com.pearl.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pearl.domain.CustomUser;
import com.pearl.domain.EmotionVO;
import com.pearl.domain.GalleryVO;
import com.pearl.domain.MemberVO;
import com.pearl.domain.PictureVO;
import com.pearl.domain.SubscribeVO;
import com.pearl.service.AdminService;
import com.pearl.service.EmotionService;
import com.pearl.service.MemberService;
import com.pearl.service.MyPageService;
import com.pearl.service.UserDetailServiceImpl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MypageController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	@Setter(onMethod_ = @Autowired)
	private MyPageService mypageservice;
	
	@Setter(onMethod_ = @Autowired)
	private UserDetailServiceImpl user;

	@Setter(onMethod_ = @Autowired)
	private EmotionService emotion;

	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/mypage/delete")
	public ModelAndView adminmem(Long memNum, HttpSession session){
		ModelAndView mv = new ModelAndView("redirect:/");
		mypageservice.editdelete(memNum);
		session.invalidate();
		return mv;
	}
	
	
	@RequestMapping("/mypage")
	public ModelAndView my(@AuthenticationPrincipal CustomUser customUser, Long memNum) {
		ModelAndView mv = new ModelAndView("/mypage/my");
		if(memNum==null) { 
			if(customUser==null) {
				mv.setViewName("redirect:/login");
				return mv;
			}
			memNum = customUser.getMember().getMemNum();
		}
		List<SubscribeVO> subLists = mypageservice.subList(memNum);
		if(customUser!=null&&memNum!=customUser.getMember().getMemNum()) {
			boolean result = false;
			for(int i=0;i<subLists.size();i++) {
				SubscribeVO sub = subLists.get(i);
				if(sub.getAudience()==customUser.getMember().getMemNum()) {
					result = true;
				}
			}
			if(result) mv.addObject("subs", "true");
			else mv.addObject("subs", "false");
		}
		List<GalleryVO> myGallery = mypageservice.myGallery(memNum);
		for(int j=0; j<myGallery.size();j++) {
			GalleryVO gal = myGallery.get(j);
			List<EmotionVO> emoList = emotion.emoCount(gal.getBoardNum().intValue());
			for(int k=0; k<emoList.size();k++) {
				EmotionVO emo = emoList.get(k);
				if(emo.getEmoExpress().equals("a")) {
					gal.setLike(emo.getEmoCount());
				}else if(emo.getEmoExpress().equals("b")) {
					gal.setSad(emo.getEmoCount());
				}else if(emo.getEmoExpress().equals("c")) {
					gal.setAngry(emo.getEmoCount());
				}else if(emo.getEmoExpress().equals("d")) {
					gal.setHappy(emo.getEmoCount());
				}
			}
		}
		mv.addObject("meminfo", service.getProfile(memNum));
		mv.addObject("subscriber", subLists.size());
		mv.addObject("myGallery", myGallery);
		return mv;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/mypage/edit")
	public ModelAndView edit(@AuthenticationPrincipal CustomUser customUser) {
		ModelAndView mv = new ModelAndView("/mypage/edit");
		MemberVO member = service.getProfile(customUser.getMember().getMemNum());
		member.setMemPass("");
		mv.addObject("meminfo", member);
		return mv;
	}
	
	
	@PreAuthorize("principal.username == #vo.memEmail")
	@PostMapping("/mypage/editsend")
	 public String Editsend(MemberVO vo,@RequestParam("file") MultipartFile file,
			 RedirectAttributes rttr, @AuthenticationPrincipal CustomUser customUser) {
		
		String nickname = vo.getMemName();
		 if(!customUser.getMember().getMemName().equals(nickname)&&
			  !service.checkName(nickname)) {
			  rttr.addFlashAttribute("result","EditFail"); 
		  }else { 
			  log.info("VO2>>>>>"+vo);
			  if(file.getSize()>0) vo.setProfile(uploadPicture(file));
			  user.updateUser(vo); 
			  customUser.getMember().setMemName(nickname);
		  }
		 return "redirect:/mypage/edit"; 
	}
	
	 
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/mypage/subinfo")
	public ModelAndView subinfo(@AuthenticationPrincipal CustomUser customUser) {
		ModelAndView mv = new ModelAndView("/mypage/subinfo");
		mv.addObject("list",mypageservice.mySubList(customUser.getMember().getMemNum()));
		return mv;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/mypage/myfund")
	public ModelAndView myfund(@AuthenticationPrincipal CustomUser customUser) {
		ModelAndView mv = new ModelAndView("mypage/myfund");
		mv.addObject("MyfundList", mypageservice.myfundList(customUser.getMember().getMemNum())); 
		return mv;
	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/mypage/donafund")
	public ModelAndView donafund(@AuthenticationPrincipal CustomUser customUser) {
		ModelAndView mv = new ModelAndView("mypage/donafund");
		mv.addObject("donaList", mypageservice.donaList(customUser.getMember().getMemNum()));
		return mv;
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/subscribe")
	public ResponseEntity<Integer> subscribe(@RequestBody SubscribeVO subscriber) {
		log.info("subscribe>>>>>>>>>>>>"+subscriber);
		List<SubscribeVO> subLists = mypageservice.subList(subscriber.getArtist());
		for(int i=0;i<subLists.size();i++) {
			SubscribeVO sub = subLists.get(i);
			if(sub.getAudience()==subscriber.getAudience()) {
				mypageservice.unsubscribe(subscriber);
				return  new ResponseEntity<Integer>(subLists.size()-1, HttpStatus.OK);
			}
		}
		mypageservice.subscribe(subscriber);
		return new ResponseEntity<Integer>(subLists.size()+1, HttpStatus.OK);
	}

	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		return str.replace("-", File.separator); //separator:폴더와 폴더의 구분자
	}
	
	private PictureVO uploadPicture(MultipartFile file) {
		PictureVO picture = new PictureVO();
		String uploadFolder = "c:\\pearl";
		
		if(!file.isEmpty()) {
			String contentType = file.getContentType();
			if(contentType.contains("image/jpeg")) {
				picture.setPicTail("jpg");
			}else if(contentType.contains("image/gif")) {
				picture.setPicTail("gif");
			}else if(contentType.contains("image/png")) {
				picture.setPicTail("png");
			}else if(contentType.contains("image/bmp")) {
				picture.setPicTail("bmp");
			}else {
				return null;
			}
		}
		log.info("확장자>>>>"+picture.getPicTail());
		
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
		if(!checkImageType(saveFile)) return null;
		try {
			file.transferTo(saveFile);
			picture.setPicUuid(uuid.toString());
			picture.setPicPath(getFolder());
		} catch (Exception e) {
			log.error(e.getMessage());
		} //end catch
		
		return picture;
	}
	
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			log.info("checkImage>>>>>"+contentType);
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
