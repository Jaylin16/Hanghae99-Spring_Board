package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.dto.BoardDetailDto;
import com.example.board.dto.BoardListDto;
import com.example.board.dto.BoardRequestDto;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;
    //BoardRepository에 쌓여있는 데이터와 BoardService를 통해 요청받은 사항을 불변값으로 받아옴.

    @PostMapping("/api/boards") //create
    public String createBoard(BoardRequestDto requestDto) {
        System.out.println("1111");
        Board board = new Board(requestDto);
        boardRepository.save(board);

        System.out.println("22222");
        return "redirect:/";
    }
    //post요청을 통해 /boards 경로에서 받아온 데이터를 Dto에 새로 생성 및 저장해줌.
    //그리고 Dto에 저장된 board 값을 Repository로 옮김.

    @GetMapping("/api/boards") //read (게시글 작성쪽 양식 가져오는 get요청)
    public String create() {

        return "create";
    }
    //get요청을 통해 /boards 경로에 boardRepository에 있는 내용 중 필요 내용을 service에서 가공하여 result로 받아옴.
    //이 때 repository내용을 모두 불러와서 controller가 (html이 있으면 그곳에 보내서) 보여주게 되는건지......?????????


    @GetMapping("/api/boards/{id}") //read
    public BoardDetailDto boardDetailDtos(@PathVariable Long id) {
        return boardService.boardDetailDtos(id);
    }

    @PutMapping("/api/boards/{id}") //put
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) throws Exception {
        boardService.update(id, requestDto);
        return id;
    }// service쪽에서 비밀번호 불일치한 데이터가 controller쪽으로 쫒겨나고 그 데이터를 또 다시 exception으로 쫒아냄.
    //그럼 비밀번호가 불일치한 데이터는 Controller의 밖. 즉, spring으로 쫒겨나기 때문에 그 뒤는 spring이 알아서 처리함.

    @DeleteMapping("/api/boards/{id}") //delete
    public Long deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return id;
    }
    //id값을 기준으로 repository에 저장되어있는 값을 찾아 삭제하고 완료 후 id값 리턴.
    //여기서 url의 {id}는 @PathVariable annotation을 사용해서 바꿔주는 중.

}
