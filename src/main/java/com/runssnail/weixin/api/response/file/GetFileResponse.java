package com.runssnail.weixin.api.response.file;

import com.runssnail.weixin.api.response.ByteResponse;

/**
 * 下载多媒体文件
 * <p>
 * Created by zhengwei on 2017/3/2.
 */
public class GetFileResponse extends ByteResponse {

    private static final long serialVersionUID = -2747873088333216799L;

    private byte[] content;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
