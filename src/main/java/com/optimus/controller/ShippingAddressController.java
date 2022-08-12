package com.optimus.controller;

import com.optimus.commons.R;
import com.optimus.dto.params.ShippingAddressParamsDTO;
import com.optimus.dto.results.ShippingAddressResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.service.ShippingAddressService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DragonZhang
 */
@RestController
@RequestMapping("/shippingAddress")
public class ShippingAddressController {

    @Resource
    ShippingAddressService shippingAddressService;
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public R query( ShippingAddressParamsDTO shippingAddressParamsDTO){
        List<ShippingAddressResultDTO> shippingAddressResultDTOList = shippingAddressService.query(shippingAddressParamsDTO);
        if (CollectionUtils.isEmpty(shippingAddressResultDTOList)){
            return R.fail(GlobalEnum.MSG_BLANK.getCode(),GlobalEnum.MSG_BLANK.getMsg());
        }
        return R.creatR(shippingAddressResultDTOList,GlobalEnum.SUCCESS);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public R add(@RequestBody ShippingAddressParamsDTO shippingAddressParamsDTO){
        Integer add = shippingAddressService.add(shippingAddressParamsDTO);
        if (null==add || add<=0){
            return R.fail();
        }
        return R.creatR(add,GlobalEnum.SUCCESS);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public R update(@RequestBody ShippingAddressParamsDTO shippingAddressParamsDTO){
        Integer update = shippingAddressService.update(shippingAddressParamsDTO);
        if (null==update || update<=0){
            return R.fail();
        }
        return R.creatR(update,GlobalEnum.SUCCESS);
    }
}
