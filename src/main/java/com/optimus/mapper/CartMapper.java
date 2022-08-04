package com.optimus.mapper;

import com.optimus.dto.params.CartParamsDTO;
import com.optimus.dto.results.CartResultDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    /**
     * insert into order_info (order_number,user_id,store_name,store_id,order_money,pay_money,order_desc,pay_type,order_status,consignee_address,consignee_name,third_pay_number,pay_detail,consignee_detail,`status`) VALUES(1,2,3,4,5,6,7,8,9,10,11,12,13,14,0)
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
     * 查找数据
     * @param cartParamsDTO
     * @return
     */
    List<CartResultDTO> queryPage(Integer size,Integer page,CartParamsDTO cartParamsDTO);

    /**
     *查找记录
     * @return
     */
    Integer count(CartParamsDTO cartParamsDTO);

    /**
     * 更新数据
     * @param cartParamsDTO
     * @return
     */
    Integer update(CartParamsDTO cartParamsDTO);

}
