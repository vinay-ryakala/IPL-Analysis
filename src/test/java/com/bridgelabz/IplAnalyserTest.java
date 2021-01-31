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
        }
    }

    @Test
    public void givenMostWicketCSVFileReturnsCorrectRecords() {
        try {
            List numOfRecords = iplLeagueAnalysis.loadWicketsCSV(MOST_WICKET_CSV_FILE);
            Assert.assertEquals(99, numOfRecords.size());
        } catch (IPLAnalyserException e) {
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
}
