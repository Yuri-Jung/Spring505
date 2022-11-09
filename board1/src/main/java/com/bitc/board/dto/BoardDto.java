package com.bitc.board.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data : lombok 라이브러리에서 지원하는 어노테이션으로 해당 클래스의 멤버 변수에 대한 getter/setter/toString() 메서드를
// 자동으로 생성하는 어노테이션.@Getter, @Setter , @ToString 어노테이션을 모두 사용할 효과. @Data = @Getter + @Setter + @ToString
// 한번 씩 @Data만 입력하면 작동이 잘 안 될 때가 종종 있다. 그럴 때는 @Getter, @Setter , @ToString입력하면 된다.

//@Data
//이렇게 클래스를 넣는 이유 다양한 데이터 타입을 적은 용량으로 즐기기 위해서
@Getter
@Setter
public class BoardDto {
//    컬럼명 그대로, _없애고 첫번째 문자 대문자로 해줘야 하고 개수 그대로 다 넣어야 한다.
    private int idx;
    private String title;
    private String contents;
    private String userId;
    private String pwd;
    private String createDt;
    private String updateDt;
    private int hitCnt;
}
