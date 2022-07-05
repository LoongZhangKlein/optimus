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
    public List<MenuResultDTO> query(MenuParamsDTO menuParamsDTO) {
        List<MenuResultDTO> query = menuMapper.query(menuParamsDTO);
        return query;
    }

    @Override
    public List<MenuResultDTO> queryCategory(MenuParamsDTO menuParamsDTO) {
        return null;
    }

    /*@Override
    public List<MenuResultDTO> queryCategory(MenuParamsDTO menuParamsDTO) {
        // 总菜单
        List<MenuResultDTO> allMenuList = new ArrayList();
        // 一级菜单查询结果
        List<MenuResultDTO> menuResultDTOS = menuMapper.firstMenu(menuParamsDTO);
        // 遍历
        Iterator<MenuResultDTO> iteratorFirst = menuResultDTOS.iterator();

        while (iteratorFirst.hasNext()) {
            // 一级菜单
            MenuResultDTO firstMenu = iteratorFirst.next();
            // 封装查询入参
            menuParamsDTO.setId(firstMenu.getId());
            // 二级菜单查询结果
            List<MenuResultDTO> secondMenu = menuMapper.secondMenu(menuParamsDTO);
            Iterator<MenuResultDTO> iteratorSecond = secondMenu.iterator();
            List<SecondMenuDTO> secondMenuDTOList = new ArrayList();
            while (iteratorSecond.hasNext()) {
                // 封装二级菜单
                SecondMenuDTO secondMenuDTO = new SecondMenuDTO();
                MenuResultDTO menuResSecond = iteratorSecond.next();
                secondMenuDTO.setName(menuResSecond.getName());
                menuParamsDTO.setId(menuResSecond.getId());
                // 三级菜单查询结果
                List<MenuResultDTO> thirdMenu = menuMapper.secondMenu(menuParamsDTO);
                Iterator<MenuResultDTO> iteratorThird = thirdMenu.iterator();
                List<ThirdResultDTO> thirdResultDTOList = new ArrayList();
                while (iteratorThird.hasNext()) {
                    MenuResultDTO menuResThird = iteratorThird.next();
                    ThirdResultDTO thirdResultDTO = new ThirdResultDTO();
                    thirdResultDTO.setName(menuResThird.getName());
                    thirdResultDTOList.add(thirdResultDTO);
                    Iterator<MenuResultDTO> iterator = thirdMenuRes.iterator();
                    while (iterator.hasNext()) {
                        MenuResultDTO next = iterator.next();
                        ThirdResultDTO thirdResultDTO = new ThirdResultDTO();
                        thirdResultDTO.setName(next.getName());
                        thirdResultDTOList.add(thirdResultDTO);

                    }

                }
                secondMenuDTO.setThirdResultDTOList(thirdResultDTOList);
                secondMenuDTOList.add(secondMenuDTO);

            }
            firstMenu.setSecondMenuDTOList(secondMenuDTOList);
            allMenuList.add(firstMenu);
        }
        return allMenuList;
    }*/

    /**
     * 多级菜单查询(不可越级开启菜单)
     * @param menuParamsDTO
     * @return
     */
    @Override
    public List<MenuResultDTO> queryMenu(MenuParamsDTO menuParamsDTO) {
        long start = System.currentTimeMillis();
        List<MenuResultDTO> allMenuList = menuMapper.query(menuParamsDTO);
        if (allMenuList==null){
            throw new GlobalException(GlobalEnum.MSG_NOTFULL);
        }
        // 菜单归类
        Map<Integer, List<MenuResultDTO>> mapMenuList = this.buildMenusLevel(allMenuList);
        // 菜单拼装
        Map<Integer, List<MenuResultDTO>> integerListMap = this.buildMultiplyMenu(mapMenuList);
        List<MenuResultDTO> menuResultDTOS = integerListMap.get(1);
        System.out.println("去掉后");
        System.out.println(System.currentTimeMillis()-start);
        return menuResultDTOS;
    }

    /**
     * 分类整理各级别菜单
     * @param allMenuList
     * @return
     */
    private Map<Integer, List<MenuResultDTO>> buildMenusLevel(List<MenuResultDTO> allMenuList) {
        // 存放结果
        Map<Integer, List<MenuResultDTO>> menuListMap=new HashMap<>(16);
        Iterator<MenuResultDTO> iterator = allMenuList.iterator();
        while (iterator.hasNext()) {
            MenuResultDTO menuResultDTO = iterator.next();
            List<MenuResultDTO> list = menuListMap.get(menuResultDTO.getLevel());
            // 不存在该级菜单
            if (list==null) {
                list = new ArrayList<>();
                list.add(menuResultDTO);
                menuListMap.put(menuResultDTO.getLevel(), list);
                /**/
            }else{
                List<MenuResultDTO> menuResultDTOList = menuListMap.get(menuResultDTO.getLevel());
                menuResultDTOList.add(menuResultDTO);
            }

            /**/
        }
        return menuListMap;
    }

    /**
     * 多级菜单拼装
     * @param mapMenuList
     * @return
     */
    private Map<Integer, List<MenuResultDTO>> buildMultiplyMenu(Map<Integer, List<MenuResultDTO>> mapMenuList) {
        // 多级菜单组装结果
        Map<Integer, List<MenuResultDTO>> menuListMap = new HashMap();
        // 判断提前加
        /*if (mapMenuList.size() > 0) { }*/
            for (int i = mapMenuList.size(); i >= 1; i--) {
                // 最后一级菜单上一级
                List<MenuResultDTO> beforeMenuList = mapMenuList.get(i - 1);
                if (beforeMenuList == null) {
                    return mapMenuList;
                }else{
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
                        menuListMap.put(1, beforeMenuList);
                    }
                }
            }

        return menuListMap;
    }

}
