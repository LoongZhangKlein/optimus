package com.optimus.service;

import com.optimus.dto.params.MenuParamsDTO;
import com.optimus.dto.results.MenuResultDTO;

import java.util.List;

public interface MenuService {
    /**
     * 首页菜单栏查询
     * @param menuParamsDTO
     * @return
     */
    List<MenuResultDTO> queryIndexMenu(MenuParamsDTO menuParamsDTO);

    /**
     * 分类页面菜单查询
     * @param menuParamsDTO
     * @return
     */
    List<MenuResultDTO> queryMenu(MenuParamsDTO menuParamsDTO);
}
