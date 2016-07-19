package com.runssnail.weixin.api.test.common;

import com.runssnail.weixin.api.common.utils.SignUtils;

/**
 * Created by zhengwei on 16/7/19.
 */
public class SignUtilsTest {
    public static void main(String[] args) {
        String body = "<xml><return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<return_msg><![CDATA[OK]]></return_msg>\n" +
                "<appid><![CDATA[wxada6b9ac122390ff]]></appid>\n" +
                "<mch_id><![CDATA[1334006201]]></mch_id>\n" +
                "<nonce_str><![CDATA[bjtcJjnZ8S1YoWqV]]></nonce_str>\n" +
                "<sign><![CDATA[5394F4C6A7664A8A95BD22BCAA5D07E1]]></sign>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<openid><![CDATA[o9dkewEqSw354trjs4sshGFnBois]]></openid>\n" +
                "<is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "<trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<total_fee>10</total_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<transaction_id><![CDATA[4005732001201607199310689261]]></transaction_id>\n" +
                "<out_trade_no><![CDATA[2016071915360821040461]]></out_trade_no>\n" +
                "<attach><![CDATA[]]></attach>\n" +
                "<time_end><![CDATA[20160719153635]]></time_end>\n" +
                "<trade_state><![CDATA[SUCCESS]]></trade_state>\n" +
                "<cash_fee>10</cash_fee>\n" +
                "</xml>";

        boolean validate = SignUtils.validateSign(body, "MgxJjILkONRsd85H5bUqTib02RFhRnS4");

        System.out.println(validate);
    }
}
