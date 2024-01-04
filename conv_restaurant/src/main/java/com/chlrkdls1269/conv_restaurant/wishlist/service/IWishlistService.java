package com.chlrkdls1269.conv_restaurant.wishlist.service;

import java.util.List;

import com.chlrkdls1269.conv_restaurant.board.model.Board;
import com.chlrkdls1269.conv_restaurant.wishlist.model.Wishlist;

public interface IWishlistService {
	void insertWishlist(String memberId, int boardId);
	List<Board> searchAllWishlist(String memberId);
	void deleteWishlist(String memberId, int wishlistId);
}
