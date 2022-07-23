package com.optimus.mapper;


import com.optimus.dto.params.ShippingAddressParamsDTO;
import com.optimus.dto.results.ShippingAddressResultDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShippingAddressMapper {
    List<ShippingAddressResultDTO> query(ShippingAddressParamsDTO shippingAddressParamsDTO);

}
