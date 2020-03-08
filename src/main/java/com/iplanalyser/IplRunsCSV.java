package com.iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class IplRunsCSV {

    @CsvBindByName(column = "Avg",required = true)
    public double battingAvg;

    @CsvBindByName(column = "SR",required = true)
    public double strikRate;
}
