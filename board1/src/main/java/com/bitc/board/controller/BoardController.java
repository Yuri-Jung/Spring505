package com.bitc.board.controller;

import com.bitc.board.dto.BoardDto;
import com.bitc.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
//@Controller : 사용자가 웹브라우저를 통하여 어떠한 요청을 할 경우 해당 요청을 처리하기 위한 비즈니스 로직을 가지고 있는 이노테이션,
//클래스에 해당 어노테이션을 사용하면 해당 클래스는 사용자 요청을 처리하기 위한 클래스라는 것을 스프링 프레임워크에 알림
@Controller
public class BoardController {

// @Autowired : 사용자가 해당 타입의 객체를 생성하는 것이 아니라 스프링 프레임워크가 해당 타입의 객체를 생성하고 사용자는
//    이용만 하도록 하는 어노테이션 어느 순간 생성되고 삭제되는지 알 필요 없다.
    @Autowired
    private BoardService boardService;

//    @RequestMapping : 사용자가 접속하는 웹 브라우저를 통해 접속하는 실제 주소와 메서드를 매칭하기 위한 어노테이션
//    value 속성 : 사용자가 접속할 주소 설정, 두 개 이상의 주소를 하나의 메서드와 연결하려면 {주소1, 주소2, ...}형태로 사용,
//    value 속성만 사용할 경우 생략 가능 원래는 value= "/board/~~"
//    method 속성 : 클라이언트에서 서버로 요청 시 사용하는 통신 방식을 설정하는 속성, (get/post), Restful 방식을 사용할 경우
//    RequestMethod 타입을 사용, Restful방식을 사용할 경우 get/post/update/delete를 사용할 수 있음
//    아래에 예시 넣어놓음. 기본값은 get 방식이다.
    @RequestMapping("/")
    public String index() throws Exception {
        return "index"; //"index"는 템플릿 밑의 파일 이름. 확인해야함. 동일해야한다.
    }

    @RequestMapping(value = "/board/openBoardList.do", method = RequestMethod.GET)
    public ModelAndView openBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("board/boardList"); //view는 무조건 templets밑에 있다. board라는
//        폴더 안에 있는 board/boardList와 /board/openBoardList.do을 연결시켜준다.

        List<BoardDto> dataList = boardService.selectBoardList();
//        service와 orm은 둘 다 인터페이스다.
//        마이바티스.
        mv.addObject("dataList", dataList);

//        BoardDto b = new BoardDto();

        return mv;
    }
}
