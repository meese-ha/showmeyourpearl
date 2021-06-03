package com.pearl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.domain.CustomUser;
import com.pearl.domain.MemberVO;
import com.pearl.domain.PictureVO;
import com.pearl.mapper.MemberMapper;
import com.pearl.mapper.PictureMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private PictureMapper picMapper;
	
	@Transactional
	public int joinUser(MemberVO member) {
		// 비밀번호 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setMemPass(passwordEncoder.encode(member.getMemPass()));
		return mapper.insert(member);
	}
	
	@Transactional
	public int updateUser(MemberVO member) {
        // 비밀번호 암호화
		if(member.getMemPass()!=null&&!member.getMemPass().trim().equals("")) {
			log.info("ServicePass>>>>>"+member.getMemPass());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			member.setMemPass(passwordEncoder.encode(member.getMemPass()));
		}
		int result = mapper.update(member);
		if(member.getProfile()==null) {
			return result;
		}
		PictureVO picture = member.getProfile();
		log.info(">>>>>>>>PostNum:"+member.getMemNum());
		String picPath = picture.getPicPath();
		log.info(">>>>>>>>picPath:"+picPath.split("\\\\")[0]+"%5C"+picPath.split("\\\\")[1]+"%5C"+picPath.split("\\\\")[2]);
		picture.setPicPath(
				picPath.split("\\\\")[0]+"%5C"+picPath.split("\\\\")[1]+"%5C"+picPath.split("\\\\")[2]);
		picture.setPostNum(member.getMemNum());
		picture.setPicClass("p");
		if(picMapper.getProfile(member.getMemNum())==null) picMapper.insertPic(picture); 
		else picMapper.updatePic(picture); 
		return result;
    }
	
	public boolean emailCheck(String memEmail) {
		if(mapper.get(memEmail)==null) {
			return true;
		}
		return false;
	}

    @Override
    public CustomUser loadUserByUsername(String memEmail) throws UsernameNotFoundException {
    	MemberVO member = mapper.get(memEmail);
        return new CustomUser(member);
    }
    
}
