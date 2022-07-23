package com.optimus.service;

import com.optimus.dto.params.ShippingAddressParamsDTO;
import com.optimus.dto.results.ShippingAddressResultDTO;

import java.util.List;

public interface ShippingAddressService {
    /**
     * 查询地址
     * @return
     */
    List<ShippingAddressResultDTO> query(ShippingAddressParamsDTO shippingAddressParamsDTO);
}
