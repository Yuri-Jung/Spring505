package com.bitc.board.mapper;

import com.bitc.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
// 옛날 이름이 ibatis였다.
import java.util.List;

//@Mapper : mybatis orm을 사용하여 xml 파일과 연동된 인터페이스임을 알려주는 어노테이션

//mapper 파일이 하는 일
//1. mapper 파일은 db의 데이터 객체와 자바의 데이터 객체의 형태가 다르기 때문에 데이터를 변환
//2. 자바에서 db에 명령을 보낼 수 있는 형태의 메서드를 제공. 자바에서도 그렇기 때문에 statement, preparedstatement객체를 사용했다
// 숫자 mysql  int. 자바에서 int
// 문자 mysql char/varchar 자바 string
// 날짜 mysql datetime 자바 다양한 방법있다.
@Mapper
public interface BoardMapper {
//    이렇게 하면 메서드를 보낼 수 있고 사용할 수 있다.
    List<BoardDto> selectBoardList() throws Exception;
//    여기서 서비스로 넘겨주면

    BoardDto selectBoardDetail(int idx) throws Exception;
//    이름바꿔 사용하고 싶다면 @Param("board_idx")


    void insertBoard(BoardDto board);

    void updateBoard(BoardDto board);

    void deleteBoard(int idx) throws Exception;

    void updateHitCount(int idx) throws Exception;
}
