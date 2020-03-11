package com.iplanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class IplAnalyserTest {
    private static IplAnalyser iplAnalyser;
    private static final String IPL_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_BOWLING_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @BeforeClass
    public static void setUp() {
        iplAnalyser = new IplAnalyser();
    }

    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnMostRunAverage() {

            iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = iplAnalyser.getSortedCricketData(SortedField.AVERAGE);
            IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IplRunsCSV[].class);
            Assert.assertEquals("MS Dhoni", mostRunCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnStrikeRate() {

            iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
            String sortedStrikeRateData = iplAnalyser.getSortedCricketData(SortedField.STRIKE_RATE);
            IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedStrikeRateData, IplRunsCSV[].class);
            Assert.assertEquals("Ishant Sharma",mostRunCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnBest4sAnd6sHittingCount() {
            iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
            String sortedData = iplAnalyser.getSortedCricketData(SortedField.NO_OF_4S_AND_6S);
            IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedData, IplRunsCSV[].class);
            Assert.assertEquals("Andre Russell",mostRunCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnBest4sAnd6sStrikingRate() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.BEST_FOURS_AND_SIX_WITH_STRIKING_RATE);
        IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedData, IplRunsCSV[].class);
        Assert.assertEquals("Andre Russell",mostRunCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSortedOnAverages_ShouldReturnBestStrikeRate() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.BEST_AVG_WITH_STRIKE_RATE);
        IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedData, IplRunsCSV[].class);
        Assert.assertEquals("MS Dhoni",mostRunCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSortedOnRuns_ShouldReturnBestAverage() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.BEST_RUNS_WITH_BEST_AVG);
        IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedData, IplRunsCSV[].class);
        Assert.assertEquals("David Warner ",mostRunCsv[0].playerName);

    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnTopBowlingAverage() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.AVERAGE);
        IplBowlingCSV[] mostBowlingCsv = new Gson().fromJson(sortedData, IplBowlingCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostBowlingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnTopStrikingRate() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.STRIKE_RATE);
        IplBowlingCSV[] mostBowlingCsv = new Gson().fromJson(sortedData, IplBowlingCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostBowlingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnBestEconomyRate() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.ECONOMY_RATE);
        IplBowlingCSV[] mostBowlingCsv = new Gson().fromJson(sortedData, IplBowlingCSV[].class);
        Assert.assertEquals("Ben Cutting", mostBowlingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnBestStrikingRateWith5WicketsAnd4Wickets() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.STRIKE_RATE_WITH_4_AND_5_WICKET);
        IplBowlingCSV[] mostBowlingCsv = new Gson().fromJson(sortedData, IplBowlingCSV[].class);
        Assert.assertEquals("Lasith Malinga", mostBowlingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnBestBowlingAverageWithBestStrikingRate() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.BEST_BOWLING_AVG_WITH_BEST_STRIKE_RATE);
        IplBowlingCSV[] mostBowlingCsv = new Gson().fromJson(sortedData, IplBowlingCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostBowlingCsv[0].playerName);
    }




}
