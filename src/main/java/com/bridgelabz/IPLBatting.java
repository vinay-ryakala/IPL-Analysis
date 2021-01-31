package com.bridgelabz;
import com.opencsv.bean.CsvBindByName;

public class IPLBatting {

    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName(column = "Mat", required = true)
    public int matchesPlayed;

    @CsvBindByName(column = "Inns", required = true)
    public int inningsPlayed;

    @CsvBindByName(column = "NO", required = true)
    public int numberOFNotOuts;

    @CsvBindByName(column = "Runs", required = true)
    public int runsScored;

    @CsvBindByName(column = "HS", required = true)
    public String highestScore;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "BF", required = true)
    public int ballsFaced;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "100", required = true)
    public int hundredsScored;

    @CsvBindByName(column = "50", required = true)
    public int fiftiesScored;

    @CsvBindByName(column = "4s", required = true)
    public int foursCollected;

    @CsvBindByName(column = "6s", required = true)
    public int sixesCollected;

    @Override
    public String toString() {
        return "Player =" + playerName + ", Matches=" + matchesPlayed + ", Innings=" + inningsPlayed + " not outs = "
                + numberOFNotOuts + "runs = " + runsScored + " highest score  = " + highestScore + " average = "
                + average + " ballsfaced = " + ballsFaced + " Strike Rate = " + strikeRate + "100s scored = "
                + hundredsScored + " 50s scored = " + fiftiesScored + "fours = " + foursCollected + " sixes = "
                + sixesCollected;
    }
}