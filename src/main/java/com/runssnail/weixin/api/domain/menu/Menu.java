package com.runssnail.weixin.api.domain.menu;

import java.util.List;

import com.runssnail.weixin.api.domain.BaseDomain;

/**
 * 菜单数据对象，全部类型的菜单数据全在这里
 * <ul>
 * <li>一级菜单数组，个数应为1~3个
 *
 * @author zhengwei
 */
public class Menu extends BaseDomain {

    /**
     *
     */
    private static final long serialVersionUID = -7539648101020457271L;

    /**
     * 类型，菜单的响应动作类型，必填
     */
    private String            type;

    /**
     * 菜单标题，不超过16个字节，子菜单不超过40个字节，必填
     */
    private String            name;

    /**
     * 菜单KEY值，用于消息接口推送，不超过128字节(click等点击类型必须)
     */
    private String            key;

    /**
     * 调用新增永久素材接口返回的合法media_id(media_id类型和view_limited类型必须)
     */
    private String            media_id;

    /**
     * 网页链接，用户点击菜单可打开链接，不超过256字节(view类型必须)
     */
    private String            url;

    /**
     * 二级菜单数组，个数应为1~5个
     */
    private List<Menu>        sub_button;

    public Menu(String name) {
        this.name = name;
    }

    public Menu() {
    }

    /**
     * 创建菜单
     *
     * @param type 类型
     * @param name 标题
     */
    public Menu(String type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * 创建菜单
     *
     * @param name 标题
     * @param subMenus 子菜单
     */
    public Menu(String name, List<Menu> subMenus) {
        this.name = name;
        this.sub_button = subMenus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public List<Menu> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Menu> sub_button) {
        this.sub_button = sub_button;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
