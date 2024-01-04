package com.chlrkdls1269.conv_restaurant.wishlist.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chlrkdls1269.conv_restaurant.board.model.Board;
import com.chlrkdls1269.conv_restaurant.wishlist.service.IWishlistService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/wishlist")
@Tag(name = "Wishlist", description = "위시리스트 관리 API")
public class WishlistController {
	@Autowired
	IWishlistService wishlistService;
	
	@Operation(summary = "찜 등록")
	@PostMapping("/insert/{memberId}/{boardId}")
	public ResponseEntity<String> insertWishlist(@PathVariable String memberId,
												 @PathVariable int boardId,
												  Principal principal){
		String username = principal.getName();
		
		if(username!=null&&!username.equals("")) {
			try {
				// DB wishlist 테이블에 memberId, boardId unique 제약 걸어서
				// 둘이 같은 데이터가 이미 있을경우
				// 에러반환하도록 만들어서
				// 같은 찜이 두번 발생하지 않는 로직을 설정하였음.
				wishlistService.insertWishlist(username, boardId);
				
				String responseOk = "(찜 등록) API response Ok";
				
				return ResponseEntity.ok(responseOk);
			}catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("데이터 에러");
			}
		}
		else {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("접속 유저 확인 에러");
		}
	}
	
	@Operation(summary = "찜 전체 조회")
	@GetMapping("/totalsearch")
	public ResponseEntity<List<Board>> totalSearchWishlist(Principal principal){
		String username = principal.getName();
		
		if(username!=null&&!username.equals("")) {
			try {
				// 회원이 찜한거 찾는거
				// wishlistmapper에서 사용자(memberid)가 추가한 boardid만 
				// 가져오는거 만들고
				// 반환으로 String boardid걸어서
				// boardMapper에서
				// find byID ->  boardid걸어버리면 될듯
				
				// 지금은 pathvariable로 했지만 나중에는 그냥 principal에서 꺼내면댐
				List<Board> userWishlist = wishlistService.searchAllWishlist(username);
				
				return ResponseEntity.ok(userWishlist);
			}catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
		}
		else {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@Operation(summary = "찜 삭제")
	@PostMapping("/delete/{wishlistId}")
	public ResponseEntity<String> deleteWishlist(@PathVariable int wishlistId,
												  Principal principal){
		String username = principal.getName();
		
		if(username!=null&&!username.equals("")) {
			try {
				wishlistService.deleteWishlist(username, wishlistId);
				
				String responseOk = "(찜 삭제) API response Ok";
				
				return ResponseEntity.ok(responseOk);
			}catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("데이터 에러");
			}
		}
		else {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("접속 유저 확인 에러");
		}
	}
}
