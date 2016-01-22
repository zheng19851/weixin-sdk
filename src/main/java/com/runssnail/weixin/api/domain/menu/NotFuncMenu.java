package com.runssnail.weixin.api.domain.menu;

import java.util.List;

/**
 *非功能菜单
 *
 * @author zhengwei
 */
public class NotFuncMenu extends Menu {

    /**
     * 
     */
    private static final long serialVersionUID = -7315366613081322939L;
    
    
    private List<Menu> sub_button;

    public NotFuncMenu(String name, List<Menu> subMenus) {
        super(name);
        this.sub_button = subMenus;
    }

    public List<Menu> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Menu> sub_button) {
        this.sub_button = sub_button;
    }

}
