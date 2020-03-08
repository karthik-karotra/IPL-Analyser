package com.iplanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class IplAnalyserTest {
    private static IplAnalyser iplAnalyser;
    private static final String IPL_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @BeforeClass
    public static void setUp() throws Exception {
        iplAnalyser = new IplAnalyser();
    }

    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnMostRunAverage() {
        try {
            iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = iplAnalyser.getSortedCricketData(SortedField.AVERAGE);
            IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IplRunsCSV[].class);
            Assert.assertEquals(83.2, mostRunCsv[0].battingAvg, 0.0);
        }catch (IplAnalyserException e){}
    }

    @Test
    public void givenIPLMostRunData_When_Sorted_ShouldReturnStrikeRate() {
        try{
            iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
            String sortedStrikeRateData = iplAnalyser.getSortedCricketData(SortedField.STRIKE_RATE);
            IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedStrikeRateData, IplRunsCSV[].class);
            Assert.assertEquals(333.33,mostRunCsv[0].strikRate,0.0);
        }catch (IplAnalyserException e){}
    }
}
