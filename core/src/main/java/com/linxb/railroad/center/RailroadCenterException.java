package com.linxb.railroad.center;

public class RailroadCenterException extends RuntimeException {

    public RailroadCenterException() {
        super();
    }

    public RailroadCenterException(String message) {
        super(message);
    }

    public RailroadCenterException(String message, Throwable cause) {
        super(message, cause);
    }

    public RailroadCenterException(Throwable cause) {
        super(cause);
    }

    protected RailroadCenterException(String message, Throwable cause,
                                      boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
