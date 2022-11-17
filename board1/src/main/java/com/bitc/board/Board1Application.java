package com.bitc.board;
// 서버에 등록되는 부분
//스프링이 제공하는 기본적인 부분을 무시하고
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

//@SpringBootApplication
// exclude : 옵션을 사용하여 MultipartAutoConfiguration클래스의 자동구성을 사용하지 않도록 설정

@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class Board1Application {

    public static void main(String[] args) {

        SpringApplication.run(Board1Application.class, args);
    }

}
