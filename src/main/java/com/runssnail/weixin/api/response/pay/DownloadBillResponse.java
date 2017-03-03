package com.runssnail.weixin.api.response.pay;

import com.runssnail.weixin.api.constant.DataType;

/**
 * 下载对账单
 * <p>
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6
 * <p>
 * Created by zhengwei on 16/6/8.
 */
public class DownloadBillResponse extends PayResponse {

//    交易时间,公众账号ID,商户号,子商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,总金额,代金券或立减优惠金额,微信退款单号,商户退款单号,退款金额,代金券或立减优惠退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率
//    `2014-11-1016：33：45,`wx2421b1c4370ec43b,`10000100,`0,`1000,`1001690740201411100005734289,`1415640626,`085e9858e3ba5186aafcbaed1,`MICROPAY,`SUCCESS,`CFT,`CNY,`0.01,`0.0,`0,`0,`0,`0,`,`,`被扫支付测试,`订单额外描述,`0,`0.60%
//            `2014-11-1016：46：14,`wx2421b1c4370ec43b,`10000100,`0,`1000,`1002780740201411100005729794,`1415635270,`085e9858e90ca40c0b5aee463,`MICROPAY,`SUCCESS,`CFT,`CNY,`0.01,`0.0,`0,`0,`0,`0,`,`,`被扫支付测试,`订单额外描述,`0,`0.60%
//    总交易单数,总交易额,总退款金额,总代金券或立减优惠退款金额,手续费总金额
//    `2,`0.02,`0.0,`0.0,`0


    /**
     * 响应数据格式
     *
     * @return
     */
    public DataType getDataType() {
        return DataType.TXT;
    }


}