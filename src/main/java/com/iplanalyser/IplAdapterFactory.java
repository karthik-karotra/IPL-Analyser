package com.iplanalyser;

import java.util.Map;

public class IplAdapterFactory {
    public static Map<String, CricketCsvDto> getIPLData(IplAnalyser.Cricket cricket, String... csvFilePath) {
if(cricket.equals(IplAnalyser.Cricket.RUNS))
    return new IplRunsAdapter().loadIplData(csvFilePath);
else if (cricket.equals(IplAnalyser.Cricket.WICKETS))
    return new IplBowlingAdapter().loadIplData(csvFilePath);
else if(cricket.equals(IplAnalyser.Cricket.ALL_ROUNDER))
    return new AllRounderAverage().loadIplData(csvFilePath);
else if(cricket.equals(IplAnalyser.Cricket.BEST_ALL_ROUNDER))
    return new AllRounderPlayer().loadIplData(csvFilePath);
else
    throw new IplAnalyserException("NO FILE FOUND!!!!",IplAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
    }
}
