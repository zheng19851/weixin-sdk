package com.runssnail.weixin.api.response.payment;

import com.runssnail.weixin.api.domain.payment.Refund;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * 退款查询响应对象
 *
 * Created by zhengwei on 16/6/8.
 */
public class RefundQueryResponse extends PaymentResponse {

    private static final long serialVersionUID = -2312702348615258625L;

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
     * 退款笔数	refund_count	是	Int	1	退款记录数
     */
    private Integer refundCount;

    private List<Refund> refunds;

//    商户退款单号	out_refund_no_$n	是	String(32)	1217752501201407033233368018	商户退款单号
//    微信退款单号	refund_id_$n	是	String(28)	1217752501201407033233368018	微信退款单号
//    退款渠道	refund_channel_$n	否	String(16)	ORIGINAL
//    ORIGINAL—原路退款
//    BALANCE—退回到余额
//    申请退款金额	refund_fee_$n	是	Int	100	退款总金额,单位为分,可以做部分退款
//    退款金额	settlement_refund_fee_$n	否	Int	100	退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
//    代金券类型	coupon_type_$n	否	Int	CASH
//    CASH--充值代金券
//    NO_CASH---非充值代金券
//    订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0
//    代金券退款金额	coupon_refund_fee_$n	否	Int	100	代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
//    退款代金券使用数量	coupon_refund_count_$n	否	Int	1	退款代金券使用数量 ,$n为下标,从0开始编号
//    退款代金券批次ID	coupon_refund_batch_id_$n_$m	否	String(20)	100	退款代金券批次ID ,$n为下标，$m为下标，从0开始编号
//    退款代金券ID	coupon_refund_id_$n_$m	否	String(20)	10000 	退款代金券ID, $n为下标，$m为下标，从0开始编号
//    单个退款代金券支付金额	coupon_refund_fee_$n_$m	否	Int	100	单个退款代金券支付金额, $n为下标，$m为下标，从0开始编号
//    退款状态	refund_status_$n	是	String(16)	SUCCESS
//    退款状态：
//    SUCCESS—退款成功
//    FAIL—退款失败
//    PROCESSING—退款处理中
//    NOTSURE—未确定，需要商户原退款单号重新发起
//    CHANGE—转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
//    退款入账账户	refund_recv_accout_$n	是	String(64)	招商银行信用卡0403	取当前退款单的退款入账方
//    1）退回银行卡：
//    {银行名称}{卡类型}{卡尾号}
//    2）退回支付用户零钱:
//    支付用户零钱


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

    public Integer getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(Integer refundCount) {
        this.refundCount = refundCount;
    }

    public List<Refund> getRefunds() {
        return refunds;
    }

    public void setRefunds(List<Refund> refunds) {
        this.refunds = refunds;
    }
}
