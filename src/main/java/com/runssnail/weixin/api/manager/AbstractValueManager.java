package com.runssnail.weixin.api.manager;

import com.runssnail.weixin.api.domain.Value;
import com.runssnail.weixin.api.result.Result;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by zhengwei on 2017/3/15.
 */
public abstract class AbstractValueManager<T extends Value> implements ValueManager<T> {

    protected final Log log = LogFactory.getLog(getClass());

    @Override
    public String getStringValue() {
        T value = this.getValue();
        if (value != null) {
            return value.getStringValue();
        }
        return null;
    }

    @Override
    public String refresh() {
        Result<T> result = this.refreshAndGet();
        if (result.isSuccess()) {
            return result.getResult().getStringValue();
        }

        return null;
    }

    @Override
    public Result<T> refreshAndGet() {

        if (log.isInfoEnabled()) {
            log.info("refreshAndGet start");
        }

        T oldValue = this.getValue();

        Result<T> result = doRefresh();

        if (!result.isSuccess()) {
            return result;
        }

        storeValue(result.getResult());

        if (oldValue != null && StringUtils.isNotBlank(oldValue.getStringValue())) {
            result.getResult().setOldValue(oldValue.getStringValue());
        }

        return result;
    }

    protected abstract void storeValue(T value);

    protected abstract Result<T> doRefresh();
}
