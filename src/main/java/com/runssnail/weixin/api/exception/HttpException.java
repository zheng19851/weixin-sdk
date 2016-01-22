package com.runssnail.weixin.api.exception;

/**
 * httpclient HttpException
 *
 * @author zhengwei
 * @since 2014-2-17
 */
public class HttpException extends RuntimeException {

    public HttpException() {
    }

    public HttpException(String msg) {
        super(msg);
    }

    public HttpException(Exception e) {
        super(e);
    }

    public HttpException(String msg, Exception e) {
        super(msg, e);
    }

    /**
     *
     */
    private static final long serialVersionUID = 210878875098375288L;

}
