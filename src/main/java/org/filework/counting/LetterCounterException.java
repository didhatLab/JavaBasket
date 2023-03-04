package org.filework.counting;

public class LetterCounterException extends Exception {
    public String errorMsg;

    public LetterCounterException(String errorMsg){
        this.errorMsg = errorMsg;
    }

}
