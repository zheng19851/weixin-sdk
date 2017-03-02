package com.runssnail.weixin.api.response.payment;

import com.runssnail.weixin.api.common.DataType;
import com.runssnail.weixin.api.common.utils.SignUtils;
import com.runssnail.weixin.api.exception.SignatureException;
import com.runssnail.weixin.api.exception.ApiException;
import com.runssnail.weixin.api.response.Response;

/**
 * 微信支付响应对象
 * <p/>
 * 返回数据是xml
 *
 * @author zhengwei
 */
public abstract class PaymentResponse extends Response {

    /**
     *
     */
    private static final long serialVersionUID = -2816113891214921028L;

    // 协议层

    /**
     * 返回状态码
     */
    private String return_code;

    /**
     * 返回信息
     */
    private String return_msg;

    // ==========业务数据==============
    /**
     * 业务结果
     */
    private String result_code;

    /**
     * 错误代码
     */
    private String err_code;

    /**
     * 错误代码描述
     */
    private String err_code_des;

    // ===============
    /**
     * 调用接口提交的公众账号ID
     */
    private String appid;

    /**
     * 调用接口提交的商户号
     */
    private String mch_id;

    /**
     * 调用接口提交的终端设备号
     */
    private String device_info;

    /**
     * 微信返回的随机字符串
     */
    private String nonce_str;

    /**
     * 微信返回的签名
     */
    private String sign;

    public String getMch_appid() {
        return this.appid;
    }

    public void setMch_appid(String appId) {
        this.appid = appId;
    }

    public String getMchid() {
        return mch_id;
    }

    public void setMchid(String mchId) {
        this.mch_id = mchId;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    @Override
    public boolean isSuccess() {
        return isReturnCodeSuccess() && isResultCodeSuccess();
    }

    private boolean isReturnCodeSuccess() {
        return "SUCCESS".equalsIgnoreCase(this.return_code);
    }

    @Override
    public String getErrcode() {
        return this.err_code;
    }

    @Override
    public void setErrcode(String errcode) {
        this.err_code = errcode;
    }

    @Override
    public void setErrmsg(String errmsg) {
        this.err_code_des = errmsg;
    }

    @Override
    public String getErrmsg() {
        return this.err_code_des;
    }

    private boolean isResultCodeSuccess() {
        return "SUCCESS".equalsIgnoreCase(this.result_code);
    }

    /**
     * 响应数据格式
     *
     * @return
     */
    public DataType getDataType() {
        return DataType.XML;
    }

    public void check(String paySignKey) throws ApiException {
        if (!isReturnCodeSuccess()) {
            return;
        }

        if (!SignUtils.validateSign(this.getResponseBody().toString(), paySignKey)) {
            throw new SignatureException("validate sign error, responseBoy=" + this.getResponseBody() + ", sign=" + this.sign);
        }

    }
}
