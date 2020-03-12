package com.iplanalyser;

import java.util.Comparator;

public class AllRounderComparator implements Comparator<IplCsvDto> {

    @Override
    public int compare(IplCsvDto p1, IplCsvDto p2) {
        int i=0;
        if(p1.allWickets!=0) {
            i = (p1.runs * p1.allWickets) - (p2.runs * p2.allWickets);
        }
        return i;
    }

}
