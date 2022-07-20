package com.optimus.mapper;

import com.optimus.dto.params.ProductSizeParamsDTO;
import com.optimus.dto.results.ProductSizeResultDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SizeMapper {
    /**
     * 查找所有颜色
     * @param productSizeParamsDTO
     * @return
     */
    List<ProductSizeResultDTO> query(ProductSizeParamsDTO productSizeParamsDTO);
}
