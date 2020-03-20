package com.service.impl;

import com.entity.Menu;
import com.entity.UserMenu;
import com.mapper.MenuMapper;
import com.mapper.UserMenuMapper;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired(required = false)
    private MenuMapper menuMapper;

    @Autowired(required = false)
    private UserMenuMapper userMenuMapper;

    /**
     * 处理登录主页根据用户身份查询所拥有的一级菜单跟二级菜单 访问数据库 并返回查询请求
     *
     * @param userAuthority
     * @return
     */
    @Override
    public List<Menu> checkMenu(Boolean userAuthority) {
        List<Menu> menus = new ArrayList<>();
        List<UserMenu> userMenus = userMenuMapper.selectMenuIdByUserAuthority(userAuthority);
        for (UserMenu userMenu : userMenus) {
            Menu menu = menuMapper.selectByPrimaryKey(userMenu.getMenuId());
            List<Menu> menuList = menuMapper.selectMenuByParentId(userMenu.getMenuId());
            menu.setSecondaryMenu(menuList);
            menus.add(menu);
        }
        return menus;
    }
}
