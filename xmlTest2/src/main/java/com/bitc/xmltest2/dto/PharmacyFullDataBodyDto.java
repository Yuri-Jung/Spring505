package com.bitc.xmltest2.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "body")
//name은 받은 파일이름 그대로사용해야함
public class PharmacyFullDataBodyDto {
    private int numOfRows;
    private int pageNo;
    private int totalCount;
//    그 자체로 데이터 나오는 애들은 그냥 쓰면 되고 아닌 애들은 클래스만들어야함
    private PharmacyFullDataItemsDto items; //얘도 getter,setter 만들어야

    @XmlElement
    public int getNumOfRows() {
        return numOfRows;
    }
    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }
    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    @XmlElement(name = "items")
    public PharmacyFullDataItemsDto getItems() {
        return items;
    }
    public void setItems(PharmacyFullDataItemsDto items) {
        this.items = items;
    }
}
