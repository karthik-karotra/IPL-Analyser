package com.iplanalyser;

import com.csvreader.CSVBuilderFactory;
import com.csvreader.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class IplAnalyser {
    List<CricketCsvDto> iplCSVList;
    Map<SortedField,Comparator<CricketCsvDto>> sortedMap;
    Map<String,CricketCsvDto> cricketCsvDtoMap;


    public IplAnalyser() {
        this.iplCSVList = new ArrayList<>();
        this.sortedMap = new HashMap<>();
        this.cricketCsvDtoMap=new HashMap<>();

        this.sortedMap.put(SortedField.AVERAGE,Comparator.comparing(ipldata -> ipldata.average));
        this.sortedMap.put(SortedField.STRIKE_RATE,Comparator.comparing(ipldata -> ipldata.strikeRate));
        this.sortedMap.put(SortedField.NO_OF_4S_AND_6S,Comparator.comparing(ipldata -> ipldata.noOfFours+ipldata.noOfSixes));
        this.sortedMap.put(SortedField.BEST_FOURS_AND_SIX_WITH_STRIKING_RATE,Comparator.comparing(ipldata -> ipldata.noOfFours+ipldata.noOfSixes));
        this.sortedMap.put(SortedField.BEST_AVG_WITH_STRIKE_RATE,Comparator.comparing(ipldata -> ipldata.average));

        this.sortedMap.put(SortedField.BEST_RUNS_WITH_BEST_AVG,Comparator.comparing(ipldata -> ipldata.runs));
       // this.sortedMap.put(SortedField.AVERAGE,Comparator.comparing(ipldata -> ipldata.));

    }

    public int loadIplData(String csvFilePath) {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplRunsCSV> mostRunCsvIterator = icsvBuilder.getCSVFileIterator(reader,IplRunsCSV.class);
            while (mostRunCsvIterator.hasNext()){
                IplRunsCSV mostRunCsv = mostRunCsvIterator.next();
                this.cricketCsvDtoMap.put(mostRunCsv.playerName,new CricketCsvDto(mostRunCsv));
            }
            iplCSVList = cricketCsvDtoMap.values().stream().collect(Collectors.toList());
            return iplCSVList.size();
        }catch (IOException e){
            throw  new IplAnalyserException(e.getMessage(),IplAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        }
    }
    public int loadIplData1(String csvFilePath) {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplBowlingCSV> mostWicketsCsvIterator = icsvBuilder.getCSVFileIterator(reader,IplBowlingCSV.class);
            while (mostWicketsCsvIterator.hasNext()){
                IplBowlingCSV mostWicketCsv = mostWicketsCsvIterator.next();
                this.cricketCsvDtoMap.put(mostWicketCsv.playerName,new CricketCsvDto(mostWicketCsv));
            }
            iplCSVList = cricketCsvDtoMap.values().stream().collect(Collectors.toList());
            return iplCSVList.size();
        }catch (IOException e){
            throw  new IplAnalyserException(e.getMessage(),IplAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        }
    }
    public String getSortedCricketData(SortedField sortedField) {

        if(iplCSVList == null || iplCSVList.size() == 0){
            throw new IplAnalyserException("No Data",IplAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        this.sort(this.sortedMap.get(sortedField));
        Collections.reverse(iplCSVList);
        String sortedDataJson=new Gson().toJson(iplCSVList);
        return sortedDataJson;
    }

    private void sort(Comparator<CricketCsvDto> iplComparator) {
        for(int i=0;i<iplCSVList.size()-1;i++){
            for(int j=0;j<iplCSVList.size()-i-1;j++){
                CricketCsvDto run1 = this.iplCSVList.get(j);
                CricketCsvDto run2 = this.iplCSVList.get(j+1);
                if(iplComparator.compare(run1,run2)>0){
                    iplCSVList.set(j,run2);
                    iplCSVList.set(j+1,run1);
                }
            }
        }
    }


}
