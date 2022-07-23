package com.optimus.service.Impl;

import com.optimus.dto.params.ShippingAddressParamsDTO;
import com.optimus.dto.results.ShippingAddressResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.exception.GlobalException;
import com.optimus.mapper.ShippingAddressMapper;
import com.optimus.service.ShippingAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
    @Resource
    ShippingAddressMapper shippingAddressMapper;

    @Override
    public List<ShippingAddressResultDTO> query(ShippingAddressParamsDTO shippingAddressParamsDTO) {
        if (shippingAddressParamsDTO == null) {
            throw new GlobalException(GlobalEnum.AUTH_DENIED);
        }
        List<ShippingAddressResultDTO> shippingAddressResList = shippingAddressMapper.query(shippingAddressParamsDTO);

        return shippingAddressResList;
    }
}
