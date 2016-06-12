package com.runssnail.weixin.api.internal.http;

import com.runssnail.weixin.api.internal.utils.HttpUtils;
import com.runssnail.weixin.api.internal.utils.HttpsClient;

import java.io.IOException;
import java.util.Map;

/**
 * 微信支付http client
 *
 * Created by zhengwei on 16/6/8.
 */
public class PaymentHttpClient implements HttpClient {

    private HttpsClient httpsClient;

    public PaymentHttpClient(String certPath, String certPassword) {
        this.httpsClient = new HttpsClient(certPath, certPassword);
    }

    @Override
    public String doPost(String url, String postData) {
        return httpsClient.doPost(url, postData);
    }

    @Override
    public String doPost(String url, String postData, int connectTimeout, int readTimeout) {
        return httpsClient.doPost(url, postData);
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
    public void init() {

    }

    @Override
    public void close() {
        if (httpsClient != null) {
            this.httpsClient.close();
        }
    }
}
