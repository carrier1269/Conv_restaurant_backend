package com.chlrkdls1269.conv_restaurant.member.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import com.chlrkdls1269.conv_restaurant.member.model.MemberDto;

@Repository
@Mapper
public interface IMemberRepository {
	MemberDto findMemberById(@Param("memberId") String memberId);
	void insertMember(MemberDto member);
	void deleteMember(@Param("memberId") String memberId);
	void updateMember(@Param("member") MemberDto member, @Param("memberId") String memberId);
	String getPassword(@Param("memberId") String memberId);
	
}
