package com.chlrkdls1269.conv_restaurant.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chlrkdls1269.conv_restaurant.board.model.Board;
import com.chlrkdls1269.conv_restaurant.board.repository.IBoardRepository;

@Service
public class BoardService implements IBoardService{
	@Autowired
	IBoardRepository boardRepository;
	
	@Override
	public List<Board> searchAllBoard() {
		return boardRepository.searchAllBoard();
	}
	
	@Override
	public List<Board> searchBoard(String memberId) {
		return boardRepository.searchBoard(memberId);
	}
	
	@Override
	public List<Board> searchByCategoryId(int categoryId) {
		return boardRepository.searchByCategoryId(categoryId);
	}

	@Override
	public void insertBoard(Board board) {
		boardRepository.insertBoard(board);
	}

	@Override
	public void updateBoard(Board board, int boardId, String username) {
		boardRepository.updateBoard(board, boardId, username);
	}

	@Override
	public void deleteBoard(int boardId, String username) {
		boardRepository.deleteBoard(boardId, username);
	}

	@Override
	public void likeBoard(Board board, int boardId) {
		boardRepository.likeBoard(board, boardId);
	}

	@Override
	public Board findById(int boardId) {
		return boardRepository.findById(boardId);
	}
}
