package com.runssnail.weixin.api.test;

import com.runssnail.weixin.api.internal.utils.HttpUtils;

/**
 * Created by zhengwei on 2015/7/23.
 */
public class ApiTest {

    public static void main(String[] args) throws Exception {
        String apiUrl = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
        System.out.println(HttpUtils.doGet(apiUrl, ""));
    }
}
