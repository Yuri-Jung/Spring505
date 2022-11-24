package com.bitc.xmltest2.service;

import com.bitc.xmltest2.dto.BoxOffice;
import com.bitc.xmltest2.dto.DailyBoxOffice;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
@Service
public class BoxOfficeServiceImpl implements BoxOfficeService {
    @Override
    public List<DailyBoxOffice> getDailyBoxOfficeListJson(String serviceUrl) {
        List<DailyBoxOffice> itemList = null;
        URL url = null;
        HttpURLConnection urlConn = null;
        BufferedReader reader = null; // 웹 서버에서 응답으로 넘겨주는 데이터를 받아오는 스트림
        try {
            url = new URL(serviceUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");
//            String 문자열 문자열연결 연산자 사용시 str1="문자열1" str2="만들기"
//            str1 + str2 => 외부적으로는 새로운 문자열을 생성하여 "문자열만들기"처럼
//            문자 합치고 새로운 객체로 출력해줌
//            매번 새롭게 만들어야 함.

//            하지만 StringBuilder 는 원본하나에 뒤에 계속 새로운 문자열이 뒤에 추가되는 형태이다.
//            그렇기 때문에 StringBuilder는 효과가 더 좋음 뒤에 toString()붙이면 문자열로 출력이 됨

//            웹 사이트 연결이 완료
            reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
//            문자열을 효과적으로 사용하기 위한 클래스
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
//            gson 객체 사용
            Gson gson = new Gson();
//            Gson을 통해서 가져온 데이터를 BoxOffice 클래스 타입으로 변환
            BoxOffice boxOffice = gson.fromJson(sb.toString(), BoxOffice.class);

            itemList = boxOffice.getBoxOfficeResult().getDailyBoxOfficeList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (urlConn != null) {
            urlConn.disconnect();
        }


    return itemList;

//    @Override
//    public List<DailyBoxOffice> getDailyBoxOfficeListJson() {
//        return null;
    }

//    @Override
//    public List<DailyBoxOffice> getDailyBoxOfficeListJson() {
//        return null;
//    }
}
//gson을 통해 변환하느냐 jaxbContext를 통해 unmarshal로 파싱하느냐의 차이
