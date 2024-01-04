package com.chlrkdls1269.conv_restaurant.wishlist.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.chlrkdls1269.conv_restaurant.board.model.Board;

@Repository
@Mapper
public interface IWishlistRepository {
	void insertWishlist(@Param("memberId") String memberId, @Param("boardId") int boardId);

	
	List<Integer> getBoardIdByMemberId(String memberId);
	Board getBoardByBoardId(int boardId);
	
	void deleteWishlist(@Param("memberId") String memberId, @Param("wishlistId") int wishlistId);
}
