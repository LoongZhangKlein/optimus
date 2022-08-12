package com.optimus.service.Impl;

import com.optimus.commons.UserMessage;
import com.optimus.dto.params.*;
import com.optimus.dto.results.*;
import com.optimus.entity.OrderDetailDo;
import com.optimus.entity.OrderInfoDo;
import com.optimus.enums.GlobalEnum;
import com.optimus.enums.OrderEnum;
import com.optimus.exception.GlobalException;
import com.optimus.exception.UnderStockException;
import com.optimus.mapper.*;
import com.optimus.service.OrderService;
import com.optimus.trans.TransToDO;
import com.optimus.utils.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;


/**
 * @author DragonZhang
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static final int effective=0;
    @Resource
    OrderMapper orderMapper;
    @Resource
    CartMapper cartMapper;
    @Resource
    ProductStockMapper productStockMapper;
    @Resource
    ShippingAddressMapper shippingAddressMapper;
    @Resource
    ProductMapper productMapper;
    @Resource
    UserMessage userMessage;



    /**
     * 添加主订单
     * @param orderInfoParamsDTO
     * @param
     * @param 
     * @return
     */
    @Override
    public Integer addAllOrderInfo(OrderInfoParamsDTO orderInfoParamsDTO) {
        if (orderInfoParamsDTO==null||CollectionUtils.isEmpty(orderInfoParamsDTO.getIdList())) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        UserResultDTO userMsg = userMessage.getUserMsg(orderInfoParamsDTO.getToken());
        if (userMsg==null){
            throw new GlobalException(GlobalEnum.NOT_LOG_IN);
        }
        orderInfoParamsDTO.setUserId(userMsg.getUserId());
        //
        List<CartResultDTO> cartList = this.getCartList(orderInfoParamsDTO.getIdList());
        if (!this.productStock(cartList)){
            throw new UnderStockException(OrderEnum.UNDERSTOCK);
        }
        // 该订单地址信息
        ShippingAddressResultDTO shippingAddress = this.getShippingAddress(orderInfoParamsDTO.getAddressID());
        // 商品详细信息

        List<ProductResultDTO> productMsg = this.getProductMsg(cartList);
        //组装orderParams
        OrderInfoParamsDTO orderInfoParams = this.assembleOrder(shippingAddress,productMsg,orderInfoParamsDTO,cartList);
        // 生成订单
        Integer integer = orderMapper.addOrderInfo(orderInfoParams);
        Integer res=0;
        if (integer > 0) {
             res = this.addOrderDetail(productMsg, orderInfoParams, cartList);
        }
        // 生成订单详情
        // 清除对应购物车

        return res;
    }

    /**
     * 查询所有状态订单
     * @param orderInfoParamsDTO
     * @return
     */
    @Override
    public List<OrderInfoResultDTO> queryAllOrderInfo(OrderInfoParamsDTO orderInfoParamsDTO) {
        if (orderInfoParamsDTO==null){
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        /*if (StringUtils.isEmpty(orderInfoParamsDTO.getToken())){
            throw new GlobalException(GlobalEnum.NOT_LOG_IN);
        }*/
        UserResultDTO userMsg = userMessage.getUserMsg(orderInfoParamsDTO.getToken());
        OrderInfoDo orderInfoDo = new OrderInfoDo();
        orderInfoDo.setUserId(userMsg.getUserId());
        List<OrderInfoResultDTO> orderInfoList = orderMapper.queryAllOrderInfo(orderInfoDo);
        for (OrderInfoResultDTO orderInfo:orderInfoList) {
            OrderDetailParamsDTO orderDetailParamsDTO = new OrderDetailParamsDTO();
            orderDetailParamsDTO.setOrderNumber(orderInfo.getOrderNumber());
            List<OrderDetailResultsDTO> orderDetail = this.queryAllOrderDetail(orderDetailParamsDTO);
            orderInfo.setOrderDetailList(orderDetail);
        }

        return orderInfoList;
    }

    @Override
    public List<OrderDetailResultsDTO> queryAllOrderDetail(OrderDetailParamsDTO orderDetailParamsDTO) {
        if (orderDetailParamsDTO==null|| orderDetailParamsDTO.getOrderNumber()==null){
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        OrderDetailDo orderDetailDo = new OrderDetailDo();
        orderDetailDo.setOrderNumber(orderDetailParamsDTO.getOrderNumber());
        List<OrderDetailResultsDTO> orderDetailList = orderMapper.queryAllOrderDetail(orderDetailDo);
        return orderDetailList;
    }

    /**
     * 获取商品所有信息
     * @param cartList
     * @return
     */
    private List<ProductResultDTO> getProductMsg(List<CartResultDTO> cartList){
        if (CollectionUtils.isEmpty(cartList)){
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        List<ProductResultDTO> productList = new ArrayList();
        for (CartResultDTO cartDTO:cartList) {
            ProductParamsDTO product = new ProductParamsDTO();
            product.setId(cartDTO.getProductId());
            List<ProductResultDTO> productResultList = productMapper.query(product);
            productList.add(productResultList.get(0));
        }
        return productList;
    }
    /**
     * 获取收货地址信息
     * @param id
     * @return
     */
    private ShippingAddressResultDTO getShippingAddress(Integer id){
        if (id==null){
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        ShippingAddressParamsDTO shippingAddress = new ShippingAddressParamsDTO();
        shippingAddress.setId(id);
        List<ShippingAddressResultDTO> shippingAddressList = shippingAddressMapper.query(shippingAddress);
        return shippingAddressList.get(0);
    }
    /**
     * 获取购物车列表的所有商品
     *
     * @param cartIDList
     * @return
     */
    private List<CartResultDTO> getCartList(List<Long> cartIDList) {
        if (CollectionUtils.isEmpty(cartIDList)){
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        List<CartResultDTO> cartResultDTOList = new ArrayList();
        for (Long cartId : cartIDList) {
            CartParamsDTO cartParamsDTO = new CartParamsDTO();
            cartParamsDTO.setId(cartId);
            List<CartResultDTO> cartResultDTO = cartMapper.query(cartParamsDTO);
            cartResultDTOList.add(cartResultDTO.get(0));
        }
        return cartResultDTOList;
    }

    /**
     * 判断库存是否充足
     *
     * @param cartResultDTOList
     * @return
     */
    private boolean productStock(List<CartResultDTO> cartResultDTOList) {
        if (CollectionUtils.isEmpty(cartResultDTOList)){
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        int cartSize = cartResultDTOList.size();
        int flag = 0;
        for (CartResultDTO cartResultDto : cartResultDTOList) {
            ProductStockParamsDTO productStockDTO = new ProductStockParamsDTO();
            productStockDTO.setProductId(cartResultDto.getProductId());
            // 取出库存
            List<ProductStockResultDTO> productStockList = productStockMapper.query(productStockDTO);
            if (productStockList == null) {
                continue;
            }
            if (productStockList.get(0).getNumber() >= cartResultDto.getNumber()) {
                flag++;
            }
        }
        if (flag != cartSize) {
            return false;
        }

        return true;
    }

    /**
     * 组装订单信息
     * @param shippingAddress
     * @param productMsg
     * @return
     */
    private OrderInfoParamsDTO assembleOrder(ShippingAddressResultDTO shippingAddress,List<ProductResultDTO> productMsg,OrderInfoParamsDTO orderInfoParamsDTO,List<CartResultDTO> cartList) {
        OrderInfoParamsDTO order = new OrderInfoParamsDTO();
        order.setOrderNumber(UUIDUtils.getUUID());
        order.setUserId(orderInfoParamsDTO.getUserId());
        //总金额
        Double countPrice=0.00;
        for (ProductResultDTO productResult:productMsg) {
            countPrice+=productResult.getPrice();

        }

        // 支付金额
        Double payMoney=0.00;
        for (ProductResultDTO productResult:productMsg) {
            if (productResult.getIsDiscount()==null||productResult.getIsDiscount()!=effective){
                payMoney=countPrice;
                break;
            }
            payMoney+=productResult.getDiscountPrice();
        }
        order.setPayMoney(payMoney);
        order.setOrderMoney(countPrice);
        // 购买商品数量
        Integer productNumber=0;
        for (CartResultDTO cartResult:cartList) {
            productNumber+=cartResult.getNumber();

        }
        order.setProductNumber(productNumber);
        order.setConsigneeAddress(shippingAddress.getAddress());
        order.setConsigneeName(shippingAddress.getLinkman());
        order.setPayDetail(orderInfoParamsDTO.getPayDetail());
        // 生成订单号
        order.setStatus(OrderEnum.WAIT_SHIPMENT.getCode());
        order.setCreateTime(new Date());
        return order;
    }

    /**
     * 为每个商品创建订单
     * @param
     * @return
     */
    public Integer addOrderDetail(List<ProductResultDTO> productMsg,OrderInfoParamsDTO orderInfoParamsDTO,List<CartResultDTO> cartList) {
        if (productMsg == null || CollectionUtils.isEmpty(productMsg)|| CollectionUtils.isEmpty(cartList)) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }

        List<OrderDetailParamsDTO> orderDetailList = this.assembleOrderDetail(productMsg, orderInfoParamsDTO, cartList);
        Integer res=null;
        for (OrderDetailParamsDTO orderDetail:orderDetailList) {
            Integer addOrderDetailRes = orderMapper.addOrderDetail(orderDetail);
            res=addOrderDetailRes;
        }

        // 创建主订单
        return res;
    }
    private List<OrderDetailParamsDTO>  assembleOrderDetail(List<ProductResultDTO> productMsg,OrderInfoParamsDTO orderInfoParamsDTO,List<CartResultDTO> cartList){
        List<OrderDetailParamsDTO> orderDetailList = new ArrayList();
        for (int i = 0; i < productMsg.size(); i++) {
            OrderDetailParamsDTO orderDetail = new OrderDetailParamsDTO();
            orderDetail.setOrderNumber(orderInfoParamsDTO.getOrderNumber());
            orderDetail.setProductNumber(cartList.get(i).getNumber());
            if (productMsg.get(i).getDiscountPrice()!=null){
                orderDetail.setProductPrice(productMsg.get(i).getDiscountPrice());
            }else{
                orderDetail.setProductPrice(productMsg.get(i).getPrice());
            }
            orderDetail.setProductOrginPrice(productMsg.get(i).getPrice());
            orderDetail.setProductName(productMsg.get(i).getName());
            orderDetail.setProductId(productMsg.get(i).getId());
            orderDetail.setCreateTime(new Date());
            orderDetail.setStatus(effective);
            orderDetailList.add(orderDetail);
        }
        return orderDetailList;
    }

}
