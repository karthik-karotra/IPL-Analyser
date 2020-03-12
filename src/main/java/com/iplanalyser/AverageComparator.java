package com.iplanalyser;

import java.util.Comparator;

public class AverageComparator implements Comparator<IplCsvDto> {

    @Override
    public int compare(IplCsvDto p1, IplCsvDto p2) {
        int i= ((int)(p1.battingAverage+p1.bowlerAverage));
        return i;
    }
}
