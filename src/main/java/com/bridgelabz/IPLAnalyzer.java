package com.bridgelabz;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IPLAnalyzer {
    List<IPLBatting> runsCSVList = null;
    List<IPLBowling> wicketsCSVList = null;

    private static <E> List <E> loadCSVData(String filepath, Class<E> csvClass) throws IPLAnalyserException{
     try {
         Reader reader = Files.newBufferedReader(Paths.get(filepath));
         ICSVBuilder<E> csvBuilder = CSVBuilderFactory.createCSVBuilder();
         return csvBuilder.getList(reader, csvClass);
     }catch (IOException e) {
         throw new IPLAnalyserException(e.getMessage(),
                 IPLAnalyserException.ExceptionType.INVALID_FILE_PATH);
     } catch (RuntimeException | CSVBuilderException e) {
         throw new IPLAnalyserException(e.getMessage(),
                 IPLAnalyserException.ExceptionType.WRONG_FILE);
     }
    }

    public int loadRunsCSV(String filePath) throws IPLAnalyserException{
        runsCSVList = loadCSVData(filePath, IPLBatting.class);
        return runsCSVList.size();
    }

    public int loadWicketsCSV(String filePath) throws IPLAnalyserException {
        wicketsCSVList = loadCSVData(filePath, IPLBowling.class);
        return wicketsCSVList.size();
    }

/*
    public double getTopBattingAvg(String filePath) throws CSVBuilderException, IOException {
        loadRunsCSV(filePath);
        double max = runsCSVList.stream().map(entry -> entry.average).max(Double::compare).get();
        return max;
    }

    public static void main(String[] args) {
        System.out.println("*** Welcome to IPL League Analysis Problem ***");
    }
*/}
