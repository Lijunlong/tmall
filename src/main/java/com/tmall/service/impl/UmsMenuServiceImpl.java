package com.tmall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tmall.dao.UmsMenuMapper;
import com.tmall.dto.MenuMetaVo;
import com.tmall.dto.MenuVo;
import com.tmall.dto.UmsMenuParam;
import com.tmall.model.UmsMenu;
import com.tmall.model.UmsPermission;
import com.tmall.model.UmsRole;
import com.tmall.service.UmsMenuService;
import com.tmall.service.UmsRoleService;

@Service
public class UmsMenuServiceImpl implements UmsMenuService {

	@Autowired
	private UmsMenuMapper umsMenuMapper;

	@Autowired
	private UmsRoleService umsRoleService;

	@Override
	public List<UmsMenu> buildMenus(long userId) {
		// 获取菜单集合
		List<UmsMenu> menuList = this.getMenuList(userId);
		// 构建菜单树
		List<UmsMenu> menuTree = this.getMenusTree(menuList);
		return menuTree;
	}

	/**
	 * 构建菜单树
	 * 
	 * @param menuList
	 * @return 菜单树
	 */
	private List<UmsMenu> getMenusTree(List<UmsMenu> menuList) {
		List<UmsMenu> menuTree = new ArrayList<UmsMenu>();

		if (menuList != null && menuList.size() > 0) {
			for (UmsMenu umsMenu : menuList) {
				if ("0".equals(umsMenu.getPid().toString())) {
					menuTree.add(umsMenu);
				}
				for (UmsMenu it : menuList) {
					if (it.getPid().equals(umsMenu.getId())) {
						if (umsMenu.getChildren() == null) {
							umsMenu.setChildren(new ArrayList<UmsMenu>());
						}
						umsMenu.getChildren().add(it);
					}
				}
			}
		}
		return menuTree;
	}

	/**
	 * 通过用户ID获取菜单集合
	 * 
	 * @param userId 用户ID
	 * @return 菜单集合
	 */
	private List<UmsMenu> getMenuList(long userId) {
		HashSet<UmsMenu> menus = new HashSet<UmsMenu>();
		// 通过用户id获取角色列表
		List<UmsRole> roleList = umsRoleService.selectRoleListByAdminId(userId);
		if (roleList != null && roleList.size() > 0) {
			for (UmsRole role : roleList) {
				// 通过角色id获取菜单列表
				Long roleId = role.getId();// 角色id
				List<UmsMenu> menuList = umsMenuMapper.selectUmsMenuListByRoleId(roleId);
				menus.addAll(menuList);
			}
		}
		return new ArrayList<UmsMenu>(menus);
	}

	@Override
	public List<MenuVo> buildMenus(List<UmsMenu> menuTree) {
		List<MenuVo> menuVoList = new ArrayList<MenuVo>();
		if (menuTree != null && menuTree.size() > 0) {
			for (UmsMenu umsMenu : menuTree) {
				//子菜单
				List<UmsMenu> children = umsMenu.getChildren();
				MenuVo menuVo = new MenuVo();
				menuVo.setName(umsMenu.getName());
				menuVo.setPath(umsMenu.getPath());
				// 判断是否为外链
				if (umsMenu.getiFrame() == 1) {// 0正常，1是外链
					// 如果不是外链
					if (umsMenu.getPid().equals(0L)) {
						// 一级目录需要加斜杠，不然访问 会跳转404页面
						menuVo.setPath("/" + umsMenu.getPath());
						menuVo.setComponent(StringUtils.isEmpty(umsMenu.getComponent()) ? "Layout" : umsMenu.getComponent());
					} else if (!StringUtils.isEmpty(umsMenu.getComponent())) {
						//如果是外链
						menuVo.setComponent(umsMenu.getComponent());
					}
				}
				//放置是否显示路由，1 显示，0隐藏
				if (umsMenu.getHidden() == 0) {
					menuVo.setHidden(true);
				}
				//放置Meta
				menuVo.setMeta(new MenuMetaVo(umsMenu.getName(), umsMenu.getIcon()));
				if(children != null && children.size() > 0){
                    menuVo.setAlwaysShow(true);
                    menuVo.setRedirect("noredirect");
                    menuVo.setChildren(buildMenus(children));
                } else if(umsMenu.getPid().equals(0L)){
                	// 处理如果是一级菜单并且没有子菜单的情况
                	List<MenuVo> list1 = new ArrayList<MenuVo>();
                    MenuVo menuVo1 = new MenuVo();
                    menuVo1.setMeta(menuVo.getMeta());
                    if(umsMenu.getiFrame() == 1){
                    	// 非外链
                        menuVo1.setPath("index");
                        menuVo1.setName(menuVo.getName());
                        menuVo1.setComponent(menuVo.getComponent());
                    } else {
                        menuVo1.setPath(umsMenu.getPath());
                    }
                    menuVo.setName(null);
                    menuVo.setMeta(null);
                    menuVo.setComponent("Layout");
                    list1.add(menuVo1);
                    menuVo.setChildren(list1);
                }
				menuVoList.add(menuVo);
			}
		}
		return menuVoList;
	}

