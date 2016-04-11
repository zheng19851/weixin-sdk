#weixin-sdk

WeiXinClient是对微信api的具体实现

// =====下面是获取access token的列子=====
String appId = "";
String appSecret = "";

WeiXinClient weiXinClient = new DefaultWeiXinClient(appId, appSecret);
try {
    GetAccessTokenRequest req = new GetAccessTokenRequest(appId, appSecret);

    Response res = weiXinClient.execute(req);

    System.out.println(res);
} finally {
    if (weiXinClient != null) {
        weiXinClient.close();
    }
}

// ===========

实际开发过程中,可以通过WeiXinService来调用具体的接口,此接口封装了access token的获取和存储