package com.bitc.board.service;

import com.bitc.board.dto.BoardDto;
import com.bitc.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service : 해당 파일이 서비스 Interface 파일을 구현하는 구현체라는 것을 알려주는 어노테이션
// 서비스가 하는 일
// 1. 컨트롤러에서 전달받은 데이터를 기반으로 연산을 진행 (계산기에서 '='. 키보드로 치면 메모리에서 저장해줌.이걸 컨트롤러가 해줌
// 연산을 하는 건 cpu가 하지만 여기서는 서비스가 해준다. mapper를 통해 db로 가고)
// 2. orm을 통해서 db에 접근.
// 3. orm을 통해서 가져온 데이터를 가공
// 4. 컨트롤러로 가공된 데이터를 다시 전달

    @Service
    public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardDto> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }

    @Override
    public BoardDto selectBoardDetail(int idx) throws Exception {
        boardMapper.updateHitCount(idx);
        return boardMapper.selectBoardDetail(idx);
    }

    @Override
    public void insertBoard(BoardDto board) throws Exception {
//        나중에 파일 업로드 부분이 추가 되는 부분
        boardMapper.insertBoard(board);
    }

    @Override
    public void updateBoard(BoardDto board) throws Exception {
        boardMapper.updateBoard(board);
    }

    @Override
    public void deleteBoard(int idx) throws Exception {
        boardMapper.deleteBoard(idx);
//        해당 글번호를 가지고 삭제 진행한다.
    }
}
