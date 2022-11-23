package com.bitc.xmltest2.service;

import com.bitc.xmltest2.dto.*;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class PharmacyFullDataServiceImpl implements PharmacyFullDataService{
    //    해당 인터페이스에 모든 메서드 구현을 하지 않아서 에러남.
    @Override
    public List<PharmacyFullDataItemDto> getItemList() throws Exception{
        
//        JAXB 라이브러리 사용 선언
//        싱글톤 방식으로 동작 중
//        PharmacyFullDataDto 클래스 타입으로 XML 데이터를 파싱

        JAXBContext jc = JAXBContext.newInstance(PharmacyFullDataDto.class);
        
//        JAXB 라이브러리를 사용하여 XML 데이터를 자바 클래스 타입의 객체로 변환하는 언마샬 객체를 생성
        Unmarshaller um = jc.createUnmarshaller();
        
//          기존에 제공된 XML 데이터를 기반으로 PharmacyFullDataDto 클래스의 객체를 생성하므로 XML 데이터를 파싱하여
//        가져온 데이터를 PharmacyFullDataDto 클래스 타입의 객체 fullData에 형변환하여 저장
        PharmacyFullDataDto fullData = (PharmacyFullDataDto) um.unmarshal(new File("C://java505//pharmacy.xml"));
        
        PharmacyFullDataHeaderDto header = fullData.getHeader();
        PharmacyFullDataBodyDto body = fullData.getBody();
        PharmacyFullDataItemsDto items = body.getItems();
//        사용자가 필요로 하는 데이터만 출력 PharmacyFullDataItemDto가 가장 핵심. 리스트 타입에 넣어 반환시켜준다!!
//        컨트롤러로 반환하면 view로 가서 뿌려줌
        List<PharmacyFullDataItemDto> itemList = items.getItemList();

        return itemList;
    }

    @Override
    public List<PharmacyFullDataItemDto> getItemListUrl(String strUrl) throws Exception {

        List<PharmacyFullDataItemDto> itemList = null;
        URL url = null;
        HttpURLConnection urlConn = null;

        try{
            url = new URL(strUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");

            JAXBContext jc = JAXBContext.newInstance(PharmacyFullDataDto.class);
            Unmarshaller um = jc.createUnmarshaller();

//            PharmacyFullDataDto fullData = (PharmacyFullDataDto) um.unmarshal(new File("C://java505//pharmacy.xml"));

            PharmacyFullDataDto fullData = (PharmacyFullDataDto) um.unmarshal(url);
            itemList = fullData.getBody().getItems().getItemList();
        }
        catch (Exception e) {
            e.printStackTrace();

        }
        finally {
            if(urlConn != null) {urlConn.disconnect();}
        }
        return itemList;
    }


}
