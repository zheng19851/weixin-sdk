package com.runssnail.weixin.api.response.file;

import com.runssnail.weixin.api.response.ByteResponse;

/**
 * 下载多媒体文件
 * <p>
 * Created by zhengwei on 2017/3/2.
 */
public class GetFileResponse extends ByteResponse {

    private static final long serialVersionUID = -2747873088333216799L;

    /**
     * 文件名称
     */
    private String filename;

    /**
     * 文件类型
     */
    private String contentType;

    /**
     * 内容
     */
    private byte[] content;

    public GetFileResponse(String filename, String contentType, byte[] content) {
        this.filename = filename;
        this.contentType = contentType;
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
