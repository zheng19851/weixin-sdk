package com.runssnail.weixin.api.internal.support;

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

}
