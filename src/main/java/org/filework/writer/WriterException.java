package org.filework.writer;

public class WriterException extends Exception {
    public String errorMsg;

    public WriterException(String msg){
        this.errorMsg = msg;
    }

}
