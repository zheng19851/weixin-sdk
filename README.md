#weixin-sdk

1、微信api接口WeixinClient和WeiXinService

    1.1 DefaultWeixinClient是对微信api的具体实现

        使用方式:

        // =====下面是获取access token的列子=====
        String appId = "";
        String appSecret = "";

        WeixinClient weiXinClient = new DefaultWeixinClient(appId, appSecret);
        try {
            GetAccessTokenRequest req = new GetAccessTokenRequest(appId, appSecret);

            GetAccessTokenResponse res = weiXinClient.execute(req);

            System.out.println(res.getAccessToken());
        } finally {
            // 应用停止后,建议关闭client
            if (weiXinClient != null) {
                weiXinClient.close();
            }
        }

    1.2 WeiXinService, 封装了access token

        // 下面是获取菜单demo

        String appId = ""; // 微信appId
        String appSecret = ""; // 微信appSecret
        WeixinClient weiXinClient = new DefaultWeixinClient(appId, appSecret);

        // 使用内存保存access token, 不建议在生产环境使用, 多台机器时, access token会有问题
        // 生产环境建议使用RedisAccessTokenService
        MemoryAccessTokenService accessTokenManager = new MemoryAccessTokenService();
        accessTokenManager.setWeiXinClient(weiXinClient);

        // WeiXinService默认实现
        DefaultWeiXinService weiXinService = new DefaultWeiXinService();
        weiXinService.setWeixinClient(weiXinClient);
        // 设置accessTokenService
        weiXinService.setAccessTokenService(accessTokenManager);

        // 获取菜单
        GetMenuResponse getMenuResponse = weiXinService.execute(new GetMenuRequest());
        if (getMenuResponse.isSuccess()) {
            // 获取菜单信息
        }

        注意: 实际开发过程中, 调用微信接口, 建议使用WeiXinService, 此接口封装了access token的获取和存储, 支持本地内存和redis

2、微信支付api接口WeixinPayClient

    使用方式:
        String appId = ""; // appid
        String mchId = ""; // 商户号
        String paySignKey = ""; // 支付密钥

        String certPath = ""; // 证书
        String certPassword = ""; // 证书密码

        DefaultWeixinPayClient payClient = new DefaultWeixinPayClient(appId, mchId, paySignKey, certPath, certPassword);

        // 不需要证书
        // DefaultWeixinPayClient payClient = new DefaultWeixinPayClient(appId, mchId, paySignKey);

        // 查询企业付款订单
        String orderNo = "32739393939338332739393939338335";
        GetTransferInfoRequest request = new GetTransferInfoRequest(orderNo);

        GetTransferInfoResponse response = payClient.execute(request);

        payClient.close();