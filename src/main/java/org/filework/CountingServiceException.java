package org.filework;

public class CountingServiceException extends Exception {
    public String errMsg;

    public CountingServiceException(String err){
        this.errMsg = err;
    }
}
