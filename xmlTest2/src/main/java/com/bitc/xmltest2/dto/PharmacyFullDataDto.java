package com.bitc.xmltest2.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
//@XmlRootElement 해당 클래스의 부모 태그. response를 부모태그로 인식하겠다(연결)

public class PharmacyFullDataDto {
    private PharmacyFullDataHeaderDto header; //dto 2개 생성
    private PharmacyFullDataBodyDto body;

    @XmlElement(name = "header")
//    header와 연결하겠다
    public PharmacyFullDataHeaderDto getHeader() {
        return header;
    }

    public void setHeader(PharmacyFullDataHeaderDto header) {
        this.header = header;
    }
    @XmlElement(name = "body")
    public PharmacyFullDataBodyDto getBody() {
        return body;
    }

    public void setBody(PharmacyFullDataBodyDto body) {
        this.body = body;
    }
}
