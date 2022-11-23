package com.bitc.xmltest2.service;

import com.bitc.xmltest2.dto.PharmacyFullDataItemDto;

import java.util.List;

public interface PharmacyFullDataService {
    List<PharmacyFullDataItemDto> getItemList() throws Exception;

    List<PharmacyFullDataItemDto> getItemListUrl(String url) throws Exception;
}
