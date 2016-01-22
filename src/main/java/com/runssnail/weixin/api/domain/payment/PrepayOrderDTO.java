package com.runssnail.weixin.api.domain.payment;

import com.runssnail.weixin.api.domain.BaseDomain;

/**
 * 创建预支付单后返回的数据
 *
 * @author zhengwei
 */
public class PrepayOrderDTO extends BaseDomain {

    /**
     *
     */
    private static final long serialVersionUID = 269558722149350544L;

    /**
     * 预支付单id
     */
    private String            prepayId;

    /**
     * trade_type为NATIVE是有返回，此参数可直接生成二维码展示出来进行扫码支付
     */
    private String            codeUrl;

    public PrepayOrderDTO(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

}
