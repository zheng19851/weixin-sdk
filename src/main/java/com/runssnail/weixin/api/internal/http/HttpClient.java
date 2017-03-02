package com.runssnail.weixin.api.internal.http;

import com.runssnail.weixin.api.common.Lifecycle;
import com.runssnail.weixin.api.domain.FileItem;

import java.util.Map;

/**
 * http请求接口
 * <p>
 * Created by zhengwei on 16/6/8.
 */
public interface HttpClient extends Lifecycle {

    String doPost(String url, String postData);

    String doPost(String url, String postData, int connectTimeout, int readTimeout);

    String doGet(String url, Map<String, Object> params);

    byte[] doGetBytes(String url, Map<String, Object> params);

    byte[] doPostAndGetBytes(String url, String params, int connectTimeout, int readTimeout);

    byte[] doPostAndGetBytes(String apiUrl, Map<String, Object> params, Map<String, FileItem> fileParams, int connectTimeout, int readTimeout);

    String doPost(String apiUrl, Map<String, Object> params, Map<String, FileItem> fileParams, int connectTimeout, int readTimeout);

}
