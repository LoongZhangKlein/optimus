package com.optimus.mapper;

import com.optimus.dto.params.ProductStockParamsDTO;
import com.optimus.dto.results.ProductStockResultDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductStockMapper {
    /**
     * 查询库存
     * @param productStockParamsDTO
     * @return
     */
    List<ProductStockResultDTO> query(ProductStockParamsDTO productStockParamsDTO);

}
