package com.runssnail.weixin.api.manager;

import com.runssnail.weixin.api.domain.Value;
import com.runssnail.weixin.api.result.Result;

/**
 * Created by zhengwei on 2017/3/15.
 */
public interface ValueManager<T extends Value> {

    /**
     * 获取value
     *
     * @return
     */
    String getStringValue();

    /**
     * 刷新value
     *
     * @return 新的value
     */
    String refresh();

    /**
     * 刷新value，并且返回新的value
     *
     * @return 新的value
     */
    Result<T> refreshAndGet();

    /**
     * 获取value信息
     *
     * @return
     */
    T getValue();

}
