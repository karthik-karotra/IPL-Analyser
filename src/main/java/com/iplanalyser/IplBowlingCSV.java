package com.iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class IplBowlingCSV {
    @CsvBindByName(column = "PLAYER",required = true)
    public String playerName;

    @CsvBindByName(column = "Avg",required = true)
    public double average;



   /* @CsvBindByName(column = "4w",required = true)
    public int fourWickets;

    @CsvBindByName(column = "5w",required = true)
    public int fiveWickets;*/
}
