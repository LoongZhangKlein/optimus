package com.optimus.controller;

import com.optimus.commons.R;
import com.optimus.dto.params.OrderInfoParamsDTO;
import com.optimus.dto.results.OrderInfoResultDTO;
import com.optimus.service.OrderService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DragonZhang
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    OrderService orderService;

    /**
     * 查询所有状态订单
     * @param orderInfoParamsDTO
     * @return
     */
    @RequestMapping("/createOrder")
    public R createOrder(@RequestBody OrderInfoParamsDTO orderInfoParamsDTO){
        Integer integer = orderService.addAllOrderInfo(orderInfoParamsDTO);
        if (integer<=0||integer==null){
            return R.fail();
        }
        return R.ok();
    }
    @RequestMapping(value = "/queryOrder",method = RequestMethod.GET)
    public R queryOrder(OrderInfoParamsDTO orderInfoParamsDTO){
        List<OrderInfoResultDTO> orderInfoList = orderService.queryAllOrderInfo(orderInfoParamsDTO);
        if (CollectionUtils.isEmpty(orderInfoList)){
            return R.fail();
        }
        return R.ok();
    }
}
