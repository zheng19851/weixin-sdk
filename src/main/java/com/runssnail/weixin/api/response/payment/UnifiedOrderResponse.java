package com.runssnail.weixin.api.response.payment;

import com.runssnail.weixin.api.domain.payment.TradeType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 统一下单响应对象
 *
 * @author zhengwei
 */
public class UnifiedOrderResponse extends PaymentResponse {

    /**
     *
     */
    private static final long serialVersionUID = 2607756506581018933L;

    /**
     * 预支付单id
     */
    @XStreamAlias("prepay_id")
    private String            prepayId;

    /**
     * 交易类型
     *
     * @see TradeType
     */
    @XStreamAlias("trade_type")
    private String            tradeType;

    /**
     * trade_type为NATIVE是有返回，此参数可直接生成二维码展示出来进行扫码支付
     */
    @XStreamAlias("code_url")
    private String            codeUrl;

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}
