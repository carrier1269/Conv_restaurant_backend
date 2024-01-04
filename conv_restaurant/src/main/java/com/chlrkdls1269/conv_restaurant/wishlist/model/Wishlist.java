package com.chlrkdls1269.conv_restaurant.wishlist.model;

import lombok.Data;

@Data
public class Wishlist {
	// 찜한 목록 ID
	private int wishlistId;
	
	// 회원 ID
	private String memberId;
	
	// 편의점 게시판 ID
	private int boardId;
}
