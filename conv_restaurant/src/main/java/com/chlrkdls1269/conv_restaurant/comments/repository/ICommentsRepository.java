package com.chlrkdls1269.conv_restaurant.comments.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.chlrkdls1269.conv_restaurant.comments.model.Comments;

@Repository
@Mapper
public interface ICommentsRepository {
	List<Comments> searchAllComments(@Param("boardId") int boardId);
	void insertComments(Comments comments);
	void updateComments(@Param("comments") Comments comments, @Param("memberId") String memberId, @Param("boardId") int boardId, @Param("commentsId") int commentsId);
	void deleteComments(@Param("memberId") String memberId, @Param("boardId") int boardId, @Param("commentsId") int commentsId);
}
