package com.runssnail.weixin.api.internal.utils;

import com.runssnail.weixin.api.constants.Constants;
import com.runssnail.weixin.api.domain.FileItem;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created by zhengwei on 2015/7/23.
 */
public abstract class HttpUtils {

    private static final String DEFAULT_CHARSET = Constants.DEFAULT_ENCODING;
    private static final String METHOD_POST = "POST";
    private static final String METHOD_GET = "GET";

    /**
     * 连接超时时间，单位毫秒，默认3秒
     */
    private static final int DEFAULT_CONNECT_TIMEOUT = Constants.DEFAULT_CONNECT_TIMEOUT;

    /**
     * 读取超时时间，单位毫秒，默认10秒
     */
    private static final int DEFAULT_READ_TIMEOUT = Constants.DEFAULT_READ_TIMEOUT;


    /**
     * 执行HTTP POST请求。
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 响应字符串
     * @throws IOException
     */
    public static String doPost(String url, Map<String, Object> params, int connectTimeout, int readTimeout) throws IOException {
        return doPost(url, params, DEFAULT_CHARSET, connectTimeout, readTimeout);
    }

    /**
     * 执行HTTP POST请求。
     *
     * @param url            请求地址
     * @param params         请求参数
     * @param charset        字符集，如UTF-8, GBK, GB2312
     * @param connectTimeout 链接超时时间, 单位毫秒
     * @param readTimeout    读取超时时间，单位毫秒
     * @return 响应字符串
     * @throws IOException
     */
    public static String doPost(String url, Map<String, Object> params, String charset, int connectTimeout, int readTimeout)
            throws IOException {
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        String query = buildQuery(params, charset);
        byte[] content = {};
        if (query != null) {
            content = query.getBytes(charset);
        }
        return doPost(url, ctype, content, connectTimeout, readTimeout);
    }

    public static byte[] doPostAndGetBytes(String url, String params, int connectTimeout, int readTimeout) throws IOException {
        String contentType = "application/x-www-form-urlencoded;charset=" + DEFAULT_CHARSET;
        byte[] content = {};
        if (StringUtils.isNotBlank(params)) {
            content = params.getBytes(DEFAULT_CHARSET);
        }
        return doPostAndGetBytes(url, contentType, content, connectTimeout, readTimeout);
    }

