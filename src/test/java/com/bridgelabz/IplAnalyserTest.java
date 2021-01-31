package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IplAnalyserTest {
    private static final String MOST_RUN_CSV_FILE = "C:\\Users\\vinay\\IdeaProjects\\IplAnalyser\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
    private static final String MOST_WICKET_CSV_FILE = "C:\\Users\\vinay\\IdeaProjects\\IplAnalyser\\src\\test\\resources\\IPL2019FactsheetMostWkts.csv";
    IPLAnalyzer iplLeagueAnalysis = new IPLAnalyzer();

    @Test
    public void givenMostRunCSVFileReturnsCorrectRecords() {
        try {
            List numOfRecords = iplLeagueAnalysis.loadRunsCSV(MOST_RUN_CSV_FILE);
            Assert.assertEquals(101, numOfRecords.size());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMostWicketCSVFileReturnsCorrectRecords() {
        try {
            List numOfRecords = iplLeagueAnalysis.loadWicketsCSV(MOST_WICKET_CSV_FILE);
            Assert.assertEquals(86, numOfRecords.size());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMostRunCSVFileReturnsTopBattingAverages() throws IPLAnalyserException {
        List<IPLBatting> averageListTopBatsmen = iplLeagueAnalysis.getTopBatsmenAverages(MOST_RUN_CSV_FILE);
        Assert.assertEquals(83.2, averageListTopBatsmen.get(0).average, 0.0);
        Assert.assertEquals("MS Dhoni", averageListTopBatsmen.get(0).playerName);
    }

    @Test
    public void givenMostRunCSVFileReturnsTop3StrikeRates() throws IPLAnalyserException {
        List<IPLBatting> strikeRateTopBatsmen = iplLeagueAnalysis.getTopStrikeRate(MOST_RUN_CSV_FILE);
        Assert.assertEquals(333.33, strikeRateTopBatsmen.get(0).strikeRate, 0.0);
        Assert.assertEquals("Ishant Sharma", strikeRateTopBatsmen.get(0).playerName);
    }

    @Test
    public void givenMostRunCSVFileReturnsMost4sAnd6s() throws IPLAnalyserException {
        List<IPLBatting> strikeRateTopBatsmen = iplLeagueAnalysis.getMax4And6s(MOST_RUN_CSV_FILE);
        Assert.assertEquals("Andre Russell", strikeRateTopBatsmen.get(0).playerName);
    }
    @Test
    public void givenMostRunCSVFileReturnsBestStrikeRatesWith6sAnd4s()  {
      try {
          List<IPLBatting> strikeRateTopBatsmen = iplLeagueAnalysis.getBestStrikeRateWith6sAnd4s(MOST_RUN_CSV_FILE);
          Assert.assertEquals("Andre Russell", strikeRateTopBatsmen.get(0).playerName);
      }catch (IPLAnalyserException e){ e.printStackTrace();}
    }
    @Test
    public void givenMostRunCSVFileReturnsBestStrikeRatesWithHigestRuns()  {
        try {
            List<IPLBatting> topStrikeRateHigestBatsmen = iplLeagueAnalysis.getHigestAverageWithBestStrikeRates(MOST_RUN_CSV_FILE);
            Assert.assertEquals("MS Dhoni", topStrikeRateHigestBatsmen.get(0).playerName);
        }catch (IPLAnalyserException ignored){}
    }
    @Test
    public void givenMostRunCSVFileReturnsCricketersWithMaximumRunWithBestAverages()  {
        try {
            List<IPLBatting> bestAverageHigestRunsBatsmen = iplLeagueAnalysis.getBatsmenWithHigestRunsWithBestAverage(MOST_RUN_CSV_FILE);
            Assert.assertEquals("David Warner ", bestAverageHigestRunsBatsmen.get(0).playerName);
        }catch (IPLAnalyserException ignored){}
    }
    @Test
    public void givenMostWicketsCSVFileReturnsTopBowlingAverages(){
        try {
            List<IPLBowling> averageList = iplLeagueAnalysis.getTopBowlerAverages(MOST_WICKET_CSV_FILE);
            Assert.assertEquals("Krishnappa Gowtham", averageList.get(0).playerName);
        }catch (IPLAnalyserException e){
            e.printStackTrace();
        }
    }
    @Test
    public void givenMostWicketsCSVFileReturnsTopBowlingStrikeRates() {
        try {
            List<IPLBowling> averageList = iplLeagueAnalysis.getTopBowlerStrikeRate(MOST_WICKET_CSV_FILE);
            Assert.assertEquals("Krishnappa Gowtham", averageList.get(0).playerName);
        }catch (IPLAnalyserException e){ e.printStackTrace();}
    }
    @Test
    public void givenMostWicketsCSVFileReturnsTopBowlingEconomy() {
        try {
            List<IPLBowling> economyList = iplLeagueAnalysis.getTopBestEconomy(MOST_WICKET_CSV_FILE);
            Assert.assertEquals(5.5, economyList.get(0).economy,0.0);
        }catch (IPLAnalyserException e){ e.printStackTrace();}
    }
    @Test
    public void givenMostWicketsCSVFileReturnsTopBowlingStrikeRateand4wsand5ws() {
        try {
            List<IPLBowling> economyList = iplLeagueAnalysis.getBolwerWithBestStrikeRatesWith5wAnd4w(MOST_WICKET_CSV_FILE);
            Assert.assertEquals("Lasith Malinga", economyList.get(0).playerName);
        }catch (IPLAnalyserException e){ e.printStackTrace();}
    }
    @Test
    public void givenMostWicketCSVFileReturnsGreatAverageWithBestStrikeRates(){
        try {
            List<IPLBowling> economyList = iplLeagueAnalysis.getGreatAverageWithBestStrikeRatesBowler(MOST_WICKET_CSV_FILE);
            Assert.assertEquals("Anukul Roy", economyList.get(0).playerName);
        }catch (IPLAnalyserException e){ e.printStackTrace();}
    }
}

