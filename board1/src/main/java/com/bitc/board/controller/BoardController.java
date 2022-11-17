package com.bitc.board.controller;

import com.bitc.board.dto.BoardDto;
import com.bitc.board.dto.BoardFileDto;
import com.bitc.board.service.BoardService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.List;
//@Controller : 사용자가 웹브라우저를 통하여 어떠한 요청을 할 경우 해당 요청을 처리하기 위한 비즈니스 로직을 가지고 있는 이노테이션,
//클래스에 해당 어노테이션을 사용하면 해당 클래스는 사용자 요청을 처리하기 위한 클래스라는 것을 스프링 프레임워크에 알림
//컨트롤러가 하는 일
//1. 사용자가 서버에 요청한 주소를 기반으로 사용자가 전송한 데이터를 받음.
//2. 사용자에게 제공할 view 파일은 연동(html)
//3. 사용자가 전송한 데이터를 바탕으로 서비스에게 내부 연산을 요청한다.
@Controller
public class BoardController {
//사용자가 치면 무조건 컨트롤러로 먼저 온다.
    // @Autowired : 사용자가 해당 타입의 객체를 생성하는 것이 아니라 스프링 프레임워크가 해당 타입의 객체를 생성하고 사용자는
//    이용만 하도록 하는 어노테이션. 어느 순간 생성되고 삭제되는지 알 필요 없다.
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

    //  게시물 목록 페이지
    @RequestMapping(value = "/board/openBoardList", method = RequestMethod.GET)
    public ModelAndView openBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("board/boardList"); //view는 무조건 templets밑에 있다. board라는
//        폴더 안에 있는 board/boardList와 /board/openBoardList.do을 연결시켜준다.

        List<BoardDto> dataList = boardService.selectBoardList();
//        service와 orm은 둘 다 인터페이스다.
//        마이바티스.
        mv.addObject("dataList", dataList);

//        BoardDto b = new BoardDto();

//        서비스에서 넘겨받은 걸 modelAndView로 준다.
        return mv;
    }


//      게시물 상세보기 페이지
//    @RequestParam : jsp의 request.getParameter()와 같은 기능을 하는 어노테이션, 클라이언트에서 서버로
//    전송된 데이터를 가져오는 어노테이션. (@RequestParam(value = "idx") int idx)이게 원칙이긴 한데 하나밖에 없다면 int idx만 사용해도 무관.
    @RequestMapping("/board/openBoardDetail") //실제 웹 사이트 주소 앞에 무조건 "/" 붙여줘야한다.
    public ModelAndView openBoardDetail(@RequestParam int idx) throws Exception {
        ModelAndView mv = new ModelAndView("board/boardDetail");
//        처음 만들 때 모델앤뷰 파일 생성됨.
        //ModelAndView: html view 주소. 앞에/ 붙이면 리눅스에서 인식 잘 못함. 윈도우에서는 무관.

//        view를 사용할려면 따로 객체 만들어줘야 한다.
        mv.setViewName("board/boardDetail");
        BoardDto board = boardService.selectBoardDetail(idx);
        mv.addObject("board", board);

        return mv;
    }
//       게시글 등록 view 페이지
    @RequestMapping("/board/boardWrite")
    public String boardWrite() throws Exception {
        return "board/boardWrite";
    }
    
//   아래 부분소스코드 추가됨!!!!(...아닌가)
    
//    게시글 등록
//    클라이언트에서 업로드된 파일 데이터를 받기 위해서 매개변수로 MultipartHttpServletRequest를 추가
    @RequestMapping("/board/insertBoard")
    public String insertBoard(BoardDto board, MultipartHttpServletRequest multipart) throws Exception {
//        업로드된 파일 데이터를 서비스 영역에서 처리하기 위해서 매개변수를 추가
        boardService.insertBoard(board, multipart);
//        BoardDto: 데이터 타입 지정받음.

        return "redirect:/board/openBoardList";
    }
//    게시글 수정
    @RequestMapping("/board/updateBoard")
    public String updateBoard(BoardDto board) throws Exception {
        boardService.updateBoard(board);

        return "redirect:/board/openBoardList";
    }

//    @RequestMapping("/board/deleteBoard")
//    public String deleteBoard(BoardDto board) throws Exception {
//        boardService.deleteBoard(idx);
//
//        return "redirect:/board/openBoardList";
//    }
    
//    게시글 삭제
    @RequestMapping("/board/deleteBoard")
    public String deleteBoard(@RequestParam int idx) throws Exception {
        boardService.deleteBoard(idx);
        return "redirect:/board/openBoardList";
    }

    @RequestMapping("/board/downloadBoardFile")
    public void downloadFile(@RequestParam int idx, @RequestParam int boardIdx, HttpServletResponse response)throws
        Exception {
        BoardFileDto boardFile = boardService.selectBoardFileInfo(idx, boardIdx);

        if(ObjectUtils.isEmpty(boardFile) == false) {
            String fileName = boardFile.getOriginalFileName();

            byte[] files = FileUtils.readFileToByteArray(new File(boardFile.getStoredFilePath()));

            response.setContentType("application/octet-stream");
            response.setContentLength(files.length);
            response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName,
                    "UTF-8") + "\";");
            response.getOutputStream().write(files);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }
}













