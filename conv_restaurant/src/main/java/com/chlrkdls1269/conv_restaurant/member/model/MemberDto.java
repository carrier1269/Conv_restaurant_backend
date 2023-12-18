package com.chlrkdls1269.conv_restaurant.member.model;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberDto {
	private String name;
	private String email;
	private String address;
	private String phoneNumber;
	private String grade;
	private Date admissionYear;
	private String password;
}
