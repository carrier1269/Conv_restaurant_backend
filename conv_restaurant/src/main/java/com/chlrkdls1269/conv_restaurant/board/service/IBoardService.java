package com.chlrkdls1269.conv_restaurant.board.service;

import java.util.List;

import com.chlrkdls1269.conv_restaurant.board.model.Board;

public interface IBoardService {
	List<Board> searchAllBoard();
	List<Board> searchBoard(String memberId);
	List<Board> searchByCategoryId(int categoryId);
	void insertBoard(Board board);
	void updateBoard(Board board, int boardId, String username);
	void deleteBoard(int boardId, String username);
	void likeBoard(Board board, int boardId);
	
	// 좋아요 기능을 위한 메서드
	Board findById(int boardId);
}
