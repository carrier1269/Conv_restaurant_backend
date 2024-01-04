package com.chlrkdls1269.conv_restaurant.member.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chlrkdls1269.conv_restaurant.member.model.MemberDto;
import com.chlrkdls1269.conv_restaurant.member.service.IMemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@Tag(name = "Member", description = "회원 관리 API")
public class MemberContorller {
	@Autowired
	IMemberService memberService;	
	
	@Operation(summary = "회원 가입")
	@PostMapping("/signin")
	public ResponseEntity<String> insertStudent(@RequestBody MemberDto member) {
	    PasswordEncoder pwEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	   
	    String unbcrypt_pwd = member.getMemberPassword();
	    String bcrypt_pwd = pwEncoder.encode(unbcrypt_pwd);
	      
	    member.setMemberPassword(bcrypt_pwd);
	    
	    Date now = new Date();
	    
	    member.setMemberJoindate(new java.sql.Date(now.getTime()));
	    
	    // 일반 유저 회원가입시 일반 권한 부여
	    member.setMemberRole("ROLE_USER");
	    
	    memberService.insertMember(member);
	    
	    String responseOk = "(회원 가입) API response Ok";
		
		return ResponseEntity.ok(responseOk);
	}
	
	// GET /login
	// 여기에
	// memberId랑 password를 formdata로 제공하면 된다.
	@Operation(summary = "GET /login -> 로그인 완료 시 실행되는 API")
	@GetMapping("/loginSuccess")
	public ResponseEntity<String> loginOk() {
		String response = "(로그인 성공) API response Ok";
		
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "GET /logout -> 로그아웃 완료 시 실행되는 API")
	@GetMapping("/logoutSuccess")
	public ResponseEntity<String> logoutOk() {
		String response = "(로그아웃 성공) API response Ok";
		
		return ResponseEntity.ok(response);
		
	}
	
	@Operation(summary = "회원 정보 조회 (마이페이지)")
	@GetMapping("/member/mypage")
	public ResponseEntity<MemberDto> findMemberInformation(Principal principal) {
		String username = principal.getName();
		
		if(username!=null&&!username.equals("")) {
			try {
				MemberDto member = memberService.findMemberById(username);
				return ResponseEntity.ok(member);
			}catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
		}
		else {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	@Operation(summary = "회원 수정")
	@PatchMapping("/member/update")
	public ResponseEntity<String> updateStudent(@RequestBody MemberDto member, Principal principal){
		String username = principal.getName();
		
		if(username!=null&&!username.equals("")) {
			try {
				memberService.updateMember(member, username);
				
				String responseOk = "(회원 수정) API response Ok";
				
				return ResponseEntity.ok(responseOk);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("데이터 에러");
			}
		}
		else {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("접속 유저 확인 에러");
		}
	}
	
//	@Autowired
//	PasswordEncoder passwordEncoder;
	
	@Operation(summary = "회원 탈퇴")
	@DeleteMapping("/member/delete") 
	public ResponseEntity<String> deleteMember(Principal principal) {
		String username = principal.getName();		
		if(username!=null&&!username.equals("")) {
			try {
				memberService.deleteMember(username);		
				
				String responseOk = "(회원 탈퇴) API response Ok";
				
				return ResponseEntity.ok(responseOk);
			}catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("데이터 에러");
			}
		}
		else {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("접속 유저 확인 에러");
		}
	}
}
