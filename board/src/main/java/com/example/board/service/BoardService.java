package com.example.board.service;

import com.example.board.entity.Board;
import com.example.board.dto.BoardDetailDto;
import com.example.board.dto.BoardListDto;
import com.example.board.dto.BoardRequestDto;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    //BoardRepository class에 boardRepository 값들은 변하면 안되므로 final로 불변변수로 만들어줌.

    @Transactional
    //게시글 수정관련 처리해주는 메소드.
    public Long update(Long id, BoardRequestDto requestDto) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        ); //id를 key값으로 boardRepository에 있는 내용을 찾아오고 없을경우 오류메세지 보냄. ok.

        if (!requestDto.getPassword().equals(board.getPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        } //exception으로 false일 경우 아예 이 위치 밖으로 내보냄. 즉, service에서 쫒겨나서 Controller로 감.
        board.update(requestDto);
        return board.getId();
        //id가 있을 경우 Dto에 게시글 내용을 추가해주고 id값을 보여주어 완료 여부를 알려준다.
    }


    //게시글 전체 리스트 조회 처리해주는 메소드.
    public List<BoardListDto> boardListDtos() {
        List<Board> boards = boardRepository.findAllByOrderByModifiedAtDesc();

        List<BoardListDto> result = new ArrayList<>();
        for (Board board : boards) { // Board class의 board 한줄씩을 boards에서 하나씩 빼서 본다.
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd a HH:mm:ss");

//            BoardListDto boardListDto = new BoardListDto(board.getTitle(), board.getUsername(), board.getCreatedAt());

            BoardListDto boardListDto = new BoardListDto(board.getTitle(), board.getUsername(), board.getCreatedAt());

            result.add(boardListDto);
        }

        return result;
    } // boardRepository에 있는 전체 데이터를 가지고 와서 원하는 조건으로 for문 통해 result라는 새로 생성된 list 안에 추가해준다.
    // 그 뒤 해당 result list를 boardListDtos 의 결과값으로 리턴해준다.


      //게시글 상세 내역 조회해주는 메소드.
    public BoardDetailDto boardDetailDtos(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        Optional <Board> aaa = boardRepository.findById(id);
        //board or null 값이 optional이라는 공간에 들어감.

//        Board board = aaa.orElse(null);

        BoardDetailDto boardDetailDto = new BoardDetailDto(board.getTitle(), board.getUsername(), board.getCreatedAt(), board.getContents());

        return boardDetailDto;
    }

//가능은 하지만 안좋은 코드 (게시글 1개 조회)
//    public List<BoardDetailDto> boardDetailDtos(Long id) {
//        List<Board> boards = boardRepository.findAllByOrderByModifiedAtDesc();
//
//        List<BoardDetailDto> result = new ArrayList<>();
//        for (Board board : boards) {
//            if (board.getId().equals(id)) {
//
//                BoardDetailDto boardDetailDto = new BoardDetailDto(board.getTitle(), board.getUsername(), board.getCreatedAt(), board.getContents());
//
//                result.add(boardDetailDto);
//            }
//        }
//        return result;
//    }


}
