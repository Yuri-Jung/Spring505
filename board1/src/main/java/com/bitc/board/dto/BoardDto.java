package com.bitc.board.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data : lombok 라이브러리에서 지원하는 어노테이션으로 해당 클래스의 멤버 변수에 대한 getter/setter/toString() 메서드를
// 자동으로 생성하는 어노테이션.@Getter, @Setter , @ToString 어노테이션을 모두 사용할 효과. @Data = @Getter + @Setter + @ToString
// 한번 씩 @Data만 입력하면 작동이 잘 안 될 때가 종종 있다. 그럴 때는 @Getter, @Setter , @ToString입력하면 된다.

//@Data
//이렇게 클래스를 넣는 이유 다양한 데이터 타입을 적은 용량으로 즐기기 위해서

//@DTO(Data Transfer Objet)
//데이터 전송 시 사용하기 위한 JAVA class 객체, db의 table과 매칭하는데 사용함.
//dto 클래스의 멤버 변수는 매칭되는 db 테이블의 컬럼명과 동일하게 맞춰야 함.(대신 카멜명명법 방식으로) 이렇게 하면 맞춰서 데이터가져옴

//데이터베이스와 일치시켜 주기 위해서. t_board2테이블과 자바에서의 데이터를 일치시켜주기 위해서 있는 페이지.
@Getter
@Setter
public class BoardDto {
//    컬럼명 그대로, _없애고 첫번째 문자 대문자로 해줘야 하고 개수 그대로 다 넣어야 한다.
    private int idx;
    private String title;
    private String contents;
    private String userId;
//    카멜명명법이 되어있다.
    private String pwd;
    private String createDt;
    private String updateDt;
    private int hitCnt;
}
