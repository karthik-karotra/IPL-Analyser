package com.iplanalyser;

import java.util.Map;

public class IplAdapterFactory {
    public static Map<String, IplCsvDto> getIPLData(IplAnalyser.Cricket cricket, String... csvFilePath) {
        if (cricket.equals(IplAnalyser.Cricket.RUNS))
            return new IplRunsAdapter().loadIplData(csvFilePath);
        else if (cricket.equals(IplAnalyser.Cricket.WICKETS))
            return new IplBowlingAdapter().loadIplData(csvFilePath);
        throw new IplAnalyserException("NO FILE FOUND!!!!", IplAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
    }
}
