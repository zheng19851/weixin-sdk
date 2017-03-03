package com.runssnail.weixin.api.request;

import com.runssnail.weixin.api.domain.FileItem;
import com.runssnail.weixin.api.response.Response;

import java.util.Map;

/**
 * 上传文件请求
 * <p>
 * Created by zhengwei on 2017/3/2.
 */
public abstract class UploadRequest<R extends Response> extends PostRequest<R> {

    /**
     * 获取需要上传文件参数
     *
     * @return
     */
    public abstract Map<String/**fieldName*/, FileItem> getFileParams();
}
