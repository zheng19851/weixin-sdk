package com.runssnail.weixin.api.test.file;

import com.runssnail.weixin.api.DefaultWeixinClient;
import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.request.file.GetFileRequest;
import com.runssnail.weixin.api.response.file.GetFileResponse;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * Created by zhengwei on 2017/3/2.
 */
public class GetFileRequestTest {

    public static void main(String[] args) throws Exception {

        String appId = "wxb8ddd7af44031487";
        String appSecret = "ba5cc30a802a1ea68bcdfca3c9235027";

        // panLaoBan test
//        String appId = "wx7cbc0121c2093f64";
//        String appSecret = "5380b2231935166e7d0f02cdce8e7209";

        WeixinClient weixinClient = new DefaultWeixinClient(appId, appSecret);

        String token = "h3NWwYStA3XHpXy5SBbnK55Xp7Ic_wrj_VE1YEgrsaSk4wu6WPK2Wz5AzIe4xWXFNpnnx1YNgGmHZdow-bSPReoVIsQ4dW29iRJen1j6-oOLmbgv2F2SNZctGJ_RURlgRURfADAEHE";

        try {

//            rVr0pUQuvTyzcV_aA0ohEjdOZnxhxNCSHRTXuSiiGWnddnrSzgcS3p_hLxzUrgT3
//            8qtfGmyz2In6oLxgtmLNox84G05G2HSjEo2x8zI-WCMMxT9LUV5ABq7VFLqMtLiA
            String id = "rVr0pUQuvTyzcV_aA0ohEjdOZnxhxNCSHRTXuSiiGWnddnrSzgcS3p_hLxzUrgT3";
            GetFileRequest getFileRequest = new GetFileRequest(id);
            GetFileResponse res = weixinClient.execute(getFileRequest, token);

            if (res.isSuccess()) {

                BufferedOutputStream out = null;
                try {

                    out = new BufferedOutputStream(new FileOutputStream("/Users/zhengwei/weixin23.jpg"));
                    out.write(res.getContent());

                } finally {
                    if (out != null) {
                        out.close();
                    }
                }

            }

            System.out.println(res);
        } finally {
            if (weixinClient != null) {
                weixinClient.close();
            }
        }
    }

}
