package com.optimus.mapper;


import com.optimus.dto.params.ProductParamsDTO;
import com.optimus.dto.results.ProductResultDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    /**
     * 查询所有商品
     * @param productParamsDTO
     * @return
     */
    List<ProductResultDTO> queryAll(ProductParamsDTO productParamsDTO);

    /**
     * 分页查询商品
     * @param size
     * @param pageSize
     * @param productParamsDTO
     * @return
     */
    List<ProductResultDTO> queryPage(@Param("size") Integer size, @Param("pageSize") Integer pageSize, @Param("productParamsDTO") ProductParamsDTO productParamsDTO);

    /**
     * 查询总条数
     * @param productParamsDTO
     * @return
     */
    Integer count(ProductParamsDTO productParamsDTO);

    /**
     * 根据指定字段排序
     * @param productParamsDTO
     * @return
     */
    List<ProductResultDTO> sort(@Param("size") Integer size, @Param("pageSize") Integer pageSize, @Param("productParamsDTO") ProductParamsDTO productParamsDTO);
}
