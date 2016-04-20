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

实际开发过程中,调用微信接口,可以使用WeiXinService,此接口封装了access token的获取和存储,支持本地内存和redis


微信支付接口
WeiXinPaymentClient

使用方式:
        String appId = ""; // appid
        String mchId = ""; // 商户号
        String paySignKey = ""; // 支付密钥

        String certPath = ""; // 证书
        String certPassword = ""; // 证书密码

        DefaultWeiXinPaymentClient paymentClient = new DefaultWeiXinPaymentClient(appId, mchId, paySignKey, certPath, certPassword);

        // 查询企业付款订单
        String orderNo = "32739393939338332739393939338335";
        GetTransferInfoRequest request = new GetTransferInfoRequest(appId, mchId, orderNo);

        GetTransferInfoResponse response = paymentClient.execute(request);

        paymentClient.close();