package com.optimus.service.Impl;


import com.optimus.dto.params.*;
import com.optimus.dto.results.*;
import com.optimus.enums.GlobalEnum;
import com.optimus.exception.GlobalException;
import com.optimus.mapper.CartMapper;
import com.optimus.mapper.ColorMapper;
import com.optimus.mapper.ProductMapper;
import com.optimus.mapper.SizeMapper;
import com.optimus.service.CartService;
import com.optimus.service.ProductService;
import com.optimus.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Service
public class CartServiceImpl implements CartService {
    private final int requiredMsg = 4;
    private final int effectiveStatus = 0;
    private final int error = -1;
    @Resource
    CartMapper cartMapper;
    @Resource
    RedisUtil redisUtil;
    @Resource
    ProductMapper productMapper;
    @Resource
    ColorMapper colorMapper;
    @Resource
    SizeMapper sizeMapper;
    @Override
    public Integer add(Map<String, String> cartMap) {
        if (CollectionUtils.isEmpty(cartMap)) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        String token = cartMap.get("token");
        // 验证是否登录
        Map<Object, Object> userMsg = redisUtil.hmget(token);
        if (CollectionUtils.isEmpty(userMsg)){
            throw new GlobalException(GlobalEnum.NOT_LOG_IN);
        }
        // 获取用户信息
        UserResultDTO userMsgDetail = getUserMsg(userMsg, token);
        // 组装DTO
        CartParamsDTO cartParamsDTO = assemble(cartMap,userMsgDetail);
        //判断是否已经加入购物车
        List<CartResultDTO> query = query(cartParamsDTO);
        // 未加入直接加入
        if (CollectionUtils.isEmpty(query)) {
            Integer integer = addToCart(cartParamsDTO);
            return integer;
        } else {
            //已经加入
            Integer update = cartMapper.update(cartParamsDTO);
            return update;
        }
    }

    /**
     * 查找购物车是否需要重复添加
     * @param cartParamsDTO
     * @return
     */
    @Override
    public List<CartResultDTO> query(CartParamsDTO cartParamsDTO) {
        if (cartParamsDTO == null) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        List<CartResultDTO> query = cartMapper.query(cartParamsDTO);
        return query;
    }

