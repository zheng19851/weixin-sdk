package com.runssnail.weixin.api.request.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.runssnail.weixin.api.domain.message.KeyNoteValue;
import com.runssnail.weixin.api.request.PostRequest;
import com.runssnail.weixin.api.response.message.TemplateMessageResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板消息
 *
 * @author zhengwei
 */
public class TemplateMessageRequest extends PostRequest<TemplateMessageResponse> {

    /**
     *
     */
    private static final long serialVersionUID = 675917578862661462L;

    private static final String API_URL = "https://api.weixin.qq.com/cgi-bin/message/message/send";

    /**
     * 接收方
     */
    private String toUser;

    /**
     * 模板id
     */
    private String templateId;

    /**
     * 详情url
     */
    private String url;

    /**
     * 颜色
     */
    private String topColor;

    /**
     * 模板数据
     */
    private Map<String, KeyNoteValue> data;

    /**
     * 模板消息
     *
     * @param templateId 模板消息id
     * @param toUser     接收方的openid
     * @param data       数据
     */
    public TemplateMessageRequest(String templateId, String toUser, Map<String, KeyNoteValue> data) {
        this.templateId = templateId;
        this.toUser = toUser;
        this.data = data;
    }

    /**
     * 模板消息
     *
     * @param templateId 模板消息id
     * @param toUser     接收方的openid
     */
    public TemplateMessageRequest(String templateId, String toUser) {
        this.templateId = templateId;
        this.toUser = toUser;
    }

    @Override
    @JSONField(serialize = false)
    public String getApiUrl() {
        return API_URL;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("touser", this.toUser);
        params.put("template_id", this.templateId);
        params.put("url", this.url);
        params.put("topcolor", this.topColor);
        params.put("data", this.data);
        return params;
    }

    @Override
    @JSONField(serialize = false)
    public Class<TemplateMessageResponse> getResponseClass() {
        return TemplateMessageResponse.class;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopColor() {
        return this.topColor;
    }

    public void setTopColor(String topColor) {
        this.topColor = topColor;
    }

    public Map<String, KeyNoteValue> getData() {
        return data;
    }

    public void setData(Map<String, KeyNoteValue> data) {
        this.data = data;
    }

    public void addKeyNote(String key, String value) {
        if (data == null) {
            data = new HashMap<String, KeyNoteValue>();
        }

        data.put(key, new KeyNoteValue(value));
    }

    public void addKeyNote(String key, KeyNoteValue k) {
        if (data == null) {
            data = new HashMap<String, KeyNoteValue>();
        }

        data.put(key, k);
    }

    public String getToUser() {
        return toUser;
    }

    public String getTemplateId() {
        return templateId;
    }
}
