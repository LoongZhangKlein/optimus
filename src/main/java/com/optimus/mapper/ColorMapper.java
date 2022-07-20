package com.optimus.mapper;

import com.optimus.dto.params.ProductColorParamsDTO;
import com.optimus.dto.results.ProductColorResultDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ColorMapper {
    List<ProductColorResultDTO> query(ProductColorParamsDTO productColorParamsDTO);
}
