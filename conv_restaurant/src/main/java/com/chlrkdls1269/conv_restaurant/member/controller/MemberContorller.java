package com.chlrkdls1269.conv_restaurant.member.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.metanetglobal.LMS.student.model.StudentDto;
import com.metanetglobal.LMS.student.model.StudentUpdateDto;
import com.metanetglobal.LMS.course.model.Course;
import com.metanetglobal.LMS.course.service.ICourseService;
import com.metanetglobal.LMS.role.model.Role;
import com.metanetglobal.LMS.role.repository.IRoleRepository;
import com.metanetglobal.LMS.student.model.Student;
import com.metanetglobal.LMS.student.service.IStudentService;

import jakarta.servlet.http.HttpSession;

@RestController
public class MemberContorller {
	@Autowired
	IMemberService studentService;	
	@Autowired
	IRoleRepository roleRepository;
	

	private static Logger logger = LoggerFactory.getLogger(MemberContorller.class.getName());
	
	@GetMapping("/loginSuccess") //로그인 성공 메시지 출력
	public String loginOk() {
		return "로그인 되었습니다!";
	}
	
	@GetMapping("/logoutSuccess") //로그인 성공 메시지 출력
	public String logoutOk() {
		return "로그아웃 되었습니다!";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session, Principal principal) {
		String session_isCheck_userid = principal.getName();
		
		System.out.println(session_isCheck_userid);
		
		if(session_isCheck_userid != null && !session_isCheck_userid.equals("")) {
			session.invalidate();
			return "로그아웃 완료";
		} else {
			return "로그인이 필요한 서비스입니다";
		}
	}
	
	@GetMapping("/mypage") //회원정보 조회
	public MemberDto findStudentById(@RequestBody Map<String, String> map) {
		String Id = map.get("studentId");
		MemberDto student = studentService.findStudentById(Id);
		logger.info("studentId={}", map.get("studentId"));
		logger.info("student={}", student);
		return student;
	}
	

	@PostMapping("/signin") //회원가입
	public String insertStudent(@RequestBody Student student) {
	    PasswordEncoder pwEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	   
	    String unbcrypt_pwd = student.getPassword();
	    String bcrypt_pwd = pwEncoder.encode(unbcrypt_pwd);
	      
	    student.setPassword(bcrypt_pwd);

	    Role role = new Role();
	    role.setStudentId(student.getStudentId());
	    role.setRoleName("ROLE_USER");
	    studentService.insertStudent(student);
	    roleRepository.insertRole(role);
	    
	    return "ok";
	}

//	@PatchMapping("/mypage/update")
//	public String updateStudent(@RequestBody StudentUpdateDto student, HttpSession session){
//		try {
//			PasswordEncoder pwEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//			String encodedPw = pwEncoder.encode(student.getPassword());
//			logger.info("encodedPw {}", encodedPw);
//			student.setPassword(encodedPw);
//			studentService.updateStudent(student);
//			session.setAttribute("email", student.getEmail());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "회원정보 수정 실패";
//		}
//		return "회원정보수정 완료";
//	}
	
	@PatchMapping("/mypage/update")
	public String updateStudent(@RequestBody StudentUpdateDto student, Principal principal){
		String session_isCheck_userid = principal.getName();
		
		System.out.println(session_isCheck_userid);
		
		if(session_isCheck_userid != null && !session_isCheck_userid.equals("")) {
			try {
				PasswordEncoder pwEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
				   
			    String unbcrypt_pwd = student.getPassword();
			    String bcrypt_pwd = pwEncoder.encode(unbcrypt_pwd);
			      
			    student.setPassword(bcrypt_pwd);
				
			    if (student.getStudentId().equals(session_isCheck_userid)) {
			    	student.setStudentId(session_isCheck_userid);
				    
					studentService.updateStudent(student);
			    }else {
			    	return "회원정보 수정 실패";
			    }
			} catch (Exception e) {
				e.printStackTrace();
				return "회원정보 수정 실패";
			}
				return "회원정보수정 완료";
		}else {
			return "로그인이 필요한 서비스입니다.";
		}
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ICourseService courseService;
	
	
	@DeleteMapping("/mypage/delete") //회원 정보 삭제
	public String deleteStudent(@RequestBody Map<String, String> map, Principal principal) {
		String session_isCheck_userid = principal.getName();		
		if(session_isCheck_userid != null && !session_isCheck_userid.equals("")) {
			try {
				MemberDto student = studentService.findStudentById(session_isCheck_userid);
				System.out.println();
				String email = map.get("email");
				System.out.println("session email : "+ email + " db email : " + student.getEmail());
				if(email.equals(student.getEmail())){
					System.out.println("삭제!!!!!!!!!!!!!!");
					List<Course> courseList = courseService.getCourseList(session_isCheck_userid);
					
					System.out.println("리스트는!~~~~~: " + courseList);
					
					for(Course course : courseList) {
						System.out.println("학생 id : " + session_isCheck_userid + "학생 강좌 리스트");
						System.out.println(course.getCourseId());
						
						courseService.deleteCourse(session_isCheck_userid, course.getCourseId());
					}

					roleRepository.deleteRole(session_isCheck_userid);
					
					studentService.deleteStudent(email);
					
					return "삭제 성공";
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return "삭제 실패";
			}
		} else {
			return "로그인이 필요한 서비스입니다.";
		}
		return "삭제 실패";
		
	}
}
