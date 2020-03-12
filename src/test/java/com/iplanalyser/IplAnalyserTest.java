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

        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        String sortedCricketData = iplAnalyser.getSortedCricketData(SortedField.AVERAGE);
        IplCsvDto[] mostRunCsv = new Gson().fromJson(sortedCricketData, IplCsvDto[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnStrikeRate() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        String sortedStrikeRateData = iplAnalyser.getSortedCricketData(SortedField.STRIKE_RATE);
        IplCsvDto[] mostRunCsv = new Gson().fromJson(sortedStrikeRateData, IplCsvDto[].class);
        Assert.assertEquals("Ishant Sharma", mostRunCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnBest4sAnd6sHittingCount() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.NO_OF_4S_AND_6S);
        IplCsvDto[] mostRunCsv = new Gson().fromJson(sortedData, IplCsvDto[].class);
        Assert.assertEquals("Andre Russell", mostRunCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnBest4sAnd6sStrikingRate() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.BEST_FOURS_AND_SIX_WITH_STRIKING_RATE);
        IplCsvDto[] mostRunCsv = new Gson().fromJson(sortedData, IplCsvDto[].class);
        Assert.assertEquals("Andre Russell", mostRunCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSortedOnAverages_ShouldReturnBestStrikeRate() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.BEST_AVG_WITH_STRIKE_RATE);
        IplCsvDto[] mostRunCsv = new Gson().fromJson(sortedData, IplCsvDto[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSortedOnRuns_ShouldReturnBestAverage() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.BEST_RUNS_WITH_BEST_AVG);
        IplCsvDto[] mostRunCsv = new Gson().fromJson(sortedData, IplCsvDto[].class);
        Assert.assertEquals("David Warner ", mostRunCsv[0].playerName);

    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnTopBowlingAverage() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS, IPL_MOST_BOWLING_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.AVERAGE);
        IplCsvDto[] mostBowlingCsv = new Gson().fromJson(sortedData, IplCsvDto[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostBowlingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnTopStrikingRate() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS, IPL_MOST_BOWLING_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.STRIKE_RATE);
        IplCsvDto[] mostBowlingCsv = new Gson().fromJson(sortedData, IplCsvDto[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostBowlingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnBestEconomyRate() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS, IPL_MOST_BOWLING_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.ECONOMY_RATE);
        IplCsvDto[] mostBowlingCsv = new Gson().fromJson(sortedData, IplCsvDto[].class);
        Assert.assertEquals("Ben Cutting", mostBowlingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnBestStrikingRateWith5WicketsAnd4Wickets() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS, IPL_MOST_BOWLING_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.STRIKE_RATE_WITH_4_AND_5_WICKET);
        IplCsvDto[] mostBowlingCsv = new Gson().fromJson(sortedData, IplCsvDto[].class);
        Assert.assertEquals("Lasith Malinga", mostBowlingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnBestBowlingAverageWithBestStrikingRate() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS, IPL_MOST_BOWLING_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.BEST_BOWLING_AVG_WITH_BEST_STRIKE_RATE);
        IplCsvDto[] mostBowlingCsv = new Gson().fromJson(sortedData, IplCsvDto[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostBowlingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnMaximumWicketWithBestBowlingAverage() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS, IPL_MOST_BOWLING_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortedField.MAX_WICKETS_WITH_BEST_BOWLING_AVERAGE);
        IplCsvDto[] mostBowlingCsv = new Gson().fromJson(sortedData, IplCsvDto[].class);
        Assert.assertEquals("Imran Tahir", mostBowlingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnBestBattingAndBowlingAverage() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH, IPL_MOST_BOWLING_FILE_PATH);
        String sortedCricketData = iplAnalyser.getSortedCricketData(SortedField.BEST_BATTING_BOWLING_AVG);
        IplCsvDto[] mostBowlingCsv = new Gson().fromJson(sortedCricketData, IplCsvDto[].class);
        Assert.assertEquals("Marcus Stoinis", mostBowlingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnAllRounderPlayerName() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH, IPL_MOST_BOWLING_FILE_PATH);
        String sortedCricketData = iplAnalyser.getSortedCricketData(SortedField.BEST_ALL_ROUNDER);
        IplCsvDto[] mostBowlingCsv = new Gson().fromJson(sortedCricketData, IplCsvDto[].class);
        Assert.assertEquals("Hardik Pandya", mostBowlingCsv[0].playerName);
    }
}
