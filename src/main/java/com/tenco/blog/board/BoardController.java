package com.tenco.blog.board;



import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller // IoC 대상 - 싱글톤 패턴으로 관리 됨
public class BoardController {
    private final BoardPersistRepository br;

    //일정 화면 연결 처리
    // 1. index.mustache 파일을 반환 시키는 기능을 만든다.
    // 주소 설계 : http://localhost:8080/, http://localhost:8080/index
    @GetMapping({"/","/index"})
    public String boardList(HttpServletRequest request){

        List<Board> boardList = br.findAll();


        request.setAttribute("boardList",boardList);

        return "index";
    }


    // 게시글 작성 화면 요청 처리
    @GetMapping("/board/save-form")
    public String saveForm(){
        return "board/save-form";
    }


    // 게시글 작성 액션 처리
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO){
        // HTTP 요청 본문 : title=값&content=값&username=값
        // TODO form MIME ( application/x-www-form-urlencoded) // 무조건 외우기

        //reqDTO <--- 사용자가 던진 데이터가 전부 잇는상태
        // DTO를 받아서 Board -- DB
        //Board board = new Board(reqDTO.getTitle(), reqDTO.getContent(),reqDTO.getUsername());
        Board board = reqDTO.toEntity();
        br.save(board);
        return "redirect:/";
    }




}
