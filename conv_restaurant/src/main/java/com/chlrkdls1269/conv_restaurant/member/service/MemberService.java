package com.chlrkdls1269.conv_restaurant.member.service;

import java.util.List;

import javax.security.auth.Subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metanetglobal.LMS.student.controller.StudentContorller;
import com.metanetglobal.LMS.student.model.StudentDto;
import com.metanetglobal.LMS.student.model.StudentUpdateDto;
import com.metanetglobal.LMS.student.model.Student;
import com.metanetglobal.LMS.student.repository.IStudentRepository;


@Service
public class MemberService implements IMemberService {
	@Autowired
	IMemberRepository studentRepository;
	private static Logger logger = LoggerFactory.getLogger(MemberService.class.getName());
	

	public MemberDto findStudentById(String studentId) {
		logger.info("서비스");
		logger.info("studentId {}",studentId);
		System.out.println("service" + studentId);
		return studentRepository.findStudentById(studentId);
	}
	
	public void insertStudent(Student student) {
		studentRepository.insertStudent(student);
	}
	
	public void deleteStudent(String email) {
		studentRepository.deleteStudent(email);
	}
	
	public void updateStudent(StudentUpdateDto student) {
		logger.info("회원정보 수정중...");
		studentRepository.updateStudent(student);
	}

	@Override
	public Student getStudentInfo(String studentId) {
		return studentRepository.getStudentInfo(studentId);
	}

	@Override
	public String getPassword(String studentId) {
		return studentRepository.getPassword(studentId);
	}

}
