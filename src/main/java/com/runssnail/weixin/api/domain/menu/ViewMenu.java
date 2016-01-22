package com.runssnail.weixin.api.domain.menu;

/**
 * 超链接菜单
 *
 * @author zhengwei
 */
public class ViewMenu extends FuncMenu {

    /**
     * 
     */
    private static final long serialVersionUID = -3362051428919213466L;

    /**
     * url
     */
    private String url;

    public ViewMenu(String name, String url) {
        super("view", name);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
