package com.chlrkdls1269.conv_restaurant.comments.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chlrkdls1269.conv_restaurant.comments.model.Comments;
import com.chlrkdls1269.conv_restaurant.comments.service.ICommentsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/comments")
@Tag(name = "Comments", description = "게시글 - 댓글 관리 API")
public class CommentsController {
	@Autowired
	ICommentsService commentsService;
	
	@Operation(summary = "해당 게시글 댓글 전체 조회")
	@GetMapping("/search/{boardId}")
	public ResponseEntity<List<Comments>> searchAllComments(@PathVariable int boardId,
															 Principal principal){
		String username = principal.getName();
		
		if(username!=null&&!username.equals("")) {
			try {
				List<Comments> findALlCommentsByBoardId = commentsService.searchAllComments(boardId);
				return ResponseEntity.ok(findALlCommentsByBoardId);
			}catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			
		}
		else {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@Operation(summary = "댓글 등록")
	@PostMapping("/insert/{boardId}")
	public ResponseEntity<String> insertComments(@PathVariable int boardId,
												 @RequestBody Comments comments,
												  Principal principal){
		String username = principal.getName();
		
		if(username!=null&&!username.equals("")) {
			try {
				comments.setBoardId(boardId);
				comments.setMemberId(username);
				commentsService.insertComments(comments);
				
				String responseOk = "(댓글 등록) API response Ok";
				
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
	
	@Operation(summary = "댓글 수정")
	@PostMapping("/update/{boardId}/{commentsId}")
	public ResponseEntity<String> updateComments(@PathVariable int boardId,
												 @PathVariable int commentsId,
												 @RequestBody Comments comments,
												  Principal principal){
		String username = principal.getName();
		
		if(username!=null&&!username.equals("")) {
			try {
				commentsService.updateComments(comments, username, boardId, commentsId);
				
				String responseOk = "(댓글 수정) API response Ok";
				
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
	
	@Operation(summary = "댓글 삭제")
	@PostMapping("/delete/{boardId}/{commentsId}")
	public ResponseEntity<String> deleteComments(@PathVariable int boardId,
												 @PathVariable int commentsId,
												  Principal principal){
		String username = principal.getName();
		
		if(username!=null&&!username.equals("")) {
			try {
				commentsService.deleteComments(username, boardId, commentsId); 
				
				String responseOk = "(댓글 삭제) API response Ok";
				
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