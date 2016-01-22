package com.runssnail.weixin.api.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 签名方式
 *
 * @author zhengwei
 */
public enum SignType {
    SHA1("SHA1"), MD5("MD5"), RSA("RSA");

    private String code;

    private static final Map<String, SignType> CACHE = new HashMap<String, SignType>(values().length);

    static {
        for (SignType entry : values()) {
            CACHE.put(entry.code, entry);
        }

    }

    SignType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean isMd5() {
        return MD5 == this;
    }

    public boolean isSHA1() {
        return SHA1 == this;
    }

    public boolean isRsa() {
        return RSA == this;
    }

    public SignType getByCode(String code) {
        return CACHE.get(code.toUpperCase());
    }

    public static boolean isSHA1(String code) {
        return SHA1.getCode().equalsIgnoreCase(code);
    }

}
