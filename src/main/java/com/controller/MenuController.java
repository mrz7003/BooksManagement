package com.controller;

import com.entity.Menu;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 接收Menu表的请求并处理
 */
@RestController
@RequestMapping("/Menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 登录主页 根据用户身份查询所拥有的菜单请求 转发给业务层
     *
     * @param userAuthority
     * @return
     */
    @GetMapping("/CheckMenu")
    public List<Menu> checkMenu(@RequestParam Boolean userAuthority) {
        List<Menu> menuList = menuService.checkMenu(userAuthority);
        if (menuList != null && menuList.size() > 0) {
            return menuList;
        }
        return null;
    }

}
