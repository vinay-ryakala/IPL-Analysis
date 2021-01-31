package com.bridgelabz;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IPLAnalyzer {
    List<IPLBatting> runsCSVList = null;
    List<IPLBowling> wicketsCSVList = null;

    private static <E> List<E> loadCSVData(String filepath, Class<E> csvClass) throws IPLAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filepath));
            ICSVBuilder<E> csvBuilder = CSVBuilderFactory.createCSVBuilder();
            return csvBuilder.getList(reader, csvClass);
        } catch (IOException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.INVALID_FILE_PATH);
        } catch (RuntimeException | CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.WRONG_FILE);
        }
    }

    public List loadRunsCSV(String filePath) throws IPLAnalyserException {
        runsCSVList = loadCSVData(filePath, IPLBatting.class);
        return runsCSVList;
    }

    public List loadWicketsCSV(String filePath) throws IPLAnalyserException {
        wicketsCSVList = loadCSVData(filePath, IPLBowling.class);
        return wicketsCSVList;
    }

    public List<IPLBatting> getTopBatsmenAverages(String filePath) throws IPLAnalyserException {
        runsCSVList = this.loadRunsCSV(filePath);
        List<IPLBatting> sortedAvgList = runsCSVList.stream()
                .sorted(Comparator.comparingDouble(player -> player.average))
                .collect(Collectors.toList());
        Collections.reverse(sortedAvgList);
        return sortedAvgList;
    }

    public List<IPLBatting> getTopStrikeRate(String filePath) throws IPLAnalyserException {
        runsCSVList = this.loadRunsCSV(filePath);
        List<IPLBatting> sortedStrikeRateList = runsCSVList.stream()
                .sorted(Comparator.comparingDouble(player -> player.strikeRate))
                .collect(Collectors.toList());
        Collections.reverse(sortedStrikeRateList);
        return sortedStrikeRateList;
    }
}
