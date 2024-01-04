package com.chlrkdls1269.conv_restaurant.member.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.chlrkdls1269.conv_restaurant.member.service.IMemberService;

@Component
public class MemberUserDetailsService implements UserDetailsService{
	@Autowired
	private IMemberService memberService; 
//	
//	@Autowired
//	private IRoleRepository roleRepository; 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDto memberInfo = memberService.findMemberById(username);
		
		if(memberInfo == null) {
			throw new UsernameNotFoundException("["+username+"]사용자가 존재하지 않습니다.");
		}
		
		
//		String request_login_user_roleName = roleRepository.getRoleName(username);
		
//		System.out.println("username : " + username + "rolename : " + request_login_user_roleName);
		
		
		
//		String[] roles = {request_login_user_roleName};
		
		String[] roles = {"ROLE_USER", "ROLE_ADMIN"};

		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roles);
		
		// 암호화되지 않은 pwd를 사용할 경우 "{noop}"+pwd로 표기한다..
		return new MemberUserDetails(memberInfo.getMemberId()
				, memberInfo.getMemberPassword()
				, authorities);
	}
	
}