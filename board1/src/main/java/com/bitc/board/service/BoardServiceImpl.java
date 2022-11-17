package com.bitc.board.service;

import com.bitc.board.common.FileUtils;
import com.bitc.board.dto.BoardDto;
import com.bitc.board.dto.BoardFileDto;
import com.bitc.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
//    Autowired: 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
    private BoardMapper boardMapper;

    @Autowired
    private FileUtils fileUtils;

    @Override
    public List<BoardDto> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }

//    추가된 파일 
    @Override
    public BoardDto selectBoardDetail(int idx) throws Exception {
        
//        조회수 증가 부분
        boardMapper.updateHitCount(idx);

//        지정한 게시물 상세정보. 현재 첨부파일 목록없음
        BoardDto board = boardMapper.selectBoardDetail(idx); // 파일 리스트로 가져온걸 board에 집어넣어줌. 원래 첨부파일리스트느ㅡㄴ
//        비워져있었지만 boardDto에 데이터를 넣어준다.

//        지정한 게시물의 첨부 파일 목록 가져오기
        List<BoardFileDto> fileList = boardMapper.selectBoardFileList(idx); //리스트 가져오기

//        가져온 게시물 상세정보에 가져온 첨부파일 목록을 추가함
        board.setFileList(fileList);

//        지정한 게시물 상세 정보
//        완성된 게시물 리스트를 리턴시킨다. 이거 때문에 아까 안떴음. board를 리턴시켜야 한다.
        return board;
    }


    @Override
    public void insertBoard(BoardDto board, MultipartHttpServletRequest uploadFiles) throws Exception {
//        나중에 파일 업로드 부분이 추가 되는 부분
        boardMapper.insertBoard(board); //이게 있어야 sql-board의 board의 데이터를 받아온다.
//  MAPPER => xml의 insertboard구문 실행
//        업로드된 파일 정보를 가지고 boardFileDto 클래스 타입의 리스트 생성
        List<BoardFileDto> fileList = fileUtils.parseFileInfo(board.getIdx(), uploadFiles);

//        파일 리스트가 비었는지 확인 후 데이터 베이스에 저장
        if(CollectionUtils.isEmpty(fileList) == false) {
            boardMapper.insertBoardFileList(fileList);
        }

//      이거는 파일 업로드용도가 아니라 콘솔에서 파일 업로드 확인만 하는 부분임!!
//        해당 객체가 비어있는지 확인해줌.
//        SpringFrame에서 추가된 클래스로 isEmpty()는 지정한 객체가 비었는지 아닌지 확인해줌(비었으면 true, 있으면 false)
//        if (ObjectUtils.isEmpty(multipart) == false) {
//            MultipartHttpServletRequest 클래스 타입의 변수 multipart에 저장된 파일 데이터 중 파일 이름만 모두 가져옴
//            가져와서 Iterator타입에 넣어줌.
//            Iterator<String> iterator = multipart.getFileNames();
//            파일명을 저장할 변수
//            String name;
////            iterator를 사용하게 되면 동일한 방식으로 사용하는 것이 가능해지니까 사용함.
//
////            Iterator 타입의 변수에 저장된 모든 내용을 출력할 때 까지 반복 실행. hasNext()는 데이터가 있는지 확인
//            while(iterator.hasNext()) {
//                name = iterator.next(); //실제 데이터를 가져옴(파일명이 저장됨)
////                실제 파일 데이터 가져오기. 해당 파일 이름을 기준으로 해서
//                List<MultipartFile> fileInfoList = multipart.getFiles(name);
//
//                for (MultipartFile fileInfo : fileInfoList) {
//                    System.out.println("start file info...");
//                    System.out.println("file name" + fileInfo.getOriginalFilename()); //실제 파일명
//                    System.out.println("file size" + fileInfo.getSize()); // 파일크기
//                    System.out.println("file content type" + fileInfo.getContentType()); // 파일 확장자명
//                    System.out.println("end file info...");
//                    System.out.println("------------");
//                }
//            }

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
    @Override
        public BoardFileDto selectBoardFileInfo(int idx, int boardIdx) throws Exception {
        return boardMapper.selectBoardFileInfo(idx,boardIdx);
    }
}
