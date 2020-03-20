package com.service;

import com.entity.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> checkMenu(Boolean userAuthority);
}
