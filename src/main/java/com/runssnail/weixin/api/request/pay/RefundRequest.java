package com.runssnail.weixin.api.request.pay;

import com.runssnail.weixin.api.response.pay.RefundResponse;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信支付退款请求
 * <p>
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
 * <p>
 * Created by zhengwei on 16/6/8.
 */
public class RefundRequest extends BasePayRequest<RefundResponse> {
    private static final long serialVersionUID = 2117484980342148758L;

    /**
     * 微信订单号	transaction_id	二选一	String(32)	1009660380201506130728806387	微信的订单号，优先使用
     */
    private String transactionId;

    /**
     * 商户订单号	out_trade_no	String(32)	20150806125346	商户系统内部的订单号，当没提供transaction_id时需要传这个。
     */
    private String outTradeNo;

    /**
     * 商户退款单号	out_refund_no	是	String(32)	1217752501201407033233368018	商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
     */
    private String outRefundNo;

    /**
     * 总金额	total_fee	是	Int	100	订单总金额，单位为分，只能为整数，详见支付金额
     */
    private Long totalFee;

    /**
     * 退款金额	refund_fee	是	Int	100	退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
     */
    private Long refundFee;

    /**
     * 货币种类	refund_fee_type	否	String(8)	CNY	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    private String refundFeeType;

    /**
     * 操作员	op_user_id	是	String(32)	1900000109	操作员帐号, 默认为商户号
     */
    private String opUserId;


    @Override
    public String getApiUrl() {
        return "https://api.mch.weixin.qq.com/secapi/pay/refund";
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
        params.put("out_refund_no", this.outRefundNo);
        params.put("total_fee", this.totalFee);

        params.put("op_user_id", this.opUserId);

        if (StringUtils.isNotBlank(this.refundFeeType)) {
            params.put("refund_fee_type", this.refundFeeType);
        }

        return params;
    }

    @Override
    public Class<RefundResponse> getResponseClass() {
        return RefundResponse.class;
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

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Long refundFee) {
        this.refundFee = refundFee;
    }

    public String getRefundFeeType() {
        return refundFeeType;
    }

    public void setRefundFeeType(String refundFeeType) {
        this.refundFeeType = refundFeeType;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }
}
