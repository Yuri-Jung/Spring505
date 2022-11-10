package com.bitc.board.service;

import com.bitc.board.dto.BoardDto;

import java.util.List;

public interface BoardService {
//    인터페이스는 구현체 필요하다.
    List<BoardDto> selectBoardList() throws Exception;
//    받은거를 서비스에서 컨트롤러로 넘겨줌.

    BoardDto selectBoardDetail(int idx) throws Exception;

    void insertBoard(BoardDto board) throws Exception ;

    void updateBoard(BoardDto board) throws Exception;

    void deleteBoard(int idx) throws Exception;
}
