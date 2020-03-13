package com.iplanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class IplAnalyser {
    List<IplCsvDto> iplCSVList;
    Map<String, IplCsvDto> iplCsvMap;

    public enum Cricket {
        RUNS, WICKETS;
    }


    public IplAnalyser() {
        this.iplCSVList = new ArrayList<>();
        SortedField.initializingSortedFieldMembers();
        this.iplCsvMap = new HashMap<>();
    }

    public int loadIplData(Cricket cricket, String... csvFilePath) {

        iplCsvMap = IplAdapterFactory.getIPLData(cricket, csvFilePath);

        return iplCsvMap.size();

    }

    public String getSortedCricketData(SortedField sortedField) {
        iplCSVList = iplCsvMap.values().stream().collect(Collectors.toList());
        if (iplCSVList == null || iplCSVList.size() == 0) {
            throw new IplAnalyserException("No Data", IplAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        this.sort(SortedField.sortedMap.get(sortedField));
        Collections.reverse(iplCSVList);
        String sortedDataJson = new Gson().toJson(iplCSVList);
        return sortedDataJson;
    }

    private void sort(Comparator<IplCsvDto> iplComparator) {
        for (int i = 0; i < iplCSVList.size() - 1; i++) {
            for (int j = 0; j < iplCSVList.size() - i - 1; j++) {
                IplCsvDto run1 = this.iplCSVList.get(j);
                IplCsvDto run2 = this.iplCSVList.get(j + 1);
                if (iplComparator.compare(run1, run2) > 0) {
                    iplCSVList.set(j, run2);
                    iplCSVList.set(j + 1, run1);
                }
            }
        }
    }


}
