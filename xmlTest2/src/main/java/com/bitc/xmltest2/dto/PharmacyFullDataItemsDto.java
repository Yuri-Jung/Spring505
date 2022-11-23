package com.bitc.xmltest2.dto;
//ssssssssssssssssssss
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "items")
public class PharmacyFullDataItemsDto {
    private List<PharmacyFullDataItemDto> itemList;

    @XmlElement(name = "item")
    public List<PharmacyFullDataItemDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<PharmacyFullDataItemDto> itemList) {
        this.itemList = itemList;
    }
}
