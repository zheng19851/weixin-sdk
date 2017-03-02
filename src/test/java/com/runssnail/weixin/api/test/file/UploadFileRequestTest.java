package com.runssnail.weixin.api.test.file;

import com.runssnail.weixin.api.DefaultWeixinClient;
import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.domain.FileItem;
import com.runssnail.weixin.api.request.file.UploadFileRequest;
import com.runssnail.weixin.api.response.file.UploadFileResponse;

import java.io.File;

/**
 * Created by zhengwei on 2017/3/2.
 */
public class UploadFileRequestTest {

    public static void main(String[] args) {
        String appId = "wxb8ddd7af44031487";
        String appSecret = "ba5cc30a802a1ea68bcdfca3c9235027";

        // panLaoBan test
//        String appId = "wx7cbc0121c2093f64";
//        String appSecret = "5380b2231935166e7d0f02cdce8e7209";

        WeixinClient weixinClient = new DefaultWeixinClient(appId, appSecret);

        String token = "GGFmj9s7jw-SR2rYzbcrowFkaiP7uCKd2rC3wQUG9_YZfD7PtsKNGEPecR0fGNFmKkX7wEk0G64RA0edXiKCzssZAHSlS2LmNXm2E4Oc-D88cw_cP00Ah5sT0xBhPdCfEHRjAFAPSC";

        try {

            UploadFileRequest fileRequest = new UploadFileRequest("image", new FileItem(new File("/Users/zhengwei/8.jpg")));
            UploadFileResponse res = weixinClient.execute(fileRequest, token);
            // 8qtfGmyz2In6oLxgtmLNox84G05G2HSjEo2x8zI-WCMMxT9LUV5ABq7VFLqMtLiA

            System.out.println(res);
        } finally {
            if (weixinClient != null) {
                weixinClient.close();
            }
        }
    }
}
