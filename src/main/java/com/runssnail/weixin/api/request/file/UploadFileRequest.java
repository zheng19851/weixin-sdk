package com.runssnail.weixin.api.request.file;

import com.runssnail.weixin.api.domain.FileItem;
import com.runssnail.weixin.api.request.UploadRequest;
import com.runssnail.weixin.api.response.file.UploadFileResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 上传文件
 * <p>
 * Created by zhengwei on 2017/3/2.
 */
public class UploadFileRequest extends UploadRequest<UploadFileResponse> {


    private static final long serialVersionUID = -945107732448688030L;

    private String type;

    private FileItem fileItem;

    /**
     * @param type     媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param fileItem 文件
     */
    public UploadFileRequest(String type, FileItem fileItem) {
        this.type = type;
        this.fileItem = fileItem;
    }

    @Override
    public String getApiUrl() {
        return "http://file.api.weixin.qq.com/cgi-bin/media/upload";
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>(1);
        params.put("type", this.type);
        return params;
    }

    @Override
    public Class<UploadFileResponse> getResponseClass() {
        return UploadFileResponse.class;
    }

    @Override
    public Map<String, FileItem> getFileParams() {
        Map<String, FileItem> files = new HashMap<>(1);
        files.put("media", fileItem);
        return files;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FileItem getFileItem() {
        return fileItem;
    }

    public void setFileItem(FileItem fileItem) {
        this.fileItem = fileItem;
    }


}
