package com.example.board.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String username;
    private String contents;
    private String password;
}
//Board 쪽에서 get하므로 Dto쪽에서 Getter로 어떤 값을 주는지 정의.