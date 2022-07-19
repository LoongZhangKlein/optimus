package com.optimus.service.Impl;


import com.optimus.dto.params.CartParamsDTO;
import com.optimus.dto.results.CartResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.exception.GlobalException;
import com.optimus.mapper.CartMapper;
import com.optimus.service.CartService;
import com.optimus.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CartServiceImpl implements CartService {
    private final int requiredMsg = 6;
    private final int effectiveStatus = 0;
    private final int error = -1;
    @Resource
    CartMapper cartMapper;
    @Resource
    RedisUtil redisUtil;
    @Resource
    HttpServletRequest request;

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
        // 组装DTO
        CartParamsDTO cartParamsDTO = assemble(cartMap);
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

    @Override
    public List<CartResultDTO> query(CartParamsDTO cartParamsDTO) {
        if (cartParamsDTO == null) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        List<CartResultDTO> query = cartMapper.query(cartParamsDTO);
        return query;
    }

    @Override
    public Integer update(CartParamsDTO cartParamsDTO) {
        if (cartParamsDTO == null) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        cartParamsDTO.setUpdateTime(new Date());
        Integer update = cartMapper.update(cartParamsDTO);
        return update;
    }

    private Map<String, String> checkLogin(Map<String, String> cartMap) {
        HttpSession session = request.getSession();
        Map<String, Object> userMsg = (Map<String, Object>) session.getAttribute("userMsg");
        cartMap.put("userId", (String) userMsg.get("userId"));

        return cartMap;
    }

    private CartParamsDTO assemble(Map<String, String> cartMap) {
        CartParamsDTO cartParamsDTO = new CartParamsDTO();
        cartParamsDTO.setNumber(Integer.valueOf(cartMap.get("number")));
        cartParamsDTO.setProductId(Long.valueOf(cartMap.get("productId")));
        cartParamsDTO.setColor(cartMap.get("color"));
        cartParamsDTO.setName(cartMap.get("name"));
        cartParamsDTO.setPrice(Double.valueOf(cartMap.get("price")));
        cartParamsDTO.setSize(cartMap.get("size"));
        cartParamsDTO.setImages(cartMap.get("images"));
        return cartParamsDTO;
    }

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

    private Integer flag(CartParamsDTO cartParamsDTO) {
        List cartParamsDTOList = new ArrayList();
        if (cartParamsDTO.getProductId() != null && cartParamsDTO.getProductId() != 0) {
            cartParamsDTOList.add(cartParamsDTO.getProductId());
        }
        if (StringUtils.isNotEmpty(cartParamsDTO.getName())) {
            cartParamsDTOList.add(cartParamsDTO.getName());
        }
        if (StringUtils.isNotEmpty(cartParamsDTO.getSize())) {
            cartParamsDTOList.add(cartParamsDTO.getSize());
        }
        if (StringUtils.isNotEmpty(cartParamsDTO.getColor())) {
            cartParamsDTOList.add(cartParamsDTO.getColor());
        }
        if (cartParamsDTO.getPrice() != null && cartParamsDTO.getPrice() != 0) {
            cartParamsDTOList.add(cartParamsDTO.getPrice());
        }
        if (cartParamsDTO.getNumber() != null && cartParamsDTO.getNumber() != 0) {
            cartParamsDTOList.add(cartParamsDTO.getNumber());
        }

        return cartParamsDTOList.size();
    }
}
