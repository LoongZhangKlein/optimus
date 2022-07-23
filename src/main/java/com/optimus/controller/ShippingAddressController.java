package com.optimus.controller;

import com.optimus.commons.R;
import com.optimus.dto.params.ShippingAddressParamsDTO;
import com.optimus.dto.results.ShippingAddressResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.exception.GlobalException;
import com.optimus.service.ShippingAddressService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/shippingAddress")
public class ShippingAddressController {
    @Resource
    ShippingAddressService shippingAddressService;
    @RequestMapping("/query")
    public R query(ShippingAddressParamsDTO shippingAddressParamsDTO){
        List<ShippingAddressResultDTO> shippingAddressResultDTOList = shippingAddressService.query(shippingAddressParamsDTO);
        if (CollectionUtils.isEmpty(shippingAddressResultDTOList)){
            return R.fail(GlobalEnum.MSG_BLANK.getCode(),GlobalEnum.MSG_BLANK.getMsg());
        }
        return R.creatR(shippingAddressParamsDTO,GlobalEnum.SUCCESS);
    }
}
