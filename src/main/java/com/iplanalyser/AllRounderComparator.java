package com.iplanalyser;

import java.util.Comparator;

public class AllRounderComparator implements Comparator<CricketCsvDto> {

    @Override
    public int compare(CricketCsvDto p1, CricketCsvDto p2) {
        int i=0;
        if(p1.allWickets!=0) {
            i = (p1.runs * p1.allWickets) - (p2.runs * p2.allWickets);
        }
        return i;
    }

}
