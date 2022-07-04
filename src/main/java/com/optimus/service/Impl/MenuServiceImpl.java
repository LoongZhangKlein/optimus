package com.optimus.service.Impl;


import com.optimus.dto.params.MenuParamsDTO;
import com.optimus.dto.results.MenuResultDTO;
import com.optimus.dto.results.SecondMenuDTO;
import com.optimus.dto.results.ThirdResultDTO;
import com.optimus.mapper.MenuMapper;
import com.optimus.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
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
        // 总菜单
        List<MenuResultDTO> allMenuList = new ArrayList();
        // 一级菜单查询结果
        List<MenuResultDTO> menuResultDTOS = menuMapper.firstMenu(menuParamsDTO);
        // 遍历
        Iterator<MenuResultDTO> iteratorFirst = menuResultDTOS.iterator();

        while (iteratorFirst.hasNext()) {
            // 单个一级菜单
            MenuResultDTO firstMenu = iteratorFirst.next();
            // 封装查询入参
            menuParamsDTO.setId(firstMenu.getId());
            // 二级菜单查询结果
            List<MenuResultDTO> secondMenu = menuMapper.secondMenu(menuParamsDTO);
            Iterator<MenuResultDTO> iteratorSecond = secondMenu.iterator();
            /*SecondMenuDTO secondMenuDTO = new SecondMenuDTO();*/
            List<SecondMenuDTO> secondMenuDTOList = new ArrayList();
            while (iteratorSecond.hasNext()) {
                // 封装二级菜单
                SecondMenuDTO secondMenuDTO = new SecondMenuDTO();
                MenuResultDTO menuResSecond = iteratorSecond.next();
                secondMenuDTO.setName(menuResSecond.getName());
                /*secondMenuDTOList.add(secondMenuDTO);*/
                /*firstMenu.setSecondMenuDTO(secondMenuDTO);*/
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

                    /*menuParamsDTO.setId(menuResThird.getId());*/

                    /*List<MenuResultDTO> thirdMenuRes = menuMapper.secondMenu(menuParamsDTO);
                    Iterator<MenuResultDTO> iterator = thirdMenuRes.iterator();
                    while (iterator.hasNext()) {
                        MenuResultDTO next = iterator.next();
                        ThirdResultDTO thirdResultDTO = new ThirdResultDTO();
                        thirdResultDTO.setName(next.getName());
                        thirdResultDTOList.add(thirdResultDTO);

                    }*/

                }
                secondMenuDTO.setThirdResultDTOList(thirdResultDTOList);
                secondMenuDTOList.add(secondMenuDTO);

            }
            firstMenu.setSecondMenuDTOList(secondMenuDTOList);
            allMenuList.add(firstMenu);
        }



        /*List<MenuResultDTO> allMenu = new ArrayList();
        // 查询一级菜单
        List<MenuResultDTO> topMenu = menuMapper.firstMenu(menuParamsDTO);
        *//*List<List<List<MenuResultDTO>>> firstMenu = new ArrayList();*//*
        // 二级菜单
        *//*Iterator<MenuResultDTO> firstIterator = topMenu.iterator();*//*
        for (int i = 0; i < topMenu.size(); i++) {
            MenuResultDTO menuResultFirst=topMenu.get(i);
            allMenu.add(menuResultFirst);
            menuParamsDTO.setId(menuResultFirst.getId());
            List<MenuResultDTO> second = menuMapper.secondMenu(menuParamsDTO);
            // 三级菜单
            List<MenuResultDTO>  secondMenu = new ArrayList();

            for (int j = 0; j < second.size(); j++) {
                List<MenuResultDTO> third = menuMapper.secondMenu(menuParamsDTO);
                MenuResultDTO menuResultSecond=second.get(j);
                secondMenu.add( menuResultSecond);
                allMenu.add(secondMenu.get(i));
                menuParamsDTO.setId(menuResultSecond.getId());
                List<List<MenuResultDTO>> thirdMenu = new ArrayList();
                thirdMenu.add(third);
                secondMenu.add(thirdMenu.get(i).get(j));
                allMenu.add(secondMenu.get(i));
                *//*thirdMenu.addAll(third);*//*
            }
        }*/
        /*while (firstIterator.hasNext()) {
            MenuResultDTO menuResultFirst=firstIterator.next();
            menuParamsDTO.setId(menuResultFirst.getId());
            List<MenuResultDTO> second = menuMapper.secondMenu(menuParamsDTO);
            secondMenu.addAll(second);
            // 三级菜单
            Iterator<MenuResultDTO> secondIterator = second.iterator();
            while (secondIterator.hasNext()) {
                MenuResultDTO menuResultThird=secondIterator.next();
                menuParamsDTO.setId(menuResultThird.getId());
                List<MenuResultDTO> third = menuMapper.secondMenu(menuParamsDTO);
                thirdMenu.addAll(third);
            }
        }*/

        return allMenuList;
    }


}
