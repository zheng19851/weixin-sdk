package com.runssnail.weixin.api.internal.utils;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * xml数据转成map
 *
 * @author zhengwei
 * @date 2014-2-17
 */
public class XmlTool {

    /**
     * 转成Map
     *
     * @param xml
     * @return
     */
    public static Map<String, Object> toMap(String xml) {

        if (StringUtils.isBlank(xml)) {
            return java.util.Collections.emptyMap();
        }

        Map<String, Object> map = null;
        try {
            // 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = getStringStream(xml);
            Document document = builder.parse(is);

            // 获取到document里面的全部结点
            NodeList allNodes = document.getFirstChild().getChildNodes();
            Node node = null;
            map = new HashMap<String, Object>();
            int i = 0;
            while (i < allNodes.getLength()) {
                node = allNodes.item(i);
                if (node instanceof Element) {
                    map.put(node.getNodeName(), node.getTextContent());
                }
                i++;
            }
        } catch (Exception e) {
            throw new RuntimeException("xml to map error, xml=" + xml, e);
        }

        return map;
    }


    public static Map<String, String> toMapStringValue(String xml) {
        if (StringUtils.isBlank(xml)) {
            return java.util.Collections.emptyMap();
        }

        Map<String, String> map = null;
        try {
            // 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = getStringStream(xml);
            Document document = builder.parse(is);

            // 获取到document里面的全部结点
            NodeList allNodes = document.getFirstChild().getChildNodes();
            Node node = null;
            map = new HashMap<String, String>();
            int i = 0;
            while (i < allNodes.getLength()) {
                node = allNodes.item(i);
                if (node instanceof Element) {
                    map.put(node.getNodeName(), node.getTextContent());
                }
                i++;
            }
        } catch (Exception e) {
            throw new RuntimeException("xml to map error, xml=" + xml, e);
        }

        return map;
    }


    private static InputStream getStringStream(String xml) throws UnsupportedEncodingException {
        ByteArrayInputStream tInputStringStream = null;
        if (StringUtils.isNotBlank(xml)) {
            tInputStringStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        }
        return tInputStringStream;
    }

    public static String toXml(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder("<xml>");

        for (Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                sb.append("<").append(entry.getKey()).append(">");
                sb.append(entry.getValue());
                sb.append("</").append(entry.getKey()).append(">");
            }
        }

        sb.append("</xml>");
        return sb.toString();
    }

}
