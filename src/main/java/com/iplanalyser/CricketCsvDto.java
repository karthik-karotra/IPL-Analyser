package com.iplanalyser;

public class CricketCsvDto {

        public int runs;
        public double average;
        public double strikeRate;
        public int noOfFours;
        public int noOfSixes;
        public String playerName;
        public double economyRate;
        public int fourWickets;
        public int fiveWickets;
        public int wickets;
        public double bowlerAverage;
        public double battingAverage;
        public int allWickets;

    public CricketCsvDto(IplRunsCSV mostRunCsv) {
            runs = mostRunCsv.runs;
            average = mostRunCsv.average;
            strikeRate = mostRunCsv.strikeRate;
            noOfFours = mostRunCsv.noOfFours;
            noOfSixes = mostRunCsv.noOfSixes;
            playerName =mostRunCsv.playerName;
            battingAverage=mostRunCsv.average;
        }

    public CricketCsvDto(IplBowlingCSV mostBowlingCsv) {
            average = mostBowlingCsv.average;
            playerName=mostBowlingCsv.playerName;
            strikeRate = mostBowlingCsv.strikeRate;
            economyRate=mostBowlingCsv.economyRate;
        fourWickets = mostBowlingCsv.fourWickets;
        fiveWickets = mostBowlingCsv.fiveWickets;
        wickets=mostBowlingCsv.wickets;

        }
    }

