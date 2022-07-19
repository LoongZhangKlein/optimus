package com.optimus.service.Impl;


import com.optimus.dto.params.MenuParamsDTO;
import com.optimus.dto.results.MenuResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.exception.GlobalException;
import com.optimus.mapper.MenuMapper;
import com.optimus.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {
    @Resource
    MenuMapper menuMapper;

    /**
     * 首页导航栏分类
     *
     * @param menuParamsDTO
     * @return
     */
    @Override
    public List<MenuResultDTO> queryIndexMenu(MenuParamsDTO menuParamsDTO) {
        List<MenuResultDTO> query = menuMapper.query(menuParamsDTO);
        return query;
    }


    /**
     * 多级菜单查询(不可越级开启菜单)
     *
     * @param menuParamsDTO
     * @return
     */
    @Override
    public List<MenuResultDTO> queryMenu(MenuParamsDTO menuParamsDTO) {
        List<MenuResultDTO> allMenuList = menuMapper.query(menuParamsDTO);
        if (allMenuList == null) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        // 菜单归类
        Map<Integer, List<MenuResultDTO>> mapMenuList = this.buildMenusLevel(allMenuList);
        // 菜单拼装
        Map<Integer, List<MenuResultDTO>> integerListMap = this.buildMultiplyMenu(mapMenuList);
        List<MenuResultDTO> menuResultDTOS = integerListMap.get(1);
        return menuResultDTOS;
    }

    /**
     * 分类整理各级别菜单
     *
     * @param allMenuList
     * @return
     */
    private Map<Integer, List<MenuResultDTO>> buildMenusLevel(List<MenuResultDTO> allMenuList) {
        // 存放结果
        Map<Integer, List<MenuResultDTO>> menuListMap = new HashMap<>(16);
        Iterator<MenuResultDTO> iterator = allMenuList.iterator();
        while (iterator.hasNext()) {
            MenuResultDTO menuResultDTO = iterator.next();
            List<MenuResultDTO> list = menuListMap.get(menuResultDTO.getLevel());
            // 不存在该级菜单
            if (list == null) {
                list = new ArrayList<>();
                list.add(menuResultDTO);
                menuListMap.put(menuResultDTO.getLevel(), list);
                /**/
            } else {
                List<MenuResultDTO> menuResultDTOList = menuListMap.get(menuResultDTO.getLevel());
                // 由于list引用传值不需要put
                menuResultDTOList.add(menuResultDTO);
            }
        }
        return menuListMap;
    }

    /**
     * 多级菜单拼装
     *
     * @param mapMenuList
     * @return
     */
    private Map<Integer, List<MenuResultDTO>> buildMultiplyMenu(Map<Integer, List<MenuResultDTO>> mapMenuList) {
        // 多级菜单组装结果
        Map<Integer, List<MenuResultDTO>> menuListMap = new HashMap(16);
        for (int i = mapMenuList.size(); i >= 1; i--) {
            // 最后一级菜单上一级
            List<MenuResultDTO> beforeMenuList = mapMenuList.get(i - 1);
            if (beforeMenuList == null) {
                return mapMenuList;
            } else {
                Iterator<MenuResultDTO> beforeIterator = beforeMenuList.iterator();
                while (beforeIterator.hasNext()) {
                    // 最后一级菜单
                    MenuResultDTO beforeLevel = beforeIterator.next();
                    List<MenuResultDTO> sonMenuList = new ArrayList();
                    // 最后一级菜单集合
                    List<MenuResultDTO> lastMenuList = mapMenuList.get(i);
                    Iterator<MenuResultDTO> lastIterator = lastMenuList.iterator();
                    while (lastIterator.hasNext()) {
                        MenuResultDTO lastLevel = lastIterator.next();
                        if (beforeLevel.getId().equals(lastLevel.getParentId())) {
                            sonMenuList.add(lastLevel);
                        }
                    }
                    beforeLevel.setMenuResultDTOList(sonMenuList);
                    menuListMap.put(i, beforeMenuList);
                }
            }
        }

        return menuListMap;
    }

}
