package com.tenco.blog.board;

import lombok.Data;

/**
 * 클라이언트에게 넘어온 데이터를
 * Object로 변환해서 전달하는 DTO 역활을 담당한다.
 */
public class BoardRequest {

    // 정적 내부 클래스롤 기능별로 DTO 관리
    // 게시글 저장 요청 데이터 담는 역활
    // BoardRequest.SaveDTO 변수명
    @Data
    public static class SaveDTO{
        private String title;
        private String content;
        private String username;

        // DTO에서 Entity로 변환하는 메서드 만들기
        // 계층간 데이터를 명확하게 분리하기 위해
        public Board toEntity(){
            return new Board(title,content,username);
        }

    } // end of saveDTO

}
