package com.iplanalyser;

import java.util.Map;

public class IplBowlingAdapter extends IplAdapter {

    @Override
    public Map<String, IplCsvDto> loadIplData(String... csvFilePath) {
        Map<String, IplCsvDto> cricketCsvDtoMap=super.loadIplData(IplBowlingCSV.class,csvFilePath[0]);
        return cricketCsvDtoMap;
    }
}
