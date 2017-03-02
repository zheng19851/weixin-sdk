package com.runssnail.weixin.api.internal.utils;

import java.io.*;

/**
 * Created by zhengwei on 2017/3/2.
 */
public class ByteUtils {

    public static String convert2String(byte[] bytes, String charset) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes), charset));
            StringWriter writer = new StringWriter();

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }

            return writer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
