package com.chlrkdls1269.conv_restaurant.member.service;

import javax.security.auth.Subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chlrkdls1269.conv_restaurant.member.model.MemberDto;
import com.chlrkdls1269.conv_restaurant.member.repository.IMemberRepository;

@Service
public class MemberService implements IMemberService {
	@Autowired
	IMemberRepository memberRepository;
	private static Logger logger = LoggerFactory.getLogger(MemberService.class.getName());
	@Override
	public MemberDto findMemberById(String memberId) {
		return memberRepository.findMemberById(memberId);
	}
	@Override
	public void insertMember(MemberDto member) {
		memberRepository.insertMember(member);
	}
	@Override
	public void deleteMember(String memberId) {
		memberRepository.deleteMember(memberId);
	}
	@Override
	public void updateMember(MemberDto member, String memberId) {
		memberRepository.updateMember(member, memberId);
	}
	@Override
	public String getPassword(String memberId) {
		return memberRepository.getPassword(memberId);
	}
}
