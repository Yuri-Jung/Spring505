package com.bitc.xmltest2.controller;

import com.bitc.xmltest2.dto.PharmacyFullDataItemDto;
import com.bitc.xmltest2.service.PharmacyFullDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
// jaxb : 자바에서 xml 데이터를 파싱을 도와주는 라이브러리
// Marshal(마샬) : 자바 클래스를 xml 데이터로 변환
// UnMarshal(언마샬) : xml 데이터를 자바 클래스 타입의 객체로 변환(serviceImpl에서 사용했음)

// @XmlRootElement : xml 데이터에서 부모가 되는 태그를 뜻함
// @XmlElement : xml 데이터에서 실제 데이터가 들어있는 태그를 뜻함
// @XmlAttribute : xml 데이터에서 지정한 태그의 속성을 뜻함 ex.getParameter 추가 속성 가져옴

@Controller
public class PharmacyController {
    @Autowired
    private PharmacyFullDataService pharmacyFullDataService;

    @Value("${openApi.endPoint}")
//    이거 넣어야함
    private String endPointUrl;
    @Value("${openApi.serviceKey}")
    private String serviceKey;

    @RequestMapping("/")
        public String index() throws Exception{
            return "index";
    }

    @RequestMapping(value = "/pharmacy/fullDataFile", method = RequestMethod.GET)
        public ModelAndView getFullData() throws Exception {
            ModelAndView mv = new ModelAndView("pharmacy/fullDataFile");

        List<PharmacyFullDataItemDto> itemList = pharmacyFullDataService.getItemList();
        mv.addObject("pharmacyDatas",itemList);
        return mv;
        }

        @RequestMapping(value = "/pharmacy/fullDataUrl", method = RequestMethod.GET)
        public String viewFullData() throws Exception {
            return "pharmacy/fullDataUrl";
        }

        @ResponseBody //이걸 붙여야 ajax로 통신 가능
        @RequestMapping(value = "/pharmacy/fullDataUrl", method = RequestMethod.POST)
        public Object getFullDataAjax() throws Exception {
//      open api 서버로 요청하기 위한 url 생성 작업 필요
//            https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService 서버주소
//            /getParmacyFullDown //서비스 요청 주소
//            ?serviceKey=kC6uK6tAuH39D%2BliLhjZ8K9Li8R%2B3CEqeHI%2BF%2BiX8Z3hBJA1TuOxuOZu59DVFUzzL7NB07Bio5tfh5EEBN3xGA%3D%3D
//            &pageNo=1 // 옵션1
//            &numOfRows=10 //옵션2

//            URL 주소로 데이터 받아옴
//            String endPoint = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/";


            String reqService = "/getParmacyFullDown";
            String service = "?serviceKey=";
            String option1 = "&pageNo=";
            String option2 = "&numOfRows=";

            String pp = re

            String url = endPointUrl + reqService + service + serviceKey + option1 + 1 + option2 + pp;
//            여기 매개변수로 넣어줘라
//            맨 마지막 숫자는 한 페이지에 보여지는 row 수 의미.

//            String url = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/" +
//                    "getParmacyFullDown?serviceKey=kC6uK6tAuH39D%2BliLhjZ8K9Li8R%2B3CEqeHI%2BF%2BiX8Z3hBJA1Tu" +
//                    "OxuOZu59DVFUzzL7NB07Bio5tfh5EEBN3xGA%3D%3D&pageNo=1&numOfRows=10";
            List<PharmacyFullDataItemDto> pharmacyDatas = pharmacyFullDataService.getItemListUrl(url);



            return pharmacyDatas;
        }

}