	@Override
	public List<UmsMenu> selectUmsMenuListByRoleId(Long roleId) {
		return umsMenuMapper.selectUmsMenuListByRoleId(roleId);
	}

	@Override
	public List<UmsMenu> getMenuTree(String name) {
		UmsMenu umsMenuSearchParam = new UmsMenu();
		umsMenuSearchParam.setName(name);
		//获取菜单列表
		List<UmsMenu> umsMenuList = umsMenuMapper.selectUmsMenuLikeUmsMenu(umsMenuSearchParam);
		//构建菜单树
		List<UmsMenu> umsMenuTree = this.getMenusTree(umsMenuList);
		if (umsMenuTree.size() == 0 && !StringUtils.isEmpty(name)) {
			return umsMenuList;
		}else {
			return umsMenuTree;
		}
	}

	@Override
	public List<Map<String, Object>> buildTree() {
		//获取根菜单
		List<UmsMenu> umsMenuList = umsMenuMapper.selectUmsMenuListByPid(0L);
		//构建前端新增菜单时下拉选择的菜单树
		return this.buildTree(umsMenuList);
	}
	
	/**
	 * 构建前端新增菜单时下拉选择的菜单树
	 * @param umsMenuList 菜单列表
	 * @return 菜单树
	 */
	private List<Map<String, Object>> buildTree(List<UmsMenu> umsMenuList) {
		List<Map<String, Object>> tree = new ArrayList<Map<String,Object>>();
		for (UmsMenu umsMenu : umsMenuList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", umsMenu.getId());
			map.put("label", umsMenu.getName());
			List<UmsMenu> children = umsMenuMapper.selectUmsMenuListByPid(umsMenu.getId());
			if (children != null && children.size() > 0) {
				map.put("children", buildTree(children));
			}
			tree.add(map);
		}
		return tree;
	}

	@Override
	public int update(Long id, UmsMenuParam umsMenuParam) {
		UmsMenu umsMenu = this.createUmsMenuByUmsMenuParam(umsMenuParam);
		umsMenu.setId(id);
		umsMenuMapper.updateUmsMenu(umsMenu);
		return 1;
	}
	
	@Override
	public Long insert(UmsMenuParam umsMenuParam) {
		UmsMenu umsMenu = this.createUmsMenuByUmsMenuParam(umsMenuParam);
		umsMenu.setCreateTime(new Date());
		umsMenuMapper.insertUmsMenu(umsMenu);
		return umsMenu.getId();
	}
	
	/**
	 * 转换器：把前端菜单的数据转成后端菜单表的数据
	 * @param umsMenuParam 前端菜单的数据
	 * @return 后端菜单的数据
	 */
	private UmsMenu createUmsMenuByUmsMenuParam(UmsMenuParam umsMenuParam) {
		UmsMenu umsMenu = new UmsMenu();
		BeanUtils.copyProperties(umsMenuParam, umsMenu);
		return umsMenu;
	}

	@Override
	public int delete(Long menuId) {
		List<UmsMenu> children = umsMenuMapper.selectUmsMenuListByPid(menuId);
		for (UmsMenu umsMenu : children) {
			delete(umsMenu.getId());
		}
		umsMenuMapper.deleteUmsMenuById(menuId);
		return 1;
	}

}
