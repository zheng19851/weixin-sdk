#weixin-sdk

1、微信api接口WeiXinClient和WeiXinService

    1.1 DefaultWeiXinClient是对微信api的具体实现

    使用方式:

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

    1.2 WeiXinService

    // 下面是获取菜单demo

    // 使用内存保存access token, 不建议在生产环境使用, 多台机器时, access token会有问题
    MemoryAccessTokenService accessTokenService = new MemoryAccessTokenService();
    accessTokenService.setWeiXinClient(weiXinClient);

    // WeiXinService默认实现
    DefaultWeiXinService weiXinService = new DefaultWeiXinService();
    weiXinService.setWeiXinClient(weiXinClient);
    // 设置accessTokenService
    weiXinService.setAccessTokenService(accessTokenService);

    // 获取菜单
    GetMenuResponse getMenuResponse = weiXinService.execute(new GetMenuRequest());
    if (getMenuResponse.isSuccess()) {
        //
    }

    注意: 实际开发过程中, 调用微信接口, 建议使用WeiXinService, 此接口封装了access token的获取和存储, 支持本地内存和redis

2、微信支付api接口WeiXinPaymentClient

    使用方式:
        String appId = ""; // appid
        String mchId = ""; // 商户号
        String paySignKey = ""; // 支付密钥

        String certPath = ""; // 证书
        String certPassword = ""; // 证书密码

        DefaultWeiXinPaymentClient paymentClient = new DefaultWeiXinPaymentClient(appId, mchId, paySignKey, certPath, certPassword);

        // 查询企业付款订单
        String orderNo = "32739393939338332739393939338335";
        GetTransferInfoRequest request = new GetTransferInfoRequest(orderNo);

        GetTransferInfoResponse response = paymentClient.execute(request);

        paymentClient.close();