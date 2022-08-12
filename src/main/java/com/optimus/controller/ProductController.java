package com.optimus.controller;


import com.optimus.commons.R;
import com.optimus.dto.params.PageParamsDTO;
import com.optimus.dto.params.ProductParamsDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author DragonZhang
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    ProductService productService;
    @RequestMapping(value = "/queryProduct")
    public R queryPage(PageParamsDTO pageParamsDTO, ProductParamsDTO productParamsDTO){
        PageParamsDTO pageParamsDTORes = productService.queryPage(pageParamsDTO, productParamsDTO);
        if (pageParamsDTORes !=null){
            return R.creatR(pageParamsDTORes, GlobalEnum.SUCCESS);
        }
        return R.fail(GlobalEnum.MSG_BLANK.getCode(),GlobalEnum.MSG_BLANK.getMsg());

    }

    @RequestMapping("/sort")
    public R query(PageParamsDTO pageParamsDTO, ProductParamsDTO productParamsDTO){
        PageParamsDTO pageParamsDTORes = productService.queryPage(pageParamsDTO, productParamsDTO);
        if (pageParamsDTORes !=null){
            return R.creatR(pageParamsDTORes, GlobalEnum.SUCCESS);
        }
        return R.fail(GlobalEnum.MSG_BLANK.getCode(),GlobalEnum.MSG_BLANK.getMsg());
    }

}
