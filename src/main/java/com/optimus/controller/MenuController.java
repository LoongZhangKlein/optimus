package com.optimus.controller;

import com.optimus.commons.R;
import com.optimus.dto.params.MenuParamsDTO;
import com.optimus.dto.results.MenuResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.service.MenuService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    MenuService menuService;
    @RequestMapping("/query")
    public R query(@RequestBody(required = false) MenuParamsDTO menuParamsDTO) {
        List<MenuResultDTO> query = menuService.query(menuParamsDTO);
        return R.creatR(query, GlobalEnum.SUCCESS);
    }
    @RequestMapping("/queryMenu")
    public R queryCategory(@RequestBody(required = false) MenuParamsDTO menuParamsDTO) {
        List<MenuResultDTO> query = menuService.queryMenu(menuParamsDTO);
        return R.creatR(query, GlobalEnum.SUCCESS);
    }

}
