package com.runssnail.weixin.api.request.file;

import com.alibaba.fastjson.JSON;
import com.runssnail.weixin.api.constants.Constants;
import com.runssnail.weixin.api.internal.utils.ByteUtils;
import com.runssnail.weixin.api.request.DownloadRequest;
import com.runssnail.weixin.api.response.file.GetFileResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 下载多媒体文件
 * <p>
 * Created by zhengwei on 2017/3/2.
 */
public class GetFileRequest extends DownloadRequest<GetFileResponse> {

    private static final long serialVersionUID = 7376488132186822164L;

    /**
     * 媒体文件ID
     */
    private String mediaId;

    /**
     * @param mediaId 媒体文件ID
     */
    public GetFileRequest(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String getApiUrl() {
        return "http://file.api.weixin.qq.com/cgi-bin/media/get";
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>(1);
        params.put("media_id", this.mediaId);
        return params;
    }

    @Override
    public Class<GetFileResponse> getResponseClass() {
        return GetFileResponse.class;
    }

    @Override
    public GetFileResponse buildResponse(Object responseBody) {

        byte[] content = (byte[]) responseBody;

        try {
            GetFileResponse res = JSON.parseObject(ByteUtils.convert2String(content, Constants.DEFAULT_ENCODING), GetFileResponse.class);
            return res;
        } catch (Exception e) {

            // 这里json转换出错表示返回的是正常的内容
            GetFileResponse response = new GetFileResponse();
            response.setContent(content);
            return response;
        }


    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
