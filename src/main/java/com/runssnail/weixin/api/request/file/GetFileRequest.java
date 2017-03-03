package com.runssnail.weixin.api.request.file;

import com.alibaba.fastjson.JSON;
import com.runssnail.weixin.api.constant.Constants;
import com.runssnail.weixin.api.domain.FileItem;
import com.runssnail.weixin.api.exception.ApiException;
import com.runssnail.weixin.api.internal.util.ByteUtils;
import com.runssnail.weixin.api.request.DownloadRequest;
import com.runssnail.weixin.api.response.file.GetFileResponse;

import java.io.IOException;
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

        FileItem fileItem = (FileItem) responseBody;
        try {
            if (fileItem.getMimeType().startsWith("text") || fileItem.getMimeType().startsWith("application")) {
                // 出错了
                GetFileResponse res = JSON.parseObject(ByteUtils.convert2String(fileItem.getContent(), Constants.DEFAULT_ENCODING), GetFileResponse.class);
                return res;
            }

            GetFileResponse response = new GetFileResponse(fileItem.getFileName(), fileItem.getMimeType(), fileItem.getContent());
            return response;
        } catch (IOException e) {
            throw new ApiException(e);
        }

    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
