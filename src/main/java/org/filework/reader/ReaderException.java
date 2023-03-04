package org.filework.reader;

public class ReaderException extends Exception {
    public String errorMessage;

    public ReaderException(String msg){
        this.errorMessage = msg;
    }
}
