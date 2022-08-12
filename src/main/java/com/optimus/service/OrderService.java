package com.optimus.service;


import com.optimus.dto.params.CartParamsDTO;
import com.optimus.dto.params.OrderDetailParamsDTO;
import com.optimus.dto.params.OrderInfoParamsDTO;
import com.optimus.dto.results.OrderDetailResultsDTO;
import com.optimus.dto.results.OrderInfoResultDTO;

import java.util.List;


/**
 * @author DragonZhang
 */
public interface OrderService {
    /**
     * 添加订单
     * @param orderInfoParamsDTO
     * @return
     */
    Integer addAllOrderInfo(OrderInfoParamsDTO orderInfoParamsDTO);

    /**
     * 查询所有订单
     * @param orderInfoParamsDTO
     * @return
     */
    List<OrderInfoResultDTO> queryAllOrderInfo(OrderInfoParamsDTO orderInfoParamsDTO);

    /**
     * 查询所有子订单
     * @param orderDetailParamsDTO
     * @return
     */
    List<OrderDetailResultsDTO> queryAllOrderDetail(OrderDetailParamsDTO orderDetailParamsDTO);
}