    /**
     * 分页查询所有购物车数据
     * @param pageParamsDTO
     * @param cartParamsDTO
     * @return
     */
    @Override
    public PageParamsDTO queryPage(PageParamsDTO pageParamsDTO, CartParamsDTO cartParamsDTO) {
        Integer curPage= pageParamsDTO.getCurPage();
        Integer pageSize= pageParamsDTO.getPageSize();
        if (curPage<1|| curPage==null || pageSize<=0 || pageSize==null){
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        Integer totalCount=null;
        //查找总记录数
        if (totalCount==null){
            totalCount=cartMapper.count(cartParamsDTO);
        }
        int totalPages=(totalCount  +  pageSize  - 1) / pageSize;
        int offset=(curPage-1)*pageSize;
        // 组装购物车
        List<CartResultDTO> cartResultDTOS = assembleCart(offset, pageSize, cartParamsDTO);
        Map<String, Double> price = getPrice(cartResultDTOS);
        Double sumPrice = price.get("sumPrice");
        Double sumDisCount = price.get("sumDisCount");
        pageParamsDTO.setSumPrice(sumPrice);
        pageParamsDTO.setSumDisCount(sumDisCount);
        pageParamsDTO.setCurPage(pageParamsDTO.getCurPage());
        pageParamsDTO.setList(cartResultDTOS);
        pageParamsDTO.setTotalCount(totalCount);
        pageParamsDTO.setTotalPage(totalPages);
        return pageParamsDTO;
    }

    /**
     * 若重复添加相同物品 仅更新数量
     * @param cartParamsDTO
     * @return
     */
    @Override
    public Integer update(CartParamsDTO cartParamsDTO) {
        if (cartParamsDTO == null) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        cartParamsDTO.setUpdateTime(new Date());
        Integer update = cartMapper.update(cartParamsDTO);
        return update;
    }
    private Map<String,Double> getPrice(List<CartResultDTO> cartResultDTOS){
        Map<String,Double> priceMap=new HashMap<>();
        Iterator<CartResultDTO> cartResultDTOIterator = cartResultDTOS.iterator();
        Double sumPrice=0.00;
        Double disCountPrice=0.00;
        while (cartResultDTOIterator.hasNext()) {
            CartResultDTO cartResultDTO = cartResultDTOIterator.next();
            sumPrice+=cartResultDTO.getPrice()*cartResultDTO.getNumber();
             disCountPrice+=cartResultDTO.getDisCountPrice()*cartResultDTO.getNumber();

        }
        priceMap.put("sumPrice",sumPrice);
        priceMap.put("disCountPrice",disCountPrice);
        return  priceMap;
    }
    /**
     * 组装购物车信息
     * @param offset
     * @param pageSize
     * @param cartParamsDTO
     * @return
     */
    private List<CartResultDTO> assembleCart(Integer offset,Integer pageSize,CartParamsDTO cartParamsDTO){
        List<CartResultDTO> cartResultDTOS = cartMapper.queryPage(offset, pageSize, cartParamsDTO);
        Iterator<CartResultDTO> cartResultDTOIterator = cartResultDTOS.iterator();
        // 获得每个购物车中的商品
        while (cartResultDTOIterator.hasNext()) {
            CartResultDTO cartResultDTO = cartResultDTOIterator.next();
            // 查找每个商品的信息
            ProductResultDTO productResultDTO = assembleProductMsg(cartResultDTO);
            // 查找颜色
            ProductColorResultDTO colorResultDTO = assembleColorMsg(cartResultDTO);
            //查找 尺寸
            ProductSizeResultDTO sizeResultDTO = assembleSizeMsg(cartResultDTO);
            // 组装cart
            cartResultDTO.setImages(productResultDTO.getMainImages());
            cartResultDTO.setPrice(productResultDTO.getPrice());
            cartResultDTO.setName(productResultDTO.getName());
            cartResultDTO.setColorStr(colorResultDTO.getColor());
            cartResultDTO.setSizeStr(sizeResultDTO.getSize());
            if (productResultDTO.getDiscountPrice()!=null){
                cartResultDTO.setDisCountPrice(productResultDTO.getDiscountPrice());
            }else{
                cartResultDTO.setDisCountPrice(0.00);
            }

        }


        return cartResultDTOS;
    }

    /**
     * 组装商品信息
     * @param cartResultDTO
     * @return
     */
    private ProductResultDTO assembleProductMsg(CartResultDTO cartResultDTO){
        // 组装
        ProductParamsDTO productParamsDTO = new ProductParamsDTO();
        productParamsDTO.setId(cartResultDTO.getProductId());
        // 查找每个商品的信息
        List<ProductResultDTO> productResultDTOList = productMapper.query(productParamsDTO);
        return productResultDTOList.get(0);
    }
    /**
     * 组装尺寸
     */
    private ProductSizeResultDTO assembleSizeMsg(CartResultDTO cartResultDTO){
        // 组装
        ProductSizeParamsDTO sizeParamsDTO = new ProductSizeParamsDTO();
        sizeParamsDTO.setId(cartResultDTO.getSize());
        List<ProductSizeResultDTO> SizeResultDTOList = sizeMapper.query(sizeParamsDTO);
        return SizeResultDTOList.get(0);
    }

    /**
     * 组装颜色
     * @param cartResultDTO
     * @return
     */
    private ProductColorResultDTO assembleColorMsg(CartResultDTO cartResultDTO){
        // 组装
        ProductColorParamsDTO colorParamsDTO = new ProductColorParamsDTO();
        colorParamsDTO.setId(cartResultDTO.getColor());

        List<ProductColorResultDTO> colorResultDTOS = colorMapper.query(colorParamsDTO);
        return colorResultDTOS.get(0);
    }
    /**
     * 获取用户登录信息
     * @param userMsg
     * @param token
     * @return
     */
    private UserResultDTO  getUserMsg(Map<Object, Object> userMsg, String token) {
        UserResultDTO userResultDTO= (UserResultDTO) userMsg.get(token);
        return userResultDTO;
    }

    /**
     * 组装购物车信息
     * @param cartMap
     * @param userMsgDetail
     * @return
     */
    private CartParamsDTO assemble(Map<String, String> cartMap,UserResultDTO userMsgDetail) {
        CartParamsDTO cartParamsDTO = new CartParamsDTO();
        cartParamsDTO.setNumber(Integer.valueOf(cartMap.get("number")));
        cartParamsDTO.setProductId(Long.valueOf(cartMap.get("productId")));
        cartParamsDTO.setColor(cartMap.get("color"));
        cartParamsDTO.setName(cartMap.get("name"));
        cartParamsDTO.setSize(cartMap.get("size"));
        cartParamsDTO.setUserId(userMsgDetail.getUserId());
        return cartParamsDTO;
    }

    /**
     * 添加物品到购物车
     * @param cartParamsDTO
     * @return
     */
    private Integer addToCart(CartParamsDTO cartParamsDTO) {
        //加入购物车
        Integer flag = this.flag(cartParamsDTO);
        Integer add = null;
        if (flag == requiredMsg) {
            cartParamsDTO.setCreateTime(new Date());
            cartParamsDTO.setUpdateTime(new Date());
            cartParamsDTO.setStatus(effectiveStatus);
            add = cartMapper.add(cartParamsDTO);

        }
        return add;
    }

    /**
     * 判断入参是否符合添加购物车
     * @param cartParamsDTO
     * @return
     */
    private Integer flag(CartParamsDTO cartParamsDTO) {
        List cartParamsDTOList = new ArrayList();
        if (cartParamsDTO.getProductId() != null && cartParamsDTO.getProductId() != 0) {
            cartParamsDTOList.add(cartParamsDTO.getProductId());
        }
        if (StringUtils.isNotEmpty(cartParamsDTO.getSize())) {
            cartParamsDTOList.add(cartParamsDTO.getSize());
        }
        if (StringUtils.isNotEmpty(cartParamsDTO.getColor())) {
            cartParamsDTOList.add(cartParamsDTO.getColor());
        }

        if (cartParamsDTO.getNumber() != null && cartParamsDTO.getNumber() != 0) {
            cartParamsDTOList.add(cartParamsDTO.getNumber());
        }

        return cartParamsDTOList.size();
    }
}
