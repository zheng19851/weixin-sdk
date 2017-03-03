package com.runssnail.weixin.api.response.pay;

import com.runssnail.weixin.api.exception.PayApiException;

/**
 * 用于商户的企业付款操作进行结果查询
 *
 * Created by zhengwei on 16/4/14.
 */
public class GetTransferInfoResponse extends PayResponse {

    //    商户单号	partner_trade_no	是	10000098201411111234567890	String(28)	商户使用查询API填写的单号的原路返回.
    private String partner_trade_no;

    //    商户号	mch_id	是	10000098	String(32)	微信支付分配的商户号

    //    付款单号	detail_id	是	1000000000201503283103439304	String(32)	调用企业付款API时，微信系统内部产生的单号
    private String detail_id;

    //    转账状态	status	是	SUCCESS	string(16) SUCCESS:转账成功 FAILED:转账失败 PROCESSING:处理中
    private String status;

    //    失败原因	reason	否	余额不足	String	如果失败则有失败原因
    private String reason;

    //    收款用户openid	openid	是	oxTWIuGaIt6gTKsQRLau2M0yL16E	 	转账的openid
    private String openid;

    //    收款用户姓名	transfer_name	否	马华	String	收款用户姓名
    private String transfer_name;

    //    付款金额	payment_amount	是	5000	int	付款金额单位分）
    private int payment_amount;

    //    转账时间	transfer_time	是	2015-04-21 20:00:00	String	发起转账的时间
    private String transfer_time;

    //    付款描述	desc	是	车险理赔	String	付款时候的描述
    private String desc;

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTransfer_name() {
        return transfer_name;
    }

    public void setTransfer_name(String transfer_name) {
        this.transfer_name = transfer_name;
    }

    public int getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(int payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getTransfer_time() {
        return transfer_time;
    }

    public void setTransfer_time(String transfer_time) {
        this.transfer_time = transfer_time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public void check(String paySignKey) throws PayApiException {
        // ignore
    }
}
