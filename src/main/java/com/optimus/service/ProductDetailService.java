package com.optimus.service;

import com.optimus.dto.params.ProductDetailParamsDTO;
import com.optimus.dto.results.ProductDetailResultDTO;

import java.util.List;

public interface ProductDetailService {
    /**
     * 查找数据
     * @param productDetailParamsDTO
     * @return
     */
    ProductDetailResultDTO query(ProductDetailParamsDTO productDetailParamsDTO);

}
