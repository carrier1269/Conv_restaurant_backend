package com.chlrkdls1269.conv_restaurant.board.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chlrkdls1269.conv_restaurant.board.model.Board;
import com.chlrkdls1269.conv_restaurant.board.repository.IBoardRepository;
import com.chlrkdls1269.conv_restaurant.board.service.IBoardService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/board")
@Slf4j
public class BoardController {
	@Autowired
	IBoardService boardService;
	
	@Operation(summary = "전체 게시글 조회")
	@GetMapping(value = "/search/all")
	public ResponseEntity<List<Board>> searchAllBoard(Principal principal){
		List<Board> allBoard = boardService.searchAllBoard();
		
        return ResponseEntity.ok(allBoard);
	}
	
	@Operation(summary = "회원별 게시글 조회")
	@GetMapping(value = "/search/{memberId}")
	public ResponseEntity<List<Board>> searchBoard(@PathVariable String memberId, Principal principal){
		// principal에서 꺼내도댐 노상관
		List<Board> userBoard = boardService.searchBoard(memberId);
		return ResponseEntity.ok(userBoard);
	}
	
	@Operation(summary = "게시글 등록")
	@PostMapping(value = "/insert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> insertBoard(@RequestPart("board") Board board, @RequestPart("file") MultipartFile file, Principal principal) throws IOException{
		
		
		board.setBoardImage(file.getBytes());
		
		log.info("input board : {}", board);
		log.info("multipartfile : {}", file.getOriginalFilename());
		
		boardService.insertBoard(board);

		String responseOk = "(게시글 등록) API response Ok";
		
		return ResponseEntity.ok(responseOk);
	}
	
	@Operation(summary = "게시글 수정")
	@PostMapping(value = "/update/{boardId}")
	public ResponseEntity<String> updateBoard(@RequestBody Board board, @PathVariable int boardId, Principal principal){
		String username = "1"; // 임시 유저 번호
		
		log.info("board : {}" , board);
		
		boardService.updateBoard(board, boardId, username);
		
		String responseOk = "(게시글 수정) API response Ok";
		
		return ResponseEntity.ok(responseOk);
	}
	
	
	@Operation(summary = "게시글 삭제")
	@PostMapping(value = "/delete/{boardId}")
	public ResponseEntity<String> deleteBoard(@PathVariable int boardId, Principal principal){
//		String username = principal.getName();
		String username = "1"; // 임시 유저 번호
		
		boardService.deleteBoard(boardId, username);

		String responseOk = "(게시글 삭제) API response Ok";
		
		return ResponseEntity.ok(responseOk);
	}
	
	@Operation(summary = "게시글 좋아요")
	@GetMapping(value = "/like/{boardId}")
	public ResponseEntity<String> likeBoard(@PathVariable int boardId, Principal principal){
		// db에서 해당 boardId에 해당하는 데이터를 찾고 가지고옴
		// board 객체를 생성하여 
		// setLikecount ++;해서
		// db에 updatequery문을 날리는 방식으로 하자.
		
		// 시퀀스 query로도 어떻게 해결할 수 있긴할텐데
		// 난 이방법할랭 ㅋ 동적 쿼리문으로 해결
		Board board = boardService.findById(boardId);
		
	
		int likecount = board.getBoardLikecount();
		
		board.setBoardLikecount(++likecount);
		
		System.out.println(board);
		
		// null인 이유는 아무나 다 좋아요 기능을 수행할 수 있도록 하기 위해서.
		// 동적쿼리문 써서 모두 조회로 바꿔버리면 댐
		
		boardService.updateBoard(board, boardId, null);
		
		String responseOk = "(게시글 좋아요) API response Ok";
		
		return ResponseEntity.ok(responseOk);
	}
}
