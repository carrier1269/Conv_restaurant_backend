package com.chlrkdls1269.conv_restaurant.comments.model;

import lombok.Data;

@Data
public class Comments {
	// 댓글 ID
	private int commentsId;
	
	// 댓글 내용
	private String commentsContent;
	
	// 편의점 게시판 ID
	private int boardId;
	
	// 회원 ID
	private String memberId;
}
