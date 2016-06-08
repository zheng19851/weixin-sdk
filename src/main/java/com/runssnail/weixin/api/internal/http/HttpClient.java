package com.runssnail.weixin.api.internal.http;

import com.runssnail.weixin.api.common.Lifecycle;

import java.util.Map;

/**
 * http请求接口
 *
 * Created by zhengwei on 16/6/8.
 */
public interface HttpClient extends Lifecycle {

    String doPost(String url, String postData);

    String doPost(String url, String postData, int connectTimeout, int readTimeout);

    String doGet(String url, Map<String, Object> params);
}
