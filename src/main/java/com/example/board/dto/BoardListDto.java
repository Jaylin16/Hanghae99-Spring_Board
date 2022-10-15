package com.example.board.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class BoardListDto {
    private String title;
    private String username;

    private LocalDateTime createdAt;

    public BoardListDto (String title, String username, LocalDateTime createdAt) {
        this.title = title;
        this.username = username;
        this.createdAt = createdAt;
    }//생성자 추가.

}


