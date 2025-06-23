package com.tenco.blog.board;


import jakarta.servlet.http.HttpServletRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {

    @Autowired
    private BoardPersistRepository br;

    @Test
    public void deletById_Test() {
        //given
        Long id = 1L;

        //when
        // 삭제할 게시글이 실제로 존재하는지 확인
        Board targetBoard = br.findById(id);
        Assertions.assertThat(targetBoard).isNotNull();

        // 영속성 컨텍스트에서 삭제 실행
        br.deleteById(id);

        //then
        List<Board> afterDeleteBoardList = br.findAll();
        Assertions.assertThat(afterDeleteBoardList.size()).isEqualTo(3);
    }

    //단위 게시글 한건 테스트 코드 작성
    @Test
    public void findById(){
        //given
        Long id = 1l;

        //when
        Board board = br.findById(id);

        //than
        Assertions.assertThat(board.getUsername()).isEqualTo("ssar");
        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");

    }

    @Test
    public void findAll_test() {
        // given
        // db/data.sql (4개의 더미 데이터)


        // when
        List<Board> boardList = br.findAll();

        // 게시글 한건 조회 말들기
        //em.find(),JPQL, 네이트브 쿼리


        // then
        System.out.println("size 테스트 : " + boardList.size());
        System.out.println("첫번째 게시글 제목 확인 : " + boardList.get(0).getTitle());

        // 네이티브 쿼리를 사용한다는 것은 영속 컨텍스트를 우회 해서 일 처리
        // JPQL 바로 영속성 컨텍스를 우회하지만 조회된 이후에는 영속성 상태가 된다.
        for(Board board: boardList) {
            Assertions.assertThat(board.getId()).isNotNull();
        }
    }


    @Test
    public void save_test() {
        // given
        Board board = new Board("제목555", "내용555", "승민군");

        // 저장 전 객체의 상태값 확인
        Assertions.assertThat(board.getId()).isNull();
        System.out.println("db에 저장 전 board : " + board);

        // when
        // 영속성 컨텍스트를 통한 엔티티 저장
        Board savedBoard = br.save(board);

        // then
        // 1. 저장 후에 자동 생성된 ID 확인
        Assertions.assertThat(savedBoard.getId()).isNotNull();
        Assertions.assertThat(savedBoard.getId()).isGreaterThan(0);

        // 2. 내용 일치 여부 확인
        Assertions.assertThat(savedBoard.getTitle()).isEqualTo("제목555");

        // 3. JPA가 자동으로 생성된 생성 시간 확인
        Assertions.assertThat(savedBoard.getCreatedAt()).isNotNull();

        // 4. 원본 객체 - board , 영속성 컨텍스에 저장한 - savedBoard
        Assertions.assertThat(board).isSameAs(savedBoard);

    }

    @Test
    public void update_test() {
        long id = 1L;
        Board board = br.findById(id);

        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");

        board.setTitle("안녕반가워");
        br.update(board);

        Assertions.assertThat(board.getTitle()).isEqualTo("안녕반가워");
    }
}
