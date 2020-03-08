package com.iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class IplRunsCSV {

    @CsvBindByName(column = "Avg",required = true)
    public double battingAvg;

    @CsvBindByName(column = "SR",required = true)
    public double strikeRate;


    @CsvBindByName(column = "4s",required = true)
    public int noOfFours;

    @CsvBindByName(column = "6s",required = true)
    public int noOfSixes;
}
