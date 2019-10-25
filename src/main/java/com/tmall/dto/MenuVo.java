package com.tmall.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 构建前端路由所需要的菜单
 * Created by Mr.Li on 2019/08/09
 */
public class MenuVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String name;

    private String path;

    private String redirect;

    private String component;

    private Boolean alwaysShow;
    
    private Boolean hidden;

    private MenuMetaVo meta;

    private List<MenuVo> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public Boolean getAlwaysShow() {
		return alwaysShow;
	}

	public void setAlwaysShow(Boolean alwaysShow) {
		this.alwaysShow = alwaysShow;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public MenuMetaVo getMeta() {
		return meta;
	}

	public void setMeta(MenuMetaVo meta) {
		this.meta = meta;
	}

	public List<MenuVo> getChildren() {
		return children;
	}

	public void setChildren(List<MenuVo> children) {
		this.children = children;
	}

}
