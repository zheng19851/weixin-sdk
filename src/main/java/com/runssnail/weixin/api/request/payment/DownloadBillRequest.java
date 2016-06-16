package com.runssnail.weixin.api.request.payment;

import com.runssnail.weixin.api.domain.payment.BillType;
import com.runssnail.weixin.api.response.payment.DownloadBillResponse;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 下载对账单
 * <p>
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6
 * <p>
 * Created by zhengwei on 16/6/8.
 */
public class DownloadBillRequest extends PaymentRequest<DownloadBillResponse> {

    private static final long serialVersionUID = 7916766681381280070L;

    /**
     * 对账单日期	bill_date	是	String(8)	20140603	下载对账单的日期，格式：20140603
     */
    private String billDate;

    /**
     * 账单类型	bill_type	否	String(8)	ALL
     *
     * ALL，返回当日所有订单信息，默认值
     * SUCCESS，返回当日成功支付的订单
     * REFUND，返回当日退款订单
     */
    private BillType billType;

    public DownloadBillRequest(String billDate) {
        this.billDate = billDate;
        billType = BillType.ALL;
    }

    /**
     *
     * @param billDate 对账单日期
     * @param billType 账单类型
     */
    public DownloadBillRequest(String billDate, BillType billType) {
        this.billDate = billDate;
        this.billType = billType;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    @Override
    public String getApiUrl() {
        return "https://api.mch.weixin.qq.com/pay/downloadbill";
    }

    @Override
    public Map<String, Object> getParams() {

        SortedMap<String, Object> params = new TreeMap<>();
        params.put("bill_date", this.billDate);

        if (this.billType != null) {
            params.put("bill_type", this.billType.getCode());
        }

        return params;
    }

    @Override
    public Class<DownloadBillResponse> getResponseClass() {
        return DownloadBillResponse.class;
    }
}