    public static byte[] doPostAndGetBytes(String url, String contentType, byte[] content, int connectTimeout, int readTimeout) throws IOException {
        HttpURLConnection conn = null;
        OutputStream out = null;
        byte[] rsp = null;
        try {
            conn = getConnection(new URL(url), METHOD_POST, contentType);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            out = conn.getOutputStream();
            out.write(content);
            rsp = getResponseAsBytes(conn);

        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    /**
     * 执行HTTP POST请求。
     *
     * @param url            请求地址
     * @param params         请求参数
     * @param connectTimeout 链接超时时间, 单位毫秒
     * @param readTimeout    读取超时时间，单位毫秒
     * @return 响应字符串
     * @throws IOException
     */
    public static String doPost(String url, String params, int connectTimeout, int readTimeout)
            throws IOException {
        String contentType = "application/x-www-form-urlencoded;charset=" + DEFAULT_CHARSET;
        byte[] content = {};
        if (StringUtils.isNotBlank(params)) {
            content = params.getBytes(DEFAULT_CHARSET);
        }
        return doPost(url, contentType, content, connectTimeout, readTimeout);
    }

    /**
     * 执行HTTP POST请求。
     *
     * @param url         请求地址
     * @param contentType 请求类型
     * @param content     请求字节数组
     * @return 响应字符串
     * @throws IOException
     */
    public static String doPost(String url, String contentType, byte[] content, int connectTimeout, int readTimeout) throws IOException {
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            conn = getConnection(new URL(url), METHOD_POST, contentType);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            out = conn.getOutputStream();
            out.write(content);
            rsp = getResponseAsString(conn);

        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    public static byte[] doPostAndGetBytes(String url, Map<String, Object> params, Map<String, FileItem> fileParams, int connectTimeout, int readTimeout) throws IOException {
        if (fileParams == null || fileParams.isEmpty()) {
            return doPostAndGetBytes(url, params, DEFAULT_CHARSET, connectTimeout, readTimeout);
        } else {
            return doPostAndGetBytes(url, params, fileParams, DEFAULT_CHARSET, connectTimeout, readTimeout);
        }
    }

    public static byte[] doPostAndGetBytes(String url, Map<String, Object> params, Map<String, FileItem> fileParams, String charset, int connectTimeout, int readTimeout) throws IOException {
        String boundary = System.currentTimeMillis() + ""; // 随机分隔线
        HttpURLConnection conn = null;
        OutputStream out = null;
        byte[] rsp = null;
        try {
            String contentType = "multipart/form-data;charset=" + charset + ";boundary=" + boundary;
            conn = getConnection(new URL(url), METHOD_POST, contentType);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);

            out = conn.getOutputStream();

            byte[] entryBoundaryBytes = ("\r\n--" + boundary + "\r\n").getBytes(charset);

            // 组装文本请求参数
            Set<Entry<String, Object>> textEntrySet = params.entrySet();
            for (Entry<String, Object> textEntry : textEntrySet) {
                byte[] textBytes = getTextEntry(textEntry.getKey(), textEntry.getValue(), charset);
                out.write(entryBoundaryBytes);
                out.write(textBytes);
            }

            // 组装文件请求参数
            Set<Entry<String, FileItem>> fileEntrySet = fileParams.entrySet();
            for (Entry<String, FileItem> fileEntry : fileEntrySet) {
                FileItem fileItem = fileEntry.getValue();
                byte[] fileBytes = getFileEntry(fileEntry.getKey(), fileItem.getFileName(),
                        fileItem.getMimeType(), charset);
                out.write(entryBoundaryBytes);
                out.write(fileBytes);
                out.write(fileItem.getContent());
            }

            // 添加请求结束标志
            byte[] endBoundaryBytes = ("\r\n--" + boundary + "--\r\n").getBytes(charset);
            out.write(endBoundaryBytes);
            rsp = getResponseAsBytes(conn);

        } finally {
            if (out != null) {
                out.close();
            }

            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    public static byte[] doPostAndGetBytes(String url, Map<String, Object> params, String charset, int connectTimeout, int readTimeout) throws IOException {
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        String query = buildQuery(params, charset);
        byte[] content = {};
        if (query != null) {
            content = query.getBytes(charset);
        }
        return doPostAndGetBytes(url, ctype, content, connectTimeout, readTimeout);
    }

    /**
     * 执行带文件上传的HTTP POST请求。
     *
     * @param url            api url
     * @param params         参数
     * @param fileParams     文件
     * @param connectTimeout 连接超时时间，单位毫秒
     * @param readTimeout    读取超时时间，单位毫秒
     * @return 响应数据
     * @throws IOException
     */
    public static String doPost(String url, Map<String, Object> params,
                                Map<String, FileItem> fileParams, int connectTimeout, int readTimeout) throws IOException {
        if (fileParams == null || fileParams.isEmpty()) {
            return doPost(url, params, DEFAULT_CHARSET, connectTimeout, readTimeout);
        } else {
            return doPost(url, params, fileParams, DEFAULT_CHARSET, connectTimeout, readTimeout);
        }
    }

    /**
     * 执行带文件上传的HTTP POST请求。
     *
     * @param url            api url
     * @param params         参数
     * @param fileParams     文件
     * @param charset        字符集，utf-8
     * @param connectTimeout 连接超时时间，单位毫秒
     * @param readTimeout    读取超时时间，单位毫秒
     * @return 响应数据
     * @throws IOException
     */
    public static String doPost(String url, Map<String, Object> params,
                                Map<String, FileItem> fileParams, String charset, int connectTimeout, int readTimeout) throws IOException {

        byte[] bytes = doPostAndGetBytes(url, params, fileParams, charset, connectTimeout, readTimeout);
        return ByteUtils.convert2String(bytes, charset);
    }

    private static byte[] getTextEntry(String fieldName, Object fieldValue, String charset)
            throws IOException {
        StringBuilder entry = new StringBuilder();
        entry.append("Content-Disposition:form-data;name=\"");
        entry.append(fieldName);
        entry.append("\"\r\nContent-Type:text/plain\r\n\r\n");
        entry.append(fieldValue);
        return entry.toString().getBytes(charset);
    }

    private static byte[] getFileEntry(String fieldName, String fileName, String mimeType,
                                       String charset) throws IOException {
        StringBuilder entry = new StringBuilder();
        entry.append("Content-Disposition:form-data;name=\"");
        entry.append(fieldName);
        entry.append("\";filename=\"");
        entry.append(fileName);
        entry.append("\"\r\nContent-Type:");
        entry.append(mimeType);
        entry.append("\r\n\r\n");
        return entry.toString().getBytes(charset);
    }

    /**
     * 执行HTTP GET请求。
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 响应字符串
     * @throws IOException
     */
    public static String doGet(String url, Map<String, Object> params) throws IOException {
        return doGet(url, params, DEFAULT_CHARSET);
    }


    /**
     * 执行HTTP GET请求。
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 响应字符串
     * @throws IOException
     */
    public static byte[] doGetBytes(String url, Map<String, Object> params) throws IOException {
        return doGetBytes(url, params, DEFAULT_CHARSET);
    }

    public static byte[] doGetBytes(String url, Map<String, Object> params, String charset) throws IOException {

        return doGetBytes(url, buildQuery(params, charset), charset);
    }

    public static byte[] doGetBytes(String url, String query, String charset) throws IOException {

        HttpURLConnection conn = null;
        byte[] rsp = null;

        try {
            String contentType = "application/x-www-form-urlencoded;charset=" + charset;
            conn = getConnection(buildGetUrl(url, query), METHOD_GET, contentType);

            rsp = getResponseAsBytes(conn);

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    /**
     * 执行HTTP GET请求。
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param charset 字符集，如UTF-8, GBK, GB2312
     * @return 响应字符串
     * @throws IOException
     */
    public static String doGet(String url, Map<String, Object> params, String charset)
            throws IOException {
        return doGet(url, buildQuery(params, charset), charset);
    }

    /**
     * 执行HTTP GET请求。
     *
     * @param url   请求地址
     * @param query 请求参数
     * @return 响应字符串
     * @throws IOException
     */
    public static String doGet(String url, String query)
            throws IOException {
        return doGet(url, query, DEFAULT_CHARSET);
    }

    /**
     * 执行HTTP GET请求。
     *
     * @param url     请求地址
     * @param query   请求参数
     * @param charset 字符集，如UTF-8, GBK, GB2312
     * @return 响应字符串
     * @throws IOException
     */
    public static String doGet(String url, String query, String charset)
            throws IOException {
        HttpURLConnection conn = null;
        String rsp = null;

        try {
            String contentType = "application/x-www-form-urlencoded;charset=" + charset;
            conn = getConnection(buildGetUrl(url, query), METHOD_GET, contentType);

            conn.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT);
            conn.setReadTimeout(DEFAULT_READ_TIMEOUT);
            rsp = getResponseAsString(conn);

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    private static HttpURLConnection getConnection(URL url, String method, String contentType) throws IOException {
        HttpURLConnection conn;
        if ("https".equals(url.getProtocol())) {
            SSLContext ctx;
            try {
                ctx = SSLContext.getInstance("TLS");
                ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
            } catch (Exception e) {
                throw new IOException(e);
            }
            HttpsURLConnection connHttps = (HttpsURLConnection) url.openConnection();
            connHttps.setSSLSocketFactory(ctx.getSocketFactory());
            connHttps.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;// 默认都认证通过
                }
            });
            conn = connHttps;
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }

        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", Constants.DEFAULT_ACCEPT);
        conn.setRequestProperty("User-Agent", Constants.DEFAULT_USER_AGENT);
        conn.setRequestProperty("Content-Type", contentType);
        return conn;
    }

    private static URL buildGetUrl(String strUrl, String query) throws IOException {
        URL url = new URL(strUrl);
        if (StringUtils.isEmpty(query)) {
            return url;
        }

        if (StringUtils.isEmpty(url.getQuery())) {
            if (strUrl.endsWith("?")) {
                strUrl = strUrl + query;
            } else {
                strUrl = strUrl + "?" + query;
            }
        } else {
            if (strUrl.endsWith("&")) {
                strUrl = strUrl + query;
            } else {
                strUrl = strUrl + "&" + query;
            }
        }

        return new URL(strUrl);
    }

    public static String buildQuery(Map<String, Object> params, String charset) throws IOException {
        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuilder query = new StringBuilder();
        Set<Entry<String, Object>> entries = params.entrySet();
        boolean hasParam = false;

        for (Entry<String, Object> entry : entries) {
            String name = entry.getKey();
            Object value = entry.getValue();
            // 忽略参数名或参数值为空的参数
            if (StringUtils.isNotBlank(name) && value != null) {
                if (hasParam) {
                    query.append("&");
                } else {
                    hasParam = true;
                }

                query.append(name).append("=").append(URLEncoder.encode(value.toString(), charset));
            }
        }

        return query.toString();
    }

    protected static byte[] getResponseAsBytes(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsBytes(conn.getInputStream());
        } else {
            String msg = getStreamAsString(es, charset);
            if (StringUtils.isNotBlank(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    private static byte[] getStreamAsBytes(InputStream stream) throws IOException {

        ByteArrayOutputStream out = null;
        try {

            out = new ByteArrayOutputStream();
            byte[] buff = new byte[1024]; // buff用于存放循环读取的临时数据
            int rc = 0;
            while ((rc = stream.read(buff)) > 0) {
                out.write(buff, 0, rc);
            }
            byte[] ret = out.toByteArray(); // 为转换之后的结果

            return ret;
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }


    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (StringUtils.isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    private static String getResponseCharset(String contentType) {
        String charset = DEFAULT_CHARSET;

        if (!StringUtils.isEmpty(contentType)) {
            String[] params = contentType.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (!StringUtils.isEmpty(pair[1])) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }

        return charset;
    }

    /**
     * 使用默认的UTF-8字符集反编码请求参数值。
     *
     * @param value 参数值
     * @return 反编码后的参数值
     */
    public static String decode(String value) {
        return decode(value, DEFAULT_CHARSET);
    }

    /**
     * 使用默认的UTF-8字符集编码请求参数值。
     *
     * @param value 参数值
     * @return 编码后的参数值
     */
    public static String encode(String value) {
        return encode(value, DEFAULT_CHARSET);
    }

    /**
     * 使用指定的字符集反编码请求参数值。
     *
     * @param value   参数值
     * @param charset 字符集
     * @return 反编码后的参数值
     */
    public static String decode(String value, String charset) {
        String result = null;
        if (!StringUtils.isEmpty(value)) {
            try {
                result = URLDecoder.decode(value, charset);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    /**
     * 使用指定的字符集编码请求参数值。
     *
     * @param value   参数值
     * @param charset 字符集
     * @return 编码后的参数值
     */
    public static String encode(String value, String charset) {
        String result = null;
        if (!StringUtils.isEmpty(value)) {
            try {
                result = URLEncoder.encode(value, charset);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    private static Map<String, String> getParamsFromUrl(String url) {
        Map<String, String> map = null;
        if (url != null && url.indexOf('?') != -1) {
            map = splitUrlQuery(url.substring(url.indexOf('?') + 1));
        }
        if (map == null) {
            map = new HashMap<String, String>();
        }
        return map;
    }

    /**
     * 从URL中提取所有的参数。
     *
     * @param query URL地址
     * @return 参数映射
     */
    public static Map<String, String> splitUrlQuery(String query) {
        Map<String, String> result = new HashMap<String, String>();

        String[] pairs = query.split("&");
        if (ArrayUtils.isNotEmpty(pairs)) {
            for (String pair : pairs) {
                String[] param = pair.split("=", 2);
                if (param != null && param.length == 2) {
                    result.put(param[0], param[1]);
                }
            }
        }

        return result;
    }


    private static class DefaultTrustManager implements X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }

}
