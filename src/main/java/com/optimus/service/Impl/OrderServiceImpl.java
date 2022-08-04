package com.optimus.service.Impl;

import com.optimus.dto.params.CartParamsDTO;
import com.optimus.dto.params.OrderDetailParamsDTO;
import com.optimus.dto.params.OrderInfoParamsDTO;
import com.optimus.dto.results.CartResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.enums.OrderEnum;
import com.optimus.exception.GlobalException;
import com.optimus.mapper.CartMapper;
import com.optimus.mapper.OrderMapper;
import com.optimus.service.OrderService;
import com.optimus.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderMapper orderMapper;
    @Resource
    CartMapper cartMapper;
    @Override
    public Integer addOrderInfo(OrderInfoParamsDTO orderInfoParamsDTO) {
        if (orderInfoParamsDTO == null|| CollectionUtils.isEmpty(orderInfoParamsDTO.getIdList())) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        // 判断库存是否充足
            //取到购物车所有商品id 和对应sku 和数量判断
        //组装orderParams
        OrderInfoParamsDTO orderInfoParams = this.assembleOrder(orderInfoParamsDTO);
        // 生成订单
        Integer integer = orderMapper.addOrderInfo(orderInfoParams);
        if (integer>0){
            
        }
        // 生成订单详情
        // 清除对应购物车

        return 0;
    }


    /**
     * 组装订单信息
     * @param orderInfoParamsDTO
     * @return
     */
    private OrderInfoParamsDTO assembleOrder(OrderInfoParamsDTO orderInfoParamsDTO){
        OrderInfoParamsDTO orderInfoParams = new OrderInfoParamsDTO();
        List<Long> idList = orderInfoParamsDTO.getIdList();
        Double orderPrice=0.00;
        Double payPrice=0.00;
        for (Long list:idList) {
            CartParamsDTO cartParamsDTO = new CartParamsDTO();
            cartParamsDTO.setId(list);
            // 根据购物车id获取对应的userid shang商品id 商品数量
            List<CartResultDTO> cartList = cartMapper.query(cartParamsDTO);
            if (!CollectionUtils.isEmpty(cartList)){
                orderPrice+=cartList.get(0).getNumber()*cartList.get(0).getPrice();
                payPrice=cartList.get(0).getDisCountPrice()*cartList.get(0).getPrice();
            }

        }
        //组装详细信息
        orderInfoParams.setOrderMoney(orderPrice);
        orderInfoParams.setOrderMoney(payPrice);
        orderInfoParams.setOrderNumber(UUIDUtils.getUUID());
        orderInfoParams.setOrderStatus(OrderEnum.WAIT_PAYMENT.getCode());
        return orderInfoParamsDTO;
    }
    @Override
    public Integer addOrderDetail(OrderDetailParamsDTO orderDetailParamsDTO) {
        return null;
    }


}
