package com.chlrkdls1269.conv_restaurant.member.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MemberUserDetails extends User{
	private static final long serialVersionUID = -4679039456330600424L;
	
	public MemberUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
}
