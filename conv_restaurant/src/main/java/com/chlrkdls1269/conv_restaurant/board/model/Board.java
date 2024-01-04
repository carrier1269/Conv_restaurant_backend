package com.chlrkdls1269.conv_restaurant.board.model;

import lombok.Data;

@Data
public class Board {
	// 편의점 게시판 ID
	private int boardId;
	
	// 게시글 제목
	private String boardName;
	
	// 게시글 내용
	private String boardContent;
	
	// 조리 시간
	private String boardCookingtime;
	
	// 조리 난이도
	private int boardCookinglevel;
	
	// 가격
	private int boardTotalprice;
	
	// 좋아요 개수
	private int boardLikecount;
	
	// 게시글 이미지
	private byte[] boardImage;
	
	// 회원 ID
	private String memberId;
	
	// 편의점 상품 ID
	private int productId;
	
	// 상품 카테고리 ID
	private int categoryId;
}
