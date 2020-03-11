package com.iplanalyser;

import java.util.Map;

public class IplRunsAdapter extends IplAdapter {

    @Override
    public Map<String, CricketCsvDto> loadIplData(String... csvFilePath) {
        Map<String,CricketCsvDto> cricketCsvDtoMap =super.loadIplData(IplRunsCSV.class,csvFilePath[0]);
        return cricketCsvDtoMap;
    }
}
