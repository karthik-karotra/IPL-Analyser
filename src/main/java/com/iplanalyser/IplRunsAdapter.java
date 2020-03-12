package com.iplanalyser;

import com.csvreader.CSVBuilderFactory;
import com.csvreader.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IplRunsAdapter extends IplAdapter {

  /*  @Override
    public Map<String, CricketCsvDto> loadIplData(String... csvFilePath) {
        Map<String,CricketCsvDto> cricketCsvDtoMap =super.loadIplData(IplRunsCSV.class,csvFilePath[0]);
        return cricketCsvDtoMap;
    }*/

  @Override
    public Map<String, IplCsvDto> loadIplData(String... csvFilePath) {
        Map<String, IplCsvDto> cricketCsvDtoMap =null;
        cricketCsvDtoMap =super.loadIplData(IplRunsCSV.class,csvFilePath[0]);
        if(csvFilePath.length>1) {
            this.loadBowlingData(cricketCsvDtoMap, csvFilePath[1]);
        }
        return cricketCsvDtoMap;
    }

    private void loadBowlingData(Map<String, IplCsvDto> cricketCsvDtoMap, String csvFilePath) {
        try(Reader reader= Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder iCsvBuilder= CSVBuilderFactory.createCSVBuilder();
            Iterator<IplBowlingCSV> iterator=iCsvBuilder.getCSVFileIterator(reader,IplBowlingCSV.class);
            Iterable<IplBowlingCSV> iterable=() ->iterator;
            StreamSupport.stream(iterable.spliterator(),false)
                    .filter(iplMostWicketsAdapter -> cricketCsvDtoMap.get(iplMostWicketsAdapter.playerName)!=null )
                    .forEach(mergedData->{cricketCsvDtoMap.get(mergedData.playerName).bowlerAverage=mergedData.average;
                        cricketCsvDtoMap.get(mergedData.playerName).allWickets=mergedData.wickets;});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
