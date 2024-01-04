package com.chlrkdls1269.conv_restaurant.member.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chlrkdls1269.conv_restaurant.member.model.MemberDto;

public interface IMemberService {
	MemberDto findMemberById(String memberId);
	void insertMember(MemberDto member);
	void deleteMember(String memberId);
	void updateMember(MemberDto member, String memberId);
	String getPassword(String memberId);
}
