package com.chlrkdls1269.conv_restaurant.member.model;

import java.sql.Date;

import lombok.Data;

// @Getter @Setter @ToString
@Data
public class MemberDto {
	// 회원 ID
	private String memberId;
	
	// 이름
	private String memberName;
	
	// 이메일
	private String memberEmail;
	
	// 닉네임
	private String memberNickname;
	
	// 휴대폰 번호
	private String memberPhonenumber;
	
	// 비밀번호
	private String memberPassword;
	
	// 성별
	private String memberGender;
	
	// 나이
	private int memberAge;
	
	// 가입날짜
	private Date memberJoindate;
	
	// 가입상태
	private String memberState;
	
	// 신고여부
	private Date memberBanstate;
	
	// 권한
	private String memberRole;
}
