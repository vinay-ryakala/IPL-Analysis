package com.bridgelabz;

public class IPLAnalyserException extends Exception{

    public enum ExceptionType {
        WRONG_FILE, NO_DATA_FOUND, INVALID_FILE_PATH, INVALID_CLASS;
    }

    ExceptionType type;

    public IPLAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}