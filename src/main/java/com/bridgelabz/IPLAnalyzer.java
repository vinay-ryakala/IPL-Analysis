package com.bridgelabz;

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
    public List<IPLBatting> getMax4And6s(String filePath) throws IPLAnalyserException {
        runsCSVList = this.loadRunsCSV(filePath);
        List<IPLBatting> max4sAnd6sList = runsCSVList.stream().sorted(Comparator.comparingDouble(i -> i.foursCollected + i.sixesCollected))
                .collect(Collectors.toList());
        Collections.reverse(max4sAnd6sList);
        return max4sAnd6sList;
    }
    public List<IPLBatting> getBestStrikeRateWith6sAnd4s(String filePath) throws IPLAnalyserException {
        runsCSVList = this.loadRunsCSV(filePath);
        int mostNumBoundaries = runsCSVList.stream().map(i -> i.foursCollected + i.sixesCollected).max(Integer::compare).get();
        List<IPLBatting> max4sAnd6sList = runsCSVList.stream().filter(i -> i.foursCollected + i.sixesCollected == mostNumBoundaries)
                .collect(Collectors.toList());

        double HighestStrikeRate = max4sAnd6sList.stream().map(i -> i.strikeRate).max(Double::compare).get();
        List<IPLBatting> maxStrikeRateList = max4sAnd6sList.stream().filter(i -> i.strikeRate == HighestStrikeRate)
                .collect(Collectors.toList());
        return maxStrikeRateList;
    }
    public List<IPLBatting> getHigestAverageWithBestStrikeRates(String filePath) throws IPLAnalyserException {
        runsCSVList = this.loadRunsCSV(filePath);
        Double HighestAverage = runsCSVList.stream().map(i -> i.average).max(Double::compare).get();
        List<IPLBatting> maxAverageList = runsCSVList.stream().filter(i -> i.average == HighestAverage)
                .collect(Collectors.toList());

        double HighestStrikeRate = maxAverageList.stream().map(i -> i.strikeRate).max(Double::compare).get();
        List<IPLBatting> maxStrikeRateList = maxAverageList.stream().filter(i -> i.strikeRate == HighestStrikeRate)
                .collect(Collectors.toList());
        return maxStrikeRateList;
    }
    public List<IPLBatting> getBatsmenWithHigestRunsWithBestAverage(String filePath) throws IPLAnalyserException {
        runsCSVList = this.loadRunsCSV(filePath);
        int maxRuns = runsCSVList.stream().map(i -> i.runsScored).max(Integer::compare).get();
        List<IPLBatting> maxRunsList = runsCSVList.stream().filter(i -> i.runsScored == maxRuns)
                .collect(Collectors.toList());

        double highestAverage = maxRunsList.stream().map(i -> i.average).max(Double::compare).get();
        List<IPLBatting> maximumAvgList = maxRunsList.stream().filter(i -> i.average == highestAverage)
                .collect(Collectors.toList());

        return maximumAvgList;
    }
}
