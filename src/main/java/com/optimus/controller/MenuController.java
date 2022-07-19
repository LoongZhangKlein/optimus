package com.optimus.controller;


import com.alibaba.fastjson.JSONObject;
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
    @RequestMapping("/queryIndexMenu")
    public R queryIndexMenu(@RequestBody(required = false) MenuParamsDTO menuParamsDTO) {
        List<MenuResultDTO> query = menuService.queryIndexMenu(menuParamsDTO);
        return R.creatR(query, GlobalEnum.SUCCESS);
    }
    @RequestMapping("/queryMenu")
    public R queryMenu(@RequestBody(required = false) MenuParamsDTO menuParamsDTO) {
        List<MenuResultDTO> query = menuService.queryMenu(menuParamsDTO);
        JSONObject json=new JSONObject();
        json.put("data",query);
        return R.creatR(json, GlobalEnum.SUCCESS);
    }

}
