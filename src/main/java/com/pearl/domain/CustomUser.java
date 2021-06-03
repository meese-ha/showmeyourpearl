package com.pearl.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User {

	private static final long serialVersionUID = 1L;
	
	private MemberVO member;
	
	public CustomUser(MemberVO member) {
		super(member.getMemEmail(), member.getMemPass(), makeGrantedeAuth(member.getMemLevel()));
		this.member = member;
	}
	
	private static List<GrantedAuthority> makeGrantedeAuth(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (("admin").equals(role)) {
        	authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        return authorities;
    }
}
