package com.chlrkdls1269.conv_restaurant.member.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import com.metanetglobal.LMS.student.model.StudentDto;
import com.metanetglobal.LMS.student.model.StudentUpdateDto;
import com.metanetglobal.LMS.student.model.Student;
import com.metanetglobal.LMS.student.service.StudentService;

@Repository
@Mapper
public interface IMemberRepository {
	MemberDto findStudentById(@Param("studentId") String studentId);
	void insertStudent(@Param("student") Student student);
	void deleteStudent(@Param("email") String email);
	void updateStudent(@Param("student") StudentUpdateDto student);
	Student getStudentInfo(@Param("studentId") String studentId);
	String getPassword(@Param("studentId") String studentId);
	
}
