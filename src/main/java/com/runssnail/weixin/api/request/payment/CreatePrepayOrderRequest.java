package com.runssnail.weixin.api.request.payment;

import com.runssnail.weixin.api.common.SignUtils;
import com.runssnail.weixin.api.domain.payment.TradeType;
import com.runssnail.weixin.api.exception.WeiXinApiRuleException;
import com.runssnail.weixin.api.internal.utils.DateUtil;
import com.runssnail.weixin.api.response.payment.CreatePrepayOrderResponse;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.apache.commons.lang.Validate.notNull;

/**
 * 创建预支付单请求
 *
 * @author zhengwei
 */
public class CreatePrepayOrderRequest extends PaymentRequest<CreatePrepayOrderResponse> {

    /**
     *
     */
    private static final long serialVersionUID = 4575293546194222001L;

    private String appId;

    /**
     * ΢商户id
     */
    private String merchantId;

    /**
     * ΢支付签名秘钥
     */
    private String paySignKey;

    /**
     * 商品描述，必填
     */
    private String productDesc;

    /**
     * 系统订单号，必填
     */
    private String orderId;

    /**
     * 总金额，必填
     */
    private Long totalFee;

    /**
     * 订单生成的机器IP，必填
     */
    private String ip;

    /**
     * 接收微信支付成功通知，必填
     */
    private String notifyUrl;

    /**
     * JSAPI、NATIVE、APP，必填
     */
    private TradeType tradeType;

    /**
     * 用户在商户appid下的唯一标识，trade_type为JSAPI时，必传
     */
    private String openId;

    // ================================下面的参数非必填===========================================

    /**
     * 只在trade_type为NATIVE时需要填写，此id为二维码中包含的商品ID
     */
    private String productId;

    /**
     * 微信支付分配的终端设备号， 非必填
     */
    private String deviceInfo;

    /**
     * 附加数据，非必填
     */
    private String attach;

    /**
     * 订单生成时间，非必填
     * <p/>
     * 格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务器
     */
    private Date createTime;

    /**
     * 订单失效时间，非必填
     * <p/>
     * 格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。时区为GMT+8 beijing。该时间取自商户服务器
     */
    private Date expireTime;

    /**
     * 商品标记，该字段不能随便填，非必填
     */
    private String goodsTag;

    public CreatePrepayOrderRequest() {

    }

    public CreatePrepayOrderRequest(String appId, String merchantId, String paySignKey) {
        this.appId = appId;
        this.merchantId = merchantId;
        this.paySignKey = paySignKey;
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

    public String getPaySignKey() {
        return paySignKey;
    }

    public void setPaySignKey(String paySignKey) {
        this.paySignKey = paySignKey;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    @Override
    public String getApiUrl() {
        return "https://api.mch.weixin.qq.com/pay/unifiedorder";
    }

    @Override
    public Map<String, Object> getParams() {
        SortedMap params = getParams(this.appId, this.merchantId);

//        // 创建sign
//        String sign = SignUtils.buildSign(params, this.paySignKey, SignType.MD5);
//        params.put("sign", sign);

        return params;
    }

    private SortedMap<String, String> getParams(String appId, String merchantId) {
        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("appid", appId);
        params.put("mch_id", merchantId); // 商户号

        if (StringUtils.isNotBlank(this.deviceInfo)) {
            params.put("device_info", this.deviceInfo); // 设备号
        }
        params.put("nonce_str", SignUtils.buildNonce());
        params.put("body", this.productDesc); // 商品描述
        if (StringUtils.isNotBlank(this.attach)) {
            params.put("attach", this.attach); // 附加数据，原样返回
        }
        params.put("out_trade_no", this.orderId); // 商户系统内部的订单号
        params.put("total_fee", String.valueOf(this.totalFee)); // 单位分
        params.put("spbill_create_ip", this.ip); // ip
        if (this.createTime != null) {
            params.put("time_start", DateUtil.getDateTime("yyyyMMddHHmmss", this.createTime)); // 订单生成时间，格式为yyyyMMddHHmmss
        }

        if (this.expireTime != null) {
            params.put("time_expire", DateUtil.getDateTime("yyyyMMddHHmmss", this.expireTime)); // 订单失效时间，格式为yyyyMMddHHmmss
        }

        if (StringUtils.isNotBlank(this.goodsTag)) {
            params.put("goods_tag", this.goodsTag); // 商品标记
        }

        params.put("notify_url", this.notifyUrl);
        params.put("trade_type", this.tradeType.getVal()); // JSAPI、NATIVE、APP
        params.put("openid", this.openId); // 用户在商户appid下的唯一标识，trade_type为JSAPI时，此参数必传

        if (StringUtils.isNotBlank(this.productId)) {
            params.put("product_id", this.productId); // 商品ID, 只在trade_type为NATIVE时需要填写
        }

        return params;
    }

    @Override
    public Class<CreatePrepayOrderResponse> getResponseClass() {
        return CreatePrepayOrderResponse.class;
    }

    @Override
    public void doCheck() throws WeiXinApiRuleException {
        // 校验参数

        notNull(this.productDesc, "productDesc is required");

        notNull(this.orderId, "orderId is required");
        notNull(this.totalFee, "totalFee is required");

        notNull(this.ip, "ip is required");
        notNull(this.notifyUrl, "notifyUrl is required");
        notNull(this.tradeType, "tradeType is required");

        if (this.tradeType.isJsApi()) {
            notNull(this.openId, "openId is required");
        }

    }

}
