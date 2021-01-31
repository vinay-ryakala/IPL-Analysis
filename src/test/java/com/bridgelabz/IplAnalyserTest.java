package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class IplAnalyserTest {
    private static final String MOST_RUN_CSV_FILE = "C:\\Users\\vinay\\IdeaProjects\\IplAnalyser\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
    private static final String MOST_WICKET_CSV_FILE = "C:\\Users\\vinay\\IdeaProjects\\IplAnalyser\\src\\test\\resources\\IPL2019FactsheetMostWkts.csv";
    IPLAnalyzer iplLeagueAnalysis = new IPLAnalyzer();

    @Test
    public void givenMostRunCSVFileReturnsCorrectRecords ()  {
          try {
              int numOfRecords = iplLeagueAnalysis.loadRunsCSV(MOST_RUN_CSV_FILE);
              Assert.assertEquals(101, numOfRecords);
          }catch (IPLAnalyserException e){}
    }

   @Test
   public void givenMostWicketCSVFileReturnsCorrectRecords() {
        try {
            int numOfRecords = iplLeagueAnalysis.loadWicketsCSV(MOST_WICKET_CSV_FILE);
            Assert.assertEquals(99, numOfRecords);
        }catch (IPLAnalyserException e){}
  }
}