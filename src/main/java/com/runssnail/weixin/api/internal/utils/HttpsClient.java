package com.runssnail.weixin.api.internal.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

public class HttpsClient {

    private static Log log = LogFactory.getLog(HttpsClient.class);

    //连接超时时间，默认10秒
    private int socketTimeout = 10000;

    //传输超时时间，默认30秒
    private int connectTimeout = 30000;

    //请求器的配置
    private RequestConfig requestConfig;

    //HTTP请求器
    private CloseableHttpClient httpClient;

    /**
     * 证书路径
     */
    private String certPath;

    /**
     * 证书密码
     */
    private String certPassword;

    public HttpsClient(String certPath, String certPassword) {
        assert certPath != null;
        assert certPassword != null;

        this.certPath = certPath;
        this.certPassword = certPassword;
        init();
    }

    private void init() {

        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream instream = new FileInputStream(new File(this.certPath));//加载本地的证书进行https加密传输
            try {
                keyStore.load(instream, this.certPassword.toCharArray());//设置证书密码
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                instream.close();
            }

            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, this.certPassword.toCharArray())
                    .build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

            httpClient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();

            //根据默认超时限制初始化requestConfig
            requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过Https往API post xml数据
     *
     * @param url    API地址
     * @param data 要提交的数据对象
     * @return API回包的实际数据
     */
    public String doPost(String url, Object data) {

        String result = null;

        HttpPost httpPost = new HttpPost(url);

        //解决XStream对出现双下划线的bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));

        //将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = xStreamForRequestPostData.toXML(data);

        if (log.isDebugEnabled()) {
            log.debug("API，POST过去的数据是：" + postDataXML);
        }

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        //设置请求器的配置
        httpPost.setConfig(requestConfig);

        if (log.isDebugEnabled()) {
            log.debug("executing request" + httpPost.getRequestLine());
        }

        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");

        } catch (Exception e) {
            throw new RuntimeException("http do post error", e);
        } finally {
            httpPost.abort();
        }

        return result;
    }

    /**
     * 设置连接超时时间
     *
     * @param socketTimeout 连接时长，默认10秒
     */
    public void setSocketTimeout(int socketTimeout) {
        socketTimeout = socketTimeout;
        resetRequestConfig();
    }

    /**
     * 设置传输超时时间
     *
     * @param connectTimeout 传输时长，默认30秒
     */
    public void setConnectTimeout(int connectTimeout) {
        connectTimeout = connectTimeout;
        resetRequestConfig();
    }

    private void resetRequestConfig() {
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    }

    /**
     * 允许商户自己做更高级更复杂的请求器配置
     *
     * @param requestConfig 设置HttpsRequest的请求器配置
     */
    public void setRequestConfig(RequestConfig requestConfig) {
        requestConfig = requestConfig;
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    public String getCertPassword() {
        return certPassword;
    }

    public void setCertPassword(String certPassword) {
        this.certPassword = certPassword;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }
}
