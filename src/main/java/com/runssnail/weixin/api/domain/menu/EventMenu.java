package com.runssnail.weixin.api.domain.menu;

public class EventMenu extends FuncMenu {

    /**
     * 
     */
    private static final long serialVersionUID = 6076470553186123056L;

    private String            key;

    public EventMenu(String type, String name, String key) {
        super(type, name);
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
