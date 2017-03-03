package com.runssnail.weixin.api.request.pay;

import com.runssnail.weixin.api.util.SignUtils;
import com.runssnail.weixin.api.exception.ApiRuleException;
import com.runssnail.weixin.api.internal.support.ApiRuleValidate;
import com.runssnail.weixin.api.response.pay.OrderQueryResponse;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 支付订单查询接口
 *
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2
 * <p>
 * Created by zhengwei on 16/6/8.
 */
public class OrderQueryRequest extends BasePayRequest<OrderQueryResponse> {

    private static final long serialVersionUID = 1322942345381160755L;

    /**
     * 微信订单号	transaction_id	二选一	String(32)	1009660380201506130728806387	微信的订单号，优先使用
     */
    private String transactionId;

    /**
     * 商户订单号	out_trade_no	String(32)	20150806125346	商户系统内部的订单号，当没提供transaction_id时需要传这个。
     */
    private String outTradeNo;

    public OrderQueryRequest() {

    }

    /**
     *
     * @param transactionId 微信订单号
     */
    public OrderQueryRequest(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String getApiUrl() {
        return "https://api.mch.weixin.qq.com/pay/orderquery";
    }

    @Override
    public Map<String, Object> getParams() {

        SortedMap<String, Object> params = new TreeMap<>();

        if (StringUtils.isNotBlank(this.transactionId)) {
            params.put("transaction_id", this.transactionId);
        }

        if (StringUtils.isNotBlank(this.outTradeNo)) {
            params.put("out_trade_no", this.outTradeNo);
        }
        params.put("nonce_str", SignUtils.buildNonce()); // 随机数

        return params;
    }

    @Override
    public Class<OrderQueryResponse> getResponseClass() {
        return OrderQueryResponse.class;
    }

    @Override
    protected void doCheck() throws ApiRuleException {
        ApiRuleValidate.isTrue(StringUtils.isNotBlank(this.transactionId) || StringUtils.isNotBlank(this.outTradeNo), "transaction_id or out_trade_no is required");
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
