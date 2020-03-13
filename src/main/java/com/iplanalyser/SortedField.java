package com.iplanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public enum  SortedField {
    AVERAGE, STRIKE_RATE,NO_OF_4S_AND_6S, BEST_RUNS_WITH_BEST_AVG, BEST_FOURS_AND_SIX_WITH_STRIKING_RATE, BEST_AVG_WITH_STRIKE_RATE, ECONOMY_RATE, STRIKE_RATE_WITH_4_AND_5_WICKET, BEST_BOWLING_AVG_WITH_BEST_STRIKE_RATE, MAX_WICKETS_WITH_BEST_BOWLING_AVERAGE, ALL_ROUNDER, BEST_BATTING_BOWLING_AVG, BEST_ALL_ROUNDER;
    static Map<SortedField,Comparator<IplCsvDto>> sortedMap=new HashMap<>();

    static void initializingSortedFieldMembers() {
         sortedMap.put(SortedField.AVERAGE, Comparator.comparing(ipldata -> ipldata.average));
         sortedMap.put(SortedField.STRIKE_RATE, Comparator.comparing(ipldata -> ipldata.strikeRate));
         sortedMap.put(SortedField.NO_OF_4S_AND_6S, Comparator.comparing(ipldata -> ipldata.noOfFours + ipldata.noOfSixes));
         Comparator<IplCsvDto> foursAndSix = Comparator.comparing(ipldata -> ipldata.noOfFours + ipldata.noOfSixes);
         sortedMap.put(SortedField.BEST_FOURS_AND_SIX_WITH_STRIKING_RATE, foursAndSix.thenComparing(ipldata -> ipldata.strikeRate));
         Comparator<IplCsvDto> average = Comparator.comparing(ipldata -> ipldata.average);
         sortedMap.put(SortedField.BEST_AVG_WITH_STRIKE_RATE, average.thenComparing(ipldata -> ipldata.strikeRate));
         Comparator<IplCsvDto> runsWithAvg = Comparator.comparing(ipldata -> ipldata.runs);
         sortedMap.put(SortedField.BEST_RUNS_WITH_BEST_AVG, runsWithAvg.thenComparing(ipldata -> ipldata.average));
         sortedMap.put(SortedField.ECONOMY_RATE, Comparator.comparing(ipldata -> ipldata.economyRate));
         Comparator<IplCsvDto> bestStrikeRateWith4And5Wickets = Comparator.comparing(ipldata -> ipldata.fourWickets + ipldata.fiveWickets);
         sortedMap.put(SortedField.STRIKE_RATE_WITH_4_AND_5_WICKET, bestStrikeRateWith4And5Wickets.thenComparing(ipldata -> ipldata.strikeRate));
         Comparator<IplCsvDto> bestBowlingAverageWithBestStrikeRate = Comparator.comparing(ipldata -> ipldata.average);
         sortedMap.put(SortedField.BEST_BOWLING_AVG_WITH_BEST_STRIKE_RATE, bestBowlingAverageWithBestStrikeRate.thenComparing(ipldata -> ipldata.strikeRate));
         Comparator<IplCsvDto> maximumWicketsWithBestBowlingAverage = Comparator.comparing(ipldata -> ipldata.wickets);
         sortedMap.put(SortedField.MAX_WICKETS_WITH_BEST_BOWLING_AVERAGE, maximumWicketsWithBestBowlingAverage.thenComparing(ipldata -> ipldata.average));
         sortedMap.put(SortedField.BEST_BATTING_BOWLING_AVG,new AverageComparator());
         sortedMap.put(SortedField.BEST_ALL_ROUNDER,new AllRounderComparator());
    }
}

