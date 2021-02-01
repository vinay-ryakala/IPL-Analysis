package com.bridgelabz;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    public List<IPLBowling> getTopBowlerAverages(String filePath) throws IPLAnalyserException {
       wicketsCSVList = this.loadWicketsCSV(filePath);
        List<IPLBowling> sortedAvgList = wicketsCSVList.stream()
                .sorted(Comparator.comparingDouble(player -> player.average))
                .collect(Collectors.toList());
        Collections.reverse(sortedAvgList);
        return sortedAvgList;
    }
    public List<IPLBowling> getTopBowlerStrikeRate(String filePath) throws IPLAnalyserException {
        wicketsCSVList = this.loadWicketsCSV(filePath);
        List<IPLBowling> sortedBowlingStrikeRateList = wicketsCSVList.stream()
                .sorted(Comparator.comparingDouble(player -> player.strikeRate))
                .collect(Collectors.toList());
        Collections.reverse(sortedBowlingStrikeRateList);
        return sortedBowlingStrikeRateList;
    }
    public List<IPLBowling> getTopBestEconomy(String filePath) throws IPLAnalyserException {
        wicketsCSVList = this.loadWicketsCSV(filePath);
        List<IPLBowling> sortedEconomyList = wicketsCSVList.stream()
                .sorted(Comparator.comparingDouble(player -> player.economy)).collect(Collectors.toList());
        return sortedEconomyList;
    }
    public List<IPLBowling> getBolwerWithBestStrikeRatesWith5wAnd4w(String filePath) throws IPLAnalyserException {
        wicketsCSVList = this.loadWicketsCSV(filePath);
        int mostNum4wAnd5w = wicketsCSVList.stream().map(i -> i.fourWicketHaul + i.fiveWicketHaul).max(Integer::compare).get();
        List<IPLBowling> max4wAnd5wList =wicketsCSVList.stream().filter(i -> i.fourWicketHaul + i.fiveWicketHaul == mostNum4wAnd5w)
                .collect(Collectors.toList());

        double maximumStrikeRate = max4wAnd5wList.stream().map(i -> i.strikeRate).max(Double::compare).get();

        List<IPLBowling> StrikeRateList = max4wAnd5wList.stream().filter(i -> i.strikeRate == maximumStrikeRate)
                .collect(Collectors.toList());
        return StrikeRateList;
    }
    public List<IPLBowling> getGreatAverageWithBestStrikeRatesBowler(String filePath) throws IPLAnalyserException {
        wicketsCSVList = this.loadWicketsCSV(filePath);
        Double LowestAverage = wicketsCSVList.stream().map(i -> i.average).min(Double::compare).get();
        List<IPLBowling> minAverageList = wicketsCSVList.stream()
                .filter(i -> i.average == LowestAverage).collect(Collectors.toList());

        double LowestStrikeRate = minAverageList.stream().map(i -> i.strikeRate).min(Double::compare).get();
        List<IPLBowling> lowStrikeRateandMinAverageList = minAverageList.stream()
                .filter(i -> i.strikeRate== LowestStrikeRate).collect(Collectors.toList());

        return lowStrikeRateandMinAverageList;
    }
    public List<IPLBowling> getlistOfMaximumWicketsWithBestAverages(String filePath) throws IPLAnalyserException {
        wicketsCSVList = this.loadWicketsCSV(filePath);
        Integer maxWickets = wicketsCSVList.stream().map(i -> i.wicketsTaken).max(Integer::compare).get();
        List<IPLBowling> maxWicketsList = wicketsCSVList.stream().filter(i -> i.wicketsTaken == maxWickets)
                .collect(Collectors.toList());

        double LowestAvgRate = maxWicketsList.stream().map(i -> i.average).min(Double::compare).get();
        List<IPLBowling> LowestAvgRateList = maxWicketsList.stream()
                .filter(i -> i.average == LowestAvgRate).collect(Collectors.toList());

        return LowestAvgRateList;
    }
    public List<String> getListOfPlayersWithBestBattingAndBowlingAverages(String filePath,String filePath1) throws IPLAnalyserException {
        runsCSVList = this.loadRunsCSV(filePath);
        wicketsCSVList = this.loadWicketsCSV(filePath1);
        List<String> bestplayerList = new ArrayList<>();

        List<IPLBatting> sortedAvgBatsmenList = runsCSVList.stream()
                .sorted(Comparator.comparingDouble(player -> player.average))
                .collect(Collectors.toList());
        Collections.reverse(sortedAvgBatsmenList);

        List<IPLBowling> sortedAvgBowlerList = wicketsCSVList.stream()
                .sorted(Comparator.comparingDouble(player -> player.average))
                .collect(Collectors.toList());
        Collections.reverse(sortedAvgBowlerList);
        for (IPLBatting bat : sortedAvgBatsmenList) {
            for (IPLBowling bowl : sortedAvgBowlerList) {
                if (bat.playerName.equals(bowl.playerName)) {
                    bestplayerList.add(bat.playerName);
                }
            }
        }
        return bestplayerList;
    }
    public List<String> toGetBestAllRounder(String filePath,String filePath1) throws IPLAnalyserException {
        runsCSVList = this.loadRunsCSV(filePath);
        wicketsCSVList = this.loadWicketsCSV(filePath1);

        List<String> bestAllRounderList = new ArrayList<>();

        List<IPLBatting> sortedRunBatsmenList = runsCSVList.stream()
                .sorted(Comparator.comparingDouble(player -> player.runsScored)).collect(Collectors.toList());
        Collections.reverse(sortedRunBatsmenList);
        System.out.println(sortedRunBatsmenList.get(0));
        List<IPLBowling> sortedWicketBowlerList = wicketsCSVList.stream()
                .sorted(Comparator.comparingDouble(player -> player.wicketsTaken)).collect(Collectors.toList());
      Collections.reverse(sortedWicketBowlerList);
        for (IPLBatting bat : sortedRunBatsmenList) {
            for (IPLBowling bowl : sortedWicketBowlerList) {
                if (bat.playerName.equals(bowl.playerName))
                    bestAllRounderList.add(bat.playerName);
            }
        }
        return bestAllRounderList;
    }
}
