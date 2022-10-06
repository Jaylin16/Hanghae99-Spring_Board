package com.example.board.entity;

import com.example.board.dto.BoardDetailDto;
import com.example.board.dto.BoardRequestDto;
import com.example.board.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity

public class Board extends Timestamped { //생성, 수정 시간 자동 생성)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    //ID값 자동 생성. id값 long 타입으로 지정.

    @Column(nullable = false)
    private String title;
    //제목 입력값(title)이 null(공란)일 수 없음. title 타입 string 지정.

    @Column(nullable = false)
    private String username;
    //username은 null(공란)일 수 없음. username 타입 string 지정.

    @Column(nullable = false)
    private String contents;
    //본문 입력값(contents)가 null(공란)일 수 없음. contents 타입 string 지정.

    @Column(nullable = false)
    private String password;
    //패스워드 입력값(password)이 null(공란)일 수 없음. password 타입 string 지정.


//    public Board(String title, String username, String contents, String password) {
//        this.title = title;
//        this.username = username;
//        this.contents = contents;
//        this.password = password;
//    }
    //Board에 대한 생성자 생성.

    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }
    //생성자에 넘겨줄 데이터를 Dto에서 받아옴.


    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }
    //Dto에 데이터 업데이트 해주는 기능(메소드) 생성.



}
