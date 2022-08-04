package com.optimus.mapper;

import com.optimus.dto.params.CartParamsDTO;
import com.optimus.dto.params.OrderDetailParamsDTO;
import com.optimus.dto.params.OrderInfoParamsDTO;
import com.optimus.dto.results.CartResultDTO;
import com.optimus.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 添加订单
     * @param
     * @return
     */
    Integer addOrderInfo(OrderInfoParamsDTO orderInfoParamsDTO);
    /**
     * 添加订单详情
     */
    Integer addOrderDetail(OrderDetailParamsDTO orderDetailParamsDTO);
}
