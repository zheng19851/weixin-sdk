package com.runssnail.weixin.api.domain.menu;

import java.util.List;

import com.runssnail.weixin.api.domain.BaseDomain;

/**
 * 菜单
 * 
 * @author zhengwei
 *
 */
public class Menus extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 220643371266915551L;

    /**
     * 一级菜单
     */
    private List<Menu>        button;

    public Menus() {
    }
    
    public Menus(List<Menu> menus) {
        this.button = menus;
    }

    public List<Menu> getButton() {
        return button;
    }

    public void setButton(List<Menu> button) {
        this.button = button;
    }

}
