package com.iplanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class IplAnalyser {
    List<IplCsvDto> iplCSVList;
    Map<SortedField,Comparator<IplCsvDto>> sortedMap;
    Map<String, IplCsvDto> iplCsvMap;
    public enum Cricket {
        RUNS,WICKETS, ALL_ROUNDER, BEST_ALL_ROUNDER;
    }


    public IplAnalyser() {
        this.iplCSVList = new ArrayList<>();
        this.sortedMap = new HashMap<>();
        this.iplCsvMap = new HashMap<>();
        this.sortedMap.put(SortedField.AVERAGE, Comparator.comparing(ipldata -> ipldata.average));
        this.sortedMap.put(SortedField.STRIKE_RATE, Comparator.comparing(ipldata -> ipldata.strikeRate));
        this.sortedMap.put(SortedField.NO_OF_4S_AND_6S, Comparator.comparing(ipldata -> ipldata.noOfFours + ipldata.noOfSixes));

        Comparator<IplCsvDto> foursAndSix = Comparator.comparing(ipldata -> ipldata.noOfFours + ipldata.noOfSixes);
        this.sortedMap.put(SortedField.BEST_FOURS_AND_SIX_WITH_STRIKING_RATE, foursAndSix.thenComparing(ipldata -> ipldata.strikeRate));
        Comparator<IplCsvDto> average = Comparator.comparing(ipldata -> ipldata.average);
        this.sortedMap.put(SortedField.BEST_AVG_WITH_STRIKE_RATE, average.thenComparing(ipldata -> ipldata.strikeRate));
        Comparator<IplCsvDto> runsWithAvg = Comparator.comparing(ipldata -> ipldata.runs);
        this.sortedMap.put(SortedField.BEST_RUNS_WITH_BEST_AVG, runsWithAvg.thenComparing(ipldata -> ipldata.average));

        this.sortedMap.put(SortedField.ECONOMY_RATE, Comparator.comparing(ipldata -> ipldata.economyRate));
        Comparator<IplCsvDto> bestStrikeRateWith4And5Wickets = Comparator.comparing(ipldata -> ipldata.fourWickets + ipldata.fiveWickets);
        this.sortedMap.put(SortedField.STRIKE_RATE_WITH_4_AND_5_WICKET, bestStrikeRateWith4And5Wickets.thenComparing(ipldata -> ipldata.strikeRate));
        Comparator<IplCsvDto> bestBowlingAverageWithBestStrikeRate = Comparator.comparing(ipldata -> ipldata.average);
        this.sortedMap.put(SortedField.BEST_BOWLING_AVG_WITH_BEST_STRIKE_RATE, bestBowlingAverageWithBestStrikeRate.thenComparing(ipldata -> ipldata.strikeRate));
        Comparator<IplCsvDto> maximumWicketsWithBestBowlingAverage = Comparator.comparing(ipldata -> ipldata.wickets);
        this.sortedMap.put(SortedField.MAX_WICKETS_WITH_BEST_BOWLING_AVERAGE, maximumWicketsWithBestBowlingAverage.thenComparing(ipldata -> ipldata.average));
        this.sortedMap.put(SortedField.BEST_BATTING_BOWLING_AVG,new AverageComparator());
        this.sortedMap.put(SortedField.BEST_ALL_ROUNDER,new AllRounderComparator());
    }

    public int loadIplData(Cricket cricket,String... csvFilePath) {

        iplCsvMap = IplAdapterFactory.getIPLData(cricket,csvFilePath);

        return iplCsvMap.size();

    }

    public String getSortedCricketData(SortedField sortedField) {
       iplCSVList = iplCsvMap.values().stream().collect(Collectors.toList());
        if(iplCSVList == null || iplCSVList.size() == 0){
            throw new IplAnalyserException("No Data",IplAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        this.sort(this.sortedMap.get(sortedField));
        Collections.reverse(iplCSVList);
        String sortedDataJson=new Gson().toJson(iplCSVList);
        return sortedDataJson;
    }

    private void sort(Comparator<IplCsvDto> iplComparator) {
        for(int i=0;i<iplCSVList.size()-1;i++){
            for(int j=0;j<iplCSVList.size()-i-1;j++){
                IplCsvDto run1 = this.iplCSVList.get(j);
                IplCsvDto run2 = this.iplCSVList.get(j+1);
                if(iplComparator.compare(run1,run2)>0){
                    iplCSVList.set(j,run2);
                    iplCSVList.set(j+1,run1);
                }
            }
        }
    }


}
