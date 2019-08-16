package com.tmall.dto;

import java.io.Serializable;

/**
 * 构建前端路由所需要的菜单
 * Created by Mr.Li on 2019/08/09
 */
public class MenuMetaVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String title;

    private String icon;
    
	public MenuMetaVo() {
		super();
	}

	public MenuMetaVo(String title, String icon) {
		super();
		this.title = title;
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
    
}
