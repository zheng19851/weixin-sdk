package com.runssnail.weixin.api.internal.support;

import com.alibaba.fastjson.JSONObject;
import com.runssnail.weixin.api.constants.WeixinApiErrorCode;
import com.runssnail.weixin.api.response.Response;

/**
 * 微信api帮助类
 *
 * @author zhengwei
 * @date 2014-2-18
 */
public class WeixinApiHelper {

	/**
	 * 判断是否AccessToken错误
	 *
	 * @param jsonObj json数据
	 * @return
	 */
	public static boolean isAccessTokenInvalid(JSONObject jsonObj) {

		// 没有错误那当然非AccessToken错误
		if (jsonObj == null || isSuccess(jsonObj)) {
			return false;
		}

		String errcode = jsonObj.getString("errcode");

		return WeixinApiErrorCode.isAccessTokenError(errcode);

	}

	/**
	 * 是否成功
	 *
	 * @param jsonObj json数据
	 * @return
	 */
	public static boolean isSuccess(JSONObject jsonObj) {

		if (!jsonObj.containsKey("errcode")) {
			return true;
		}

		String errCode = jsonObj.getString("errcode");

		return "0".equals(errCode);
	}

	/**
	 * {"errcode":40018,"errmsg":"invalid button name size"}
	 *
	 * @param jsonObj
	 * @return
	 */
	public static String getErrMsg(JSONObject jsonObj) {
		return jsonObj.getString("errmsg");
	}

	/**
	 * {"errcode":40018,"errmsg":"invalid button name size"}
	 *
	 * @param jsonObj
	 * @return
	 */
	public static String getErrCode(JSONObject jsonObj) {
		return jsonObj.getString("errcode");
	}

	/**
	 * 是否包含access_token
	 *
	 * @param jsonObj
	 * @return
	 */
	public static boolean containsAccessToken(JSONObject jsonObj) {
		return jsonObj.containsKey("access_token");
	}

	public static String getAccessToken(JSONObject jsonObj) {
		return jsonObj.getString("access_token");
	}

	public static boolean isAccessTokenInvalid(Response res) {
		// 没有错误那当然非AccessToken错误
		if (res == null || res.isSuccess()) {
			return false;
		}

		String errcode = res.getErrcode();

		return WeixinApiErrorCode.isAccessTokenError(errcode);
	}

}
