package com.runssnail.weixin.api.response.message;

import com.alibaba.fastjson.annotation.JSONField;
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

    /**
     * 消息ID
     */
    @JSONField(name = "msgid")
    private String            msgId;

    /**
     * 消息ID
     *
     * @return
     */
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
