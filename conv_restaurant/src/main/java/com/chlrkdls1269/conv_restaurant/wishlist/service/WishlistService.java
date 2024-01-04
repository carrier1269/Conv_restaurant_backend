package com.chlrkdls1269.conv_restaurant.wishlist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chlrkdls1269.conv_restaurant.board.model.Board;
import com.chlrkdls1269.conv_restaurant.wishlist.model.Wishlist;
import com.chlrkdls1269.conv_restaurant.wishlist.repository.IWishlistRepository;

@Service
public class WishlistService implements IWishlistService{
	@Autowired
	IWishlistRepository wishlistRepository;
	
	@Override
	public void insertWishlist(String memberId, int boardId) {
		wishlistRepository.insertWishlist(memberId, boardId);
	}

	@Override
	public List<Board> searchAllWishlist(String memberId) {
		List<Integer> boardIdList = wishlistRepository.getBoardIdByMemberId(memberId);
		
		System.out.println(boardIdList);
		
		List<Board> boardList = new ArrayList<>();
		
		for (int boardId : boardIdList) {
			Board wishlistFindByBoardId = wishlistRepository.getBoardByBoardId(boardId);
			boardList.add(wishlistFindByBoardId);
		}
		return boardList;
	}

	@Override
	public void deleteWishlist(String memberId, int wishlistId) {
		wishlistRepository.deleteWishlist(memberId, wishlistId);
	}

}
