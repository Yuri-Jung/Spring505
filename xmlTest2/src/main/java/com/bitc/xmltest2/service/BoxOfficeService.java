package com.bitc.xmltest2.service;

import com.bitc.xmltest2.dto.DailyBoxOffice;

import java.util.List;

public interface BoxOfficeService {
    public List<DailyBoxOffice> getDailyBoxOfficeListJson(String serviceUrl);
}
