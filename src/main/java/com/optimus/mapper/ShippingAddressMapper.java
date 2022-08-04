package com.optimus.mapper;


import com.optimus.dto.params.ShippingAddressParamsDTO;
import com.optimus.dto.results.ShippingAddressResultDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShippingAddressMapper {
    /**
     * 查询所有地址
     * @param shippingAddressParamsDTO
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
     * 更新收货地址
     * @param shippingAddressParamsDTO
     * @return
     */
    Integer update(ShippingAddressParamsDTO shippingAddressParamsDTO);


}
