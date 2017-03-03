package com.runssnail.weixin.api.internal.http;

import com.runssnail.weixin.api.internal.util.HttpsClient;

/**
 * 微信支付http client
 * <p>
 * Created by zhengwei on 16/6/8.
 */
public class PayHttpClient extends DefaultHttpClient {

    private HttpsClient httpsClient;

    public PayHttpClient(String certPath, String certPassword) {
        super();
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
    public void init() {

    }

    @Override
    public void close() {
        if (httpsClient != null) {
            this.httpsClient.close();
        }
    }
}
