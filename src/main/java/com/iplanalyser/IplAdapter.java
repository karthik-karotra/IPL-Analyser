package com.iplanalyser;

import com.csvreader.CSVBuilderFactory;
import com.csvreader.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IplAdapter extends RuntimeException{
    public abstract Map<String,CricketCsvDto> loadIplData(String...csvFilePath);


    public <E> Map<String,CricketCsvDto> loadIplData(Class<E> cricketCSVClass,String csvFilePath) {
        Map<String, CricketCsvDto> cricketMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder opencsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> cricketCsvIterator = opencsvBuilder.getCSVFileIterator(reader, cricketCSVClass);
            Iterable<E> cricketCsvIterable = () -> cricketCsvIterator;

            if (cricketCSVClass.getName().equals("com.iplanalyser.IplRunsCSV")) {
                StreamSupport.stream(cricketCsvIterable.spliterator(), false)
                        .map(IplRunsCSV.class::cast)
                        .forEach(mostRunCsv -> cricketMap.put(mostRunCsv.playerName, new CricketCsvDto(mostRunCsv)));
            } else if (cricketCSVClass.getName().equals("com.iplanalyser.IplBowlingCSV")) {
                StreamSupport.stream(cricketCsvIterable.spliterator(), false)
                        .map(IplBowlingCSV.class::cast)
                        .forEach(mostBowlingCsv -> cricketMap.put(mostBowlingCsv.playerName, new CricketCsvDto(mostBowlingCsv)));
            }
            return cricketMap;
        } catch (IOException e) {
            throw  new IplAnalyserException(e.getMessage(),IplAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);        }
    }
}
