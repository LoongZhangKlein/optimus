package com.optimus.service;


import com.optimus.dto.params.CartParamsDTO;
import com.optimus.dto.params.OrderDetailParamsDTO;
import com.optimus.dto.params.OrderInfoParamsDTO;


public interface OrderService {
    /**
     * 添加订单
     * @param orderInfoParamsDTO
     * @return
     */
    Integer addOrderInfo(OrderInfoParamsDTO orderInfoParamsDTO);

    /**
     * 添加订单详情
     * @param orderDetailParamsDTO
     * @return
     */
    Integer addOrderDetail(OrderDetailParamsDTO orderDetailParamsDTO);

}
