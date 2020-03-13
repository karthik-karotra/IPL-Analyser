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
    public abstract Map<String, IplCsvDto> loadIplData(String...csvFilePath);


    public <E> Map<String, IplCsvDto> loadIplData(Class<E> cricketCSVClass, String csvFilePath) {
        Map<String, IplCsvDto> iplMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder opencsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> cricketCsvIterator = opencsvBuilder.getCSVFileIterator(reader, cricketCSVClass);
            Iterable<E> cricketCsvIterable = () -> cricketCsvIterator;

            if (cricketCSVClass.getName().equals("com.iplanalyser.IplRunsCSV")) {
                StreamSupport.stream(cricketCsvIterable.spliterator(), false)
                        .map(IplRunsCSV.class::cast)
                        .forEach(mostRunCsv -> iplMap.put(mostRunCsv.playerName, new IplCsvDto(mostRunCsv)));
            } else if (cricketCSVClass.getName().equals("com.iplanalyser.IplBowlingCSV")) {
                StreamSupport.stream(cricketCsvIterable.spliterator(), false)
                        .map(IplBowlingCSV.class::cast)
                        .forEach(mostBowlingCsv -> iplMap.put(mostBowlingCsv.playerName, new IplCsvDto(mostBowlingCsv)));
            }
            return iplMap;
        } catch (IOException e) {
            throw  new IplAnalyserException(e.getMessage(),IplAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);        }

    catch (RuntimeException e) {
        throw  new IplAnalyserException(e.getMessage(),IplAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);        }
}

}
