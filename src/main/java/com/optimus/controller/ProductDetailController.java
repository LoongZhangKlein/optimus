package com.optimus.controller;

import com.optimus.commons.R;
import com.optimus.dto.params.ProductDetailParamsDTO;
import com.optimus.dto.results.ProductDetailResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.service.ProductDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author DragonZhang
 */
@RestController
@RequestMapping("/productDetail")
public class ProductDetailController {
    @Resource
    ProductDetailService productDetailService;
    @RequestMapping(value = "/productDetail",method = RequestMethod.GET)
    public R queryDetail(ProductDetailParamsDTO productDetailParamsDTO){
        ProductDetailResultDTO query = productDetailService.query(productDetailParamsDTO);
        if (query==null){
            return R.fail(GlobalEnum.MSG_NOT_FULL);
        }
        return R.creatR(query, GlobalEnum.SUCCESS);
    }
}
