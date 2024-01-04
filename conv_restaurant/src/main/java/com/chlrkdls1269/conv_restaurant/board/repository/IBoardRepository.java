package com.chlrkdls1269.conv_restaurant.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.chlrkdls1269.conv_restaurant.board.model.Board;

@Repository
@Mapper
public interface IBoardRepository {
	List<Board> searchAllBoard();
	List<Board> searchBoard(String memberId);
	List<Board> searchByCategoryId(int categoryId);
	void insertBoard(Board board);
	void updateBoard(@Param("board") Board board, @Param("boardId") int boardId, @Param("username") String username);
	void deleteBoard(@Param("boardId") int boardId, @Param("username") String username);
	void likeBoard(Board board, int boardId);
	
	// 좋아요 기능을 위한 메서드
	Board findById(int boardId);
}
