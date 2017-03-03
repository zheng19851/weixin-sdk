package com.runssnail.weixin.api.response.pay;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 退款响应对象
 * <p>
 * Created by zhengwei on 16/6/8.
 */
@XStreamAlias("xml")
public class RefundResponse extends PayResponse {

    /**
     * 微信支付订单号	transaction_id	是	String(32)	1009660380201506130728806387	微信支付订单号
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号	out_trade_no	是	String(32)	20150806125346	商户系统的订单号，与请求一致。
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 商户退款单号	out_refund_no	是	String(32)	1217752501201407033233368018	商户退款单号
     */
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * 微信退款单号	refund_id	是	String(28)	1217752501201407033233368018	微信退款单号
     */
    @XStreamAlias("refund_id")
    private String refundId;

    /**
     * 退款渠道	refund_channel	否	String(16)	ORIGINAL
     * ORIGINAL—原路退款
     * BALANCE—退回到余额
     */
    @XStreamAlias("refund_channel")
    private String refundChannel;

    /**
     * 申请退款金额	refund_fee	是	Int	100	退款总金额,单位为分,可以做部分退款
     */
    @XStreamAlias("refund_fee")
    private Long refundFee;

    /**
     * 订单金额	total_fee	是	Int	100	订单总金额，单位为分，只能为整数，详见支付金额
     */
    @XStreamAlias("total_fee")
    private Long totalFee;

    /**
     * 应结订单金额	settlement_total_fee	是	Int	100	应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    @XStreamAlias("settlement_total_fee")
    private Long settlementTotalFee;

    /**
     * 订单金额货币种类	fee_type	否	String(8)	CNY	订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * 现金支付金额	cash_fee	是	Int	100	现金支付金额，单位为分，只能为整数，详见支付金额
     */
    @XStreamAlias("cash_fee")
    private Long cashFee;

    /**
     * 现金退款金额	cash_refund_fee	否	Int	100	现金退款金额，单位为分，只能为整数，详见支付金额
     */
    @XStreamAlias("cash_refund_fee")
    private String cashRefundFee;


    //    退款金额	settlement_refund_fee_$n	是	Int	100	退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额

//    代金券类型	coupon_type_$n	否	String(8)	CASH
//    CASH--充值代金券
//    NO_CASH---非充值代金券
//    订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0
//    代金券退款金额	coupon_refund_fee_$n	否	Int	100	代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
//    退款代金券使用数量	coupon_refund_count_$n	否	Int	1	退款代金券使用数量 ,$n为下标,从0开始编号
//    退款代金券批次ID	coupon_refund_batch_id_$n_$m	否	String(20)	100	退款代金券批次ID ,$n为下标，$m为下标，从0开始编号
//    退款代金券ID	coupon_refund_id_$n_$m	否	String(20)	10000 	退款代金券ID, $n为下标，$m为下标，从0开始编号
//    单个退款代金券支付金额	coupon_refund_fee_$n_$m	否	Int	100	单个退款代金券支付金额, $n为下标，$m为下标，从0开始编号


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

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    public Long getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Long refundFee) {
        this.refundFee = refundFee;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(Long settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Long getCashFee() {
        return cashFee;
    }

    public void setCashFee(Long cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashRefundFee() {
        return cashRefundFee;
    }

    public void setCashRefundFee(String cashRefundFee) {
        this.cashRefundFee = cashRefundFee;
    }
}
