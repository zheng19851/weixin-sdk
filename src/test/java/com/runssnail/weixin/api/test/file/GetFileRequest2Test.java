package com.runssnail.weixin.api.test.file;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhengwei on 2017/3/2.
 */
public class GetFileRequest2Test {

    public static void main(String[] args) throws IOException {


        String token = "GGFmj9s7jw-SR2rYzbcrowFkaiP7uCKd2rC3wQUG9_YZfD7PtsKNGEPecR0fGNFmKkX7wEk0G64RA0edXiKCzssZAHSlS2LmNXm2E4Oc-D88cw_cP00Ah5sT0xBhPdCfEHRjAFAPSC";
        String id = "8qtfGmyz2In6oLxgtmLNox84G05G2HSjEo2x8zI-WCMMxT9LUV5ABq7VFLqMtLiA";

        downloadMediaFromWx(token, id, "/Users/zhengwei/");
    }


    public static String downloadMediaFromWx(String accessToken, String mediaId, String fileSavePath) throws IOException {
        if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(mediaId)) return null;

        String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID".replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        InputStream in = conn.getInputStream();

        File dir = new File(fileSavePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!fileSavePath.endsWith("/")) {
            fileSavePath += "/";
        }

        String ContentDisposition = conn.getHeaderField("Content-disposition");
        System.out.println("ContentDisposition=" + ContentDisposition);
        String weixinServerFileName = ContentDisposition.substring(ContentDisposition.indexOf("filename") + 10, ContentDisposition.length() - 1);
        fileSavePath += weixinServerFileName;
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileSavePath));
        byte[] data = new byte[1024];
        int len = -1;

        while ((len = in.read(data)) != -1) {
            bos.write(data, 0, len);
        }

        bos.close();
        in.close();
        conn.disconnect();

        return fileSavePath;
    }

}
