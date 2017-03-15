package com.runssnail.weixin.api.manager.ticket;

import com.runssnail.weixin.api.domain.ticket.TicketDO;
import com.runssnail.weixin.api.manager.RedisClient;

/**
 * 用redis来存储js api ticket
 * <p>
 * Created by zhengwei on 2016/3/31.
 */
public class RedisTicketManager extends AbstractTicketManager {

    /**
     * 保存在redis里key的前缀, prefix + appid
     */
    private static final String TICKET_KEY_PREFIX = "weixin:";

    private RedisClient redisClient;

    /**
     * 存活时间，单位秒
     */
    private long liveTime = 7000;

    @Override
    protected void storeValue(TicketDO valueDO) {
        this.redisClient.set(getKey(), valueDO, this.liveTime);
    }

    protected String getKey() {
        return TICKET_KEY_PREFIX + getTicketType() + getAppId();
    }


    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    @Override
    public TicketDO getValue() {
        return (TicketDO) this.redisClient.get(getKey());
    }


}
