package com.optimus.service;

import com.optimus.dto.params.ShippingAddressParamsDTO;
import com.optimus.dto.results.ShippingAddressResultDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ShippingAddressService {
    /**
     * 查询地址
     * @return
     */
    List<ShippingAddressResultDTO> query(ShippingAddressParamsDTO shippingAddressParamsDTO);

    /**
     * 添加收货地址
     * @param shippingAddressParamsDTO
     * @return
     */
    Integer add(ShippingAddressParamsDTO shippingAddressParamsDTO);

    /**
     * 更新地址
     * @param shippingAddressParamsDTO
     * @return
     */
    Integer update(ShippingAddressParamsDTO shippingAddressParamsDTO);
}
