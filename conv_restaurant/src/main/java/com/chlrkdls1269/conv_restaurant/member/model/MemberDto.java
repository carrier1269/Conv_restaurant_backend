package com.chlrkdls1269.conv_restaurant.member.model;

import java.sql.Date;

import lombok.Data;

// @Getter @Setter @ToString
@Data
public class MemberDto {
	private String id;
	private String password;
	private String nickname;
	private String email;
	private int age;
	private String gender;
	private Date joinDate;
}
