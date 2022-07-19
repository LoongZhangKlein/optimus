package com.optimus.service;

import com.optimus.dto.params.CartParamsDTO;
import com.optimus.dto.params.PageParamsDTO;
import com.optimus.dto.params.ProductParamsDTO;
import com.optimus.dto.results.CartResultDTO;

import java.util.List;
import java.util.Map;

public interface CartService {
    /**
     * 添加数据
     *
     * @param stringMap
     * @return
     */
    Integer add(Map<String, String> stringMap);

    /**
     * 查找数据
     *
     * @param cartParamsDTO
     * @return
     */
    List<CartResultDTO> query(CartParamsDTO cartParamsDTO);

    /**
     * 分页查找数据
     * @param pageParamsDTO
     * @param cartParamsDTO
     * @return
     */
    PageParamsDTO queryPage(PageParamsDTO pageParamsDTO, CartParamsDTO cartParamsDTO);

    /**
     * 更新数据
     *
     * @param cartParamsDTO
     * @return
     */
    Integer update(CartParamsDTO cartParamsDTO);

}
