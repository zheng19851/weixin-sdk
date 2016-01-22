package com.runssnail.weixin.api.domain.menu;

/**
 * 发送位置
 * 
 * @author zhengwei
 *
 */
public class SendLocationMenu extends EventMenu {

    /**
     * 
     */
    private static final long serialVersionUID = 5501460315095661598L;

    public SendLocationMenu(String name, String key) {
        super("location_select", name, key);
    }
}
