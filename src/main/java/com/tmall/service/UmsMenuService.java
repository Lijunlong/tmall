package com.tmall.service;

import java.util.List;
import java.util.Map;

import com.tmall.dto.MenuVo;
import com.tmall.dto.UmsMenuParam;
import com.tmall.model.UmsMenu;

/**
 * 菜单管理
 * Created by Mr.Li on 2019/08/08
 */
public interface UmsMenuService {
	
	/**
	 * 构建菜单树
	 * @param userId 用户id
	 * @return 菜单树
	 */
	List<UmsMenu> buildMenus(long userId);
	
	/**
	 * 构建前端路由所需要的菜单
	 * @param menuTree 菜单树
	 * @return 前端菜单
	 */
	List<MenuVo> buildMenus(List<UmsMenu> menuTree);

	List<UmsMenu> selectUmsMenuListByRoleId(Long roleId);
	
	/**
	 * 查询菜单
	 * @param name 菜单名称
	 * @return 菜单树
	 */
	List<UmsMenu> getMenuTree(String name);
	
	/**
	 * 返回全部的菜单，新增菜单时下拉选择
	 * @return 返回全部的菜单，新增菜单时下拉选择
	 */
	List<Map<String, Object>> buildTree();
	
	/**
	 * 修改菜单
	 * @param id
	 * @param umsMenuParam
	 * @return
	 */
	int update(Long id, UmsMenuParam umsMenuParam);
	
	/**
	 * 添加菜单
	 * @param umsMenuParam
	 * @return
	 */
	Long insert(UmsMenuParam umsMenuParam);

	int delete(Long menuId);

}
