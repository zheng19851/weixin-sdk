package com.runssnail.weixin.api.internal.http;

import com.runssnail.weixin.api.constants.Constants;
import com.runssnail.weixin.api.domain.FileItem;
import com.runssnail.weixin.api.internal.utils.HttpUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by zhengwei on 16/6/8.
 */
public class DefaultHttpClient implements HttpClient {

    /**
     * 连接超时时间，单位毫秒，默认3秒
     */
    private int connectTimeout = Constants.DEFAULT_CONNECT_TIMEOUT;

    /**
     * 读取超时时间，单位毫秒，默认10秒
     */
    private int readTimeout = Constants.DEFAULT_READ_TIMEOUT;

    public DefaultHttpClient(int connectTimeout, int readTimeout) {
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    @Override
    public String doPost(String url, String postData) {
        return this.doPost(url, postData, connectTimeout, readTimeout);
    }

    @Override
    public String doPost(String url, String postData, int connectTimeout, int readTimeout) {
        try {
            return HttpUtils.doPost(url, postData, connectTimeout, readTimeout);
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    @Override
    public String doGet(String url, Map<String, Object> params) {

        try {
            return HttpUtils.doGet(url, params);
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    @Override
    public byte[] doGetBytes(String url, Map<String, Object> params) {
        try {
            return HttpUtils.doGetBytes(url, params);
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    @Override
    public byte[] doPostAndGetBytes(String url, String params, int connectTimeout, int readTimeout) {
        try {
            return HttpUtils.doPostAndGetBytes(url, params, connectTimeout, readTimeout);
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    @Override
    public byte[] doPostAndGetBytes(String apiUrl, Map<String, Object> params, Map<String, FileItem> fileParams, int connectTimeout, int readTimeout) {
        try {
            return HttpUtils.doPostAndGetBytes(apiUrl, params, fileParams, connectTimeout, readTimeout);
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    @Override
    public String doPost(String apiUrl, Map<String, Object> params, Map<String, FileItem> fileParams, int connectTimeout, int readTimeout) {
        try {
            return HttpUtils.doPost(apiUrl, params, fileParams, connectTimeout, readTimeout);
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void close() {

    }
}
