package com.runssnail.weixin.api.domain.menu;

/**
 * 功能菜单
 * 
 * @author zhengwei
 */
public abstract class FuncMenu extends Menu {

    /**
     * 
     */
    private static final long serialVersionUID = 7641433968128880952L;

    private String            type;

    public FuncMenu(String type, String name) {
        super(name);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
