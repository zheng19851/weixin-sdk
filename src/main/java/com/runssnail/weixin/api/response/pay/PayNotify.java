package com.runssnail.weixin.api.response.pay;

import com.runssnail.weixin.api.internal.util.DateUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.text.ParseException;
import java.util.Date;

/**
 * 微信支付通知对象
 * <p>
 * Created by zhengwei on 16/6/8.
 */
@XStreamAlias("xml")
public class PayNotify extends PayResponse {

    private static final long serialVersionUID = 18694202175314349L;

    /**
     * 是否关注公众账号	is_subscribe	否	String(1)	Y	用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
     */
    @XStreamAlias("is_subscribe")
    private String isSubscribe;

    /**
     * 交易类型	trade_type	是	String(16)	JSAPI	JSAPI、NATIVE、APP
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 付款银行	bank_type	是	String(16)	CMC	银行类型，采用字符串类型的银行标识，银行类型见银行列表
     */
    @XStreamAlias("bank_type")
    private String bankType;
    /**
     * 订单金额	total_fee	是	Int	100	订单总金额，单位为分
     */
    @XStreamAlias("total_fee")
    private String totalFee;

    /**
     * 应结订单金额	settlement_total_fee	否	Int	100	应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    @XStreamAlias("settlement_total_fee")
    private Long settlementTotalFee;

    /**
     * 货币种类	fee_type	否	String(8)	CNY	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * 现金支付金额	cash_fee	是	Int	100	现金支付金额订单现金支付金额，详见支付金额
     */
    @XStreamAlias("cash_fee")
    private Long cashFee;

    /**
     * 现金支付货币类型	cash_fee_type	否	String(16)	CNY	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    @XStreamAlias("cash_fee_type")
    private String cashFeeType;

    /**
     * 代金券金额	coupon_fee	否	Int	10	代金券金额<=订单金额，订单金额-代金券金额=现金支付金额，详见支付金额
     */
    @XStreamAlias("coupon_fee")
    private Long couponFee;

    /**
     * 代金券使用数量	coupon_count	否	Int	1	代金券使用数量
     */
    @XStreamAlias("coupon_count")
    private Integer couponCount;

//    代金券类型	coupon_type_$n	否	Int	CASH
//    CASH--充值代金券
//    NO_CASH---非充值代金券
//    订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0
//    代金券ID	coupon_id_$n	否	String(20)	10000	代金券ID,$n为下标，从0开始编号
//    单个代金券支付金额	coupon_fee_$n	否	Int	100	单个代金券支付金额,$n为下标，从0开始编号

    /**
     * 微信支付订单号	transaction_id	是	String(32)	1217752501201407033233368018	微信支付订单号
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号	out_trade_no	是	String(32)	1212321211201407033568112322	商户系统的订单号，与请求一致。
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    /**
     * 商家数据包	attach	否	String(128)	123456	商家数据包，原样返回
     */
    private String attach;

    /**
     * 支付完成时间	time_end	是	String(14)	20141030133525	支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     */
    @XStreamAlias("time_end")
    private String timeEnd;

    public Date getPayTime() {
        try {
            return DateUtil.convertStringToDate("yyyyMMddHHmmss", this.timeEnd);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
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

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public Long getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(Long couponFee) {
        this.couponFee = couponFee;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
