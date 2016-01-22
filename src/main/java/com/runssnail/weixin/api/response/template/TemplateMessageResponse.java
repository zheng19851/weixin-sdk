package com.runssnail.weixin.api.response.template;

import com.runssnail.weixin.api.response.JSONResponse;

/**
 * 发送模板消息
 * 
 * @author zhengwei
 */
public class TemplateMessageResponse extends JSONResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -6700224160125268959L;

    private String            msgid;

    public String getMsgId() {
        return this.msgid;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

}
