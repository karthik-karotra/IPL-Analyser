package com.iplanalyser;

import com.csvreader.CSVBuilderException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IplAnalyserTest {
    private static IplCsvDto[] mostPlayingCsv;
    private static String sortedIplData;
    private static IplAnalyser iplAnalyser;
    private static final String IPL_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_BOWLING_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String IPL_MOST_RUNS_WRONG_FILE_PATH = "./src/test/resources/WrongFactsheet.csv";


    @BeforeClass
    public static void setUp() {
        iplAnalyser = new IplAnalyser();
    }

    @Test
    public void givenIplDataData_WithWrongClassAndCorrectFile_ShouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IplAnalyserException.class);
            iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS,IPL_MOST_RUNS_FILE_PATH);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIplDataData_WithWrongFileAndCorrectClass_ShouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IplAnalyserException.class);
            iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS,IPL_MOST_RUNS_WRONG_FILE_PATH);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnMostRunAverage() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        sortedIplData = iplAnalyser.getSortedCricketData(SortedField.AVERAGE);
        mostPlayingCsv = new Gson().fromJson(sortedIplData, IplCsvDto[].class);
        Assert.assertEquals("MS Dhoni", mostPlayingCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnStrikeRate() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        sortedIplData = iplAnalyser.getSortedCricketData(SortedField.STRIKE_RATE);
        mostPlayingCsv = new Gson().fromJson(sortedIplData, IplCsvDto[].class);
        Assert.assertEquals("Ishant Sharma", mostPlayingCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnBest4sAnd6sHittingCount() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        sortedIplData = iplAnalyser.getSortedCricketData(SortedField.NO_OF_4S_AND_6S);
        mostPlayingCsv = new Gson().fromJson(sortedIplData, IplCsvDto[].class);
        Assert.assertEquals("Andre Russell", mostPlayingCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnBest4sAnd6sStrikingRate() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        sortedIplData = iplAnalyser.getSortedCricketData(SortedField.BEST_FOURS_AND_SIX_WITH_STRIKING_RATE);
        mostPlayingCsv = new Gson().fromJson(sortedIplData, IplCsvDto[].class);
        Assert.assertEquals("Andre Russell", mostPlayingCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSortedOnAverages_ShouldReturnBestStrikeRate() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        sortedIplData = iplAnalyser.getSortedCricketData(SortedField.BEST_AVG_WITH_STRIKE_RATE);
        mostPlayingCsv = new Gson().fromJson(sortedIplData, IplCsvDto[].class);
        Assert.assertEquals("MS Dhoni", mostPlayingCsv[0].playerName);

    }

    @Test
    public void givenIPLMostRunData_WhenSortedOnRuns_ShouldReturnBestAverage() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        sortedIplData = iplAnalyser.getSortedCricketData(SortedField.BEST_RUNS_WITH_BEST_AVG);
        mostPlayingCsv = new Gson().fromJson(sortedIplData, IplCsvDto[].class);
        Assert.assertEquals("David Warner ", mostPlayingCsv[0].playerName);

    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnTopBowlingAverage() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS, IPL_MOST_BOWLING_FILE_PATH);
        sortedIplData = iplAnalyser.getSortedCricketData(SortedField.AVERAGE);
        mostPlayingCsv = new Gson().fromJson(sortedIplData, IplCsvDto[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostPlayingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnTopStrikingRate() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS, IPL_MOST_BOWLING_FILE_PATH);
        sortedIplData = iplAnalyser.getSortedCricketData(SortedField.STRIKE_RATE);
        mostPlayingCsv = new Gson().fromJson(sortedIplData, IplCsvDto[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostPlayingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnBestEconomyRate() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS, IPL_MOST_BOWLING_FILE_PATH);
        sortedIplData = iplAnalyser.getSortedCricketData(SortedField.ECONOMY_RATE);
        mostPlayingCsv = new Gson().fromJson(sortedIplData, IplCsvDto[].class);
        Assert.assertEquals("Ben Cutting", mostPlayingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnBestStrikingRateWith5WicketsAnd4Wickets() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS, IPL_MOST_BOWLING_FILE_PATH);
        sortedIplData = iplAnalyser.getSortedCricketData(SortedField.STRIKE_RATE_WITH_4_AND_5_WICKET);
        mostPlayingCsv = new Gson().fromJson(sortedIplData, IplCsvDto[].class);
        Assert.assertEquals("Lasith Malinga", mostPlayingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnBestBowlingAverageWithBestStrikingRate() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS, IPL_MOST_BOWLING_FILE_PATH);
        sortedIplData = iplAnalyser.getSortedCricketData(SortedField.BEST_BOWLING_AVG_WITH_BEST_STRIKE_RATE);
        mostPlayingCsv = new Gson().fromJson(sortedIplData, IplCsvDto[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostPlayingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnMaximumWicketWithBestBowlingAverage() {

        iplAnalyser.loadIplData(IplAnalyser.Cricket.WICKETS, IPL_MOST_BOWLING_FILE_PATH);
        sortedIplData = iplAnalyser.getSortedCricketData(SortedField.MAX_WICKETS_WITH_BEST_BOWLING_AVERAGE);
        mostPlayingCsv = new Gson().fromJson(sortedIplData, IplCsvDto[].class);
        Assert.assertEquals("Imran Tahir", mostPlayingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnBestBattingAndBowlingAverage() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH, IPL_MOST_BOWLING_FILE_PATH);
        sortedIplData = iplAnalyser.getSortedCricketData(SortedField.BEST_BATTING_BOWLING_AVG);
        mostPlayingCsv = new Gson().fromJson(sortedIplData, IplCsvDto[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostPlayingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnAllRounderPlayerName() {
        iplAnalyser.loadIplData(IplAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH, IPL_MOST_BOWLING_FILE_PATH);
        sortedIplData = iplAnalyser.getSortedCricketData(SortedField.BEST_ALL_ROUNDER);
        mostPlayingCsv = new Gson().fromJson(sortedIplData, IplCsvDto[].class);
        Assert.assertEquals("Hardik Pandya", mostPlayingCsv[0].playerName);
    }
}
