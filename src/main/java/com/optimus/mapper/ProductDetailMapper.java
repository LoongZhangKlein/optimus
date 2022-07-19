package com.optimus.mapper;

import com.optimus.dto.params.ProductDetailParamsDTO;
import com.optimus.dto.results.ProductDetailResultDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDetailMapper {
    List<ProductDetailResultDTO> query(ProductDetailParamsDTO productDetailParamsDTO);

}
