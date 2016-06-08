package com.runssnail.weixin.api.internal.support;

import com.runssnail.weixin.api.request.payment.CreatePrepayOrderRequest;
import com.thoughtworks.xstream.XStream;

/**
 * 微信支付相应数据帮助类
 *
 * @author zhengwei
 */
public class WeixinPayResponseHelper {


    /**
     *
     * 将xml设置到res对象里
     * @param xml xml数据
     * @param res 对象
     * @return 数据对象
     */
    public static Object getObjectFromXml(String xml, Object res) {
        Object result;

        XStream xStream = new XStream();
        xStream.alias("xml", res.getClass());
        xStream.processAnnotations(res.getClass());
        xStream.ignoreUnknownElements();// 暂时忽略掉一些新增的字段
        result = xStream.fromXML(xml, res);
        return result;
    }

    /**
     * 将xml数据转成指定类型的对象
     *
     * @param xml xml数据
     * @param clazz 类型
     * @return 数据对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObjectFromXml(String xml, Class<T> clazz) {
        T result;

        XStream xStream = new XStream();
        xStream.alias("xml", clazz);
        xStream.processAnnotations(clazz);
        xStream.ignoreUnknownElements();// 暂时忽略掉一些新增的字段
        result = (T)xStream.fromXML(xml);
        return result;
    }

    public static void main(String[] args) {

        String xml = "<xml><appid>wx2421b1c4370ec43b</appid><attach>支付测试</attach><body>JSAPI支付测试</body><mch_id>10000100</mch_id><nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str><notify_url>http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php</notify_url><openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid><out_trade_no>1415659990</out_trade_no><spbill_create_ip>14.23.150.211</spbill_create_ip><total_fee>1</total_fee><trade_type>JSAPI</trade_type><sign>0CB01533B8C1EF103065174F50BCA001</sign></xml>";

        CreatePrepayOrderRequest req = new CreatePrepayOrderRequest();
        Object res = WeixinPayResponseHelper.getObjectFromXml(xml, req);

        System.out.println(req);
    }
}
