package com.example.board.dto;

import com.example.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDetailDto {
    private String title;
    private String username;
    private LocalDateTime createdAt;
    private String contents;

//    public void detail(Board board) {
//        this.title = board.getTitle();
//        this.username = board.getUsername();
//        this.createdAt = board.getCreatedAt();
//        this.contents = board.getContents();
//    }
    //한개의 게시글만 가져올 때 사용하는 기능 생성.

//    public static BoardDetailDto boardDetailDto(Board board) {
//        return new BoardDetailDto(
//                board.getTitle(),
//                board.getUsername(),
//                board.getCreatedAt(),
//                board.getContents()
//        );
//    }


    public BoardDetailDto (String title, String username, LocalDateTime createdAt, String contents) {
        this.title = title;
        this.username = username;
        this.createdAt = createdAt;
        this.contents = contents;
    }//생성자 추가.

    public BoardDetailDto (Board board) {
        this.title = title;
        this.username = username;
        this.createdAt = createdAt;
        this.contents = contents;
    }//생성자 추가.

}
