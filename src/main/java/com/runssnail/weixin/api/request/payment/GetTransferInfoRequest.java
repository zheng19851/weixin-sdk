package com.runssnail.weixin.api.request.payment;

import com.runssnail.weixin.api.common.utils.SignUtils;
import com.runssnail.weixin.api.response.payment.GetTransferInfoResponse;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 用于商户的企业付款操作进行结果查询
 *
 * Created by zhengwei on 16/4/14.
 */
public class GetTransferInfoRequest extends PaymentRequest<GetTransferInfoResponse> {

    /**
     * 公众账号	mch_appid
     */
    private String appId;

    /**
     * 商户id mchid
     */
    private String merchantId;

    /**
     * 商户订单号 partner_trade_no
     */
    private String orderNo;

    public GetTransferInfoRequest(String appId, String merchantId, String orderNo) {
        this.appId = appId;
        this.merchantId = merchantId;
        this.orderNo = orderNo;
    }

    @Override
    public String getApiUrl() {
        return "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";
    }

    @Override
    public Map<String, Object> getParams() {

//        随机字符串	nonce_str	是	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	String(32)	随机字符串，不长于32位
//        签名	sign	是	C380BEC2BFD727A4B6845133519F3AD6	String(32)	生成签名方式查看3.2.1节
//        商户订单号	partner_trade_no	是	10000098201411111234567890	String(28)	商户调用企业付款API时使用的商户订单号
//        商户号	mch_id	是	10000098	String(32)	微信支付分配的商户号
//        Appid	appid	是	wxe062425f740d30d8	String(32)	商户号的appid

        SortedMap<String, Object> params = new TreeMap<>();
        params.put("appid", appId);
        params.put("mch_id", merchantId); // 商户号

        params.put("partner_trade_no", this.orderNo);
        params.put("nonce_str", SignUtils.buildNonce());

        return params;
    }

    @Override
    public Class<GetTransferInfoResponse> getResponseClass() {
        return GetTransferInfoResponse.class;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
