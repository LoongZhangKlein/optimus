package com.optimus.mapper;

import com.optimus.dto.params.CartParamsDTO;
import com.optimus.dto.results.CartResultDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    /**
     * 添加数据到购物车
     * @param cartParamsDTO
     * @return
     */
    Integer add(CartParamsDTO cartParamsDTO);

    /**
     * 查找数据
     * @param cartParamsDTO
     * @return
     */
    List<CartResultDTO> query(CartParamsDTO cartParamsDTO);

    /**
     * 更新数据
     * @param cartParamsDTO
     * @return
     */
    Integer update(CartParamsDTO cartParamsDTO);

}
