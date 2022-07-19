package com.optimus.service;

import com.optimus.dto.params.PageParamsDTO;
import com.optimus.dto.params.ProductParamsDTO;
import com.optimus.dto.results.ProductResultDTO;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有商品
     *
     * @param productParamsDTO
     * @return
     */
    List<ProductResultDTO> queryAll(ProductParamsDTO productParamsDTO);

    /**
     * 分页查询所有页面
     *
     * @param pageParamsDTO
     * @param productParamsDTO
     * @return
     */
    PageParamsDTO queryPage(PageParamsDTO pageParamsDTO, ProductParamsDTO productParamsDTO);

    /**
     * 根据指定字段排序
     *
     * @param productParamsDTO
     * @return
     */
    PageParamsDTO sort(PageParamsDTO pageParamsDTO, ProductParamsDTO productParamsDTO);

}
