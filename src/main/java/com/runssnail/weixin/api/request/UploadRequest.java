package com.runssnail.weixin.api.request;

import com.runssnail.weixin.api.domain.FileItem;
import com.runssnail.weixin.api.response.Response;

import java.util.Map;

/**
 * Created by zhengwei on 2017/3/2.
 */
public abstract class UploadRequest<R extends Response> extends PostRequest<R> {

    public abstract Map<String, FileItem> getFileParams();
}
