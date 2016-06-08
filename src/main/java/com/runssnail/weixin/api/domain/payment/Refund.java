package com.runssnail.weixin.api.domain.payment;

import com.runssnail.weixin.api.domain.BaseDomain;

/**
 * 退款
 * <p>
 * Created by zhengwei on 16/6/8.
 */
public class Refund extends BaseDomain {

    private static final long serialVersionUID = -7360366501764226724L;

    /**
     * 商户退款单号	out_refund_no_$n	是	String(32)	1217752501201407033233368018	商户退款单号
     */
    private String outRefundNo;

    /**
     * 微信退款单号	refund_id_$n	是	String(28)	1217752501201407033233368018	微信退款单号
     */
    private String refundId;

    /**
     * 退款渠道	refund_channel_$n	否	String(16)	ORIGINAL
     * ORIGINAL—原路退款
     * BALANCE—退回到余额
     */
    private String refundChannel;

    /**
     * 申请退款金额	refund_fee_$n	是	Int	100	退款总金额,单位为分,可以做部分退款
     */
    private Integer refundFee;

    /**
     * 退款金额	settlement_refund_fee_$n	否	Int	100	退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    private Integer settlementRefundFee;

    /**
     * 代金券类型	coupon_type_$n	否	Int	CASH
     * CASH--充值代金券
     * NO_CASH---非充值代金券
     * 订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0
     */
    private Integer couponType;

    /**
     * 代金券退款金额	coupon_refund_fee_$n	否	Int	100	代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
     */
    private Integer couponRefundFee;

    /**
     * 退款代金券使用数量	coupon_refund_count_$n	否	Int	1	退款代金券使用数量 ,$n为下标,从0开始编号
     */
    private Integer couponRefundcount;

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

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public Integer getSettlementRefundFee() {
        return settlementRefundFee;
    }

    public void setSettlementRefundFee(Integer settlementRefundFee) {
        this.settlementRefundFee = settlementRefundFee;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Integer getCouponRefundFee() {
        return couponRefundFee;
    }

    public void setCouponRefundFee(Integer couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    public Integer getCouponRefundcount() {
        return couponRefundcount;
    }

    public void setCouponRefundcount(Integer couponRefundcount) {
        this.couponRefundcount = couponRefundcount;
    }
}
