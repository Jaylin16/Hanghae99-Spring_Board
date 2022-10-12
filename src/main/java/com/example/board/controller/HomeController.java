package com.example.board.controller;

import com.example.board.dto.BoardListDto;
import com.example.board.entity.model.UserRoleEnum;
import com.example.board.security.UserDetailsImpl;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;



@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;
    //BoardRepository에 쌓여있는 데이터와 BoardService를 통해 요청받은 사항을 불변값으로 받아옴.

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());

//        if (userDetails.getUser().getRole() == UserRoleEnum.ADMIN) {
//            model.addAttribute("admin_role", true);
//        }

        List<BoardListDto> boardListDtos = boardService.boardListDtos();
        model.addAttribute("boardList",boardListDtos);
        System.out.println("boardListDtos = " + boardListDtos);

        return "index";
    }
}
