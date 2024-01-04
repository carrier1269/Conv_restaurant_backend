package com.chlrkdls1269.conv_restaurant.comments.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chlrkdls1269.conv_restaurant.comments.model.Comments;

public interface ICommentsService {
	List<Comments> searchAllComments(int boardId);
	void insertComments(Comments comments);
	void updateComments(Comments comments, String memberId, int boardId, int commentsId);
	void deleteComments(String memberId, int boardId, int commentsId);
}
