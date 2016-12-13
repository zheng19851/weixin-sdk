package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.common.Result;
import com.runssnail.weixin.api.domain.ticket.RefreshTicketDO;
import com.runssnail.weixin.api.request.web.GetJsApiTicketRequest;
import com.runssnail.weixin.api.response.web.GetJsApiTicketResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * js api ticket service
 * <p>
 * Created by zhengwei on 2016/3/31.
 */
public abstract class AbstractJsApiTicketService implements JsApiTicketService {

    protected final Log log = LogFactory.getLog(getClass());

    private WeixinClient weiXinClient;

    private AccessTokenService accessTokenService;

    @Override
    public String refresh() {
        if (log.isInfoEnabled()) {
            log.info("refresh ticket start");
        }

        GetJsApiTicketResponse response = weiXinClient.execute(new GetJsApiTicketRequest(), accessTokenService.getAccessToken());
        if (response.isSuccess()) {
            String old = saveTicket(response.getTicket());
            if (log.isInfoEnabled()) {
                log.info("refresh ticket success, new ticket->" + response.getTicket() + ", old->" + old);
            }

            return response.getTicket();
        } else {
            log.error("refresh ticket error, errorCode=" + response.getErrcode() + ", errorInfo=" + response.getErrmsg());
        }

        return null;
    }

    @Override
    public Result<RefreshTicketDO> refreshAndGet() {
        if (log.isInfoEnabled()) {
            log.info("refreshAndGet ticket start");
        }

        Result<RefreshTicketDO> result = new Result<>();

        GetJsApiTicketResponse response = weiXinClient.execute(new GetJsApiTicketRequest(), accessTokenService.getAccessToken());
        if (response.isSuccess()) {

            RefreshTicketDO refreshTicket = new RefreshTicketDO();

            String old = saveTicket(response.getTicket());
            if (log.isInfoEnabled()) {
                log.info("refreshAndGet ticket success, new ticket->" + response.getTicket() + ", old->" + old);
            }

            refreshTicket.setTicket(response.getTicket());
            refreshTicket.setOldTicket(old);
            return result.setSuccess(true).setResult(refreshTicket);
        } else {
            log.error("refreshAndGet ticket error, errorCode=" + response.getErrcode() + ", errorInfo=" + response.getErrmsg());
            return result.setError(response.getErrcode(), response.getErrmsg());
        }

    }

    /**
     * 保存，由自子类自己实现
     *
     * @param ticket
     */
    protected abstract String saveTicket(String ticket);

    public WeixinClient getWeiXinClient() {
        return weiXinClient;
    }

    public void setWeiXinClient(WeixinClient weiXinClient) {
        this.weiXinClient = weiXinClient;
    }

    public AccessTokenService getAccessTokenService() {
        return accessTokenService;
    }

    public void setAccessTokenService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }
}
