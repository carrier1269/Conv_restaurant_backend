package com.chlrkdls1269.conv_restaurant.comments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chlrkdls1269.conv_restaurant.comments.model.Comments;
import com.chlrkdls1269.conv_restaurant.comments.repository.ICommentsRepository;

@Service
public class CommentsService implements ICommentsService{
	@Autowired
	ICommentsRepository commentsRepository;
	
	@Override
	public List<Comments> searchAllComments(int boardId) {
		return commentsRepository.searchAllComments(boardId);
	}

	@Override
	public void insertComments(Comments comments) {
		commentsRepository.insertComments(comments);
	}

	@Override
	public void updateComments(Comments comments,String memberId, int boardId, int commentsId) {
		commentsRepository.updateComments(comments, memberId, boardId, commentsId);
	}

	@Override
	public void deleteComments(String memberId, int boardId, int commentsId) {
		commentsRepository.deleteComments(memberId, boardId, commentsId);
	}
}
