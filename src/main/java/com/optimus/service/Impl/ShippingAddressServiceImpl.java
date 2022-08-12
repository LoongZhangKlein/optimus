package com.optimus.service.Impl;

import com.optimus.commons.UserMessage;
import com.optimus.dto.params.ShippingAddressParamsDTO;
import com.optimus.dto.results.ShippingAddressResultDTO;
import com.optimus.dto.results.UserResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.exception.GlobalException;
import com.optimus.mapper.ShippingAddressMapper;
import com.optimus.service.ShippingAddressService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
    @Resource
    ShippingAddressMapper shippingAddressMapper;
    @Resource
    UserMessage userMessage;

    @Override
    public List<ShippingAddressResultDTO> query(ShippingAddressParamsDTO shippingAddressParamsDTO) {
        if (shippingAddressParamsDTO == null) {
            throw new GlobalException(GlobalEnum.AUTH_DENIED);
        }
        List<ShippingAddressResultDTO> shippingAddressResList = shippingAddressMapper.query(shippingAddressParamsDTO);

        return shippingAddressResList;
    }

    @Override
    public Integer add(ShippingAddressParamsDTO shippingAddressParamsDTO) {
        if (shippingAddressParamsDTO == null) {
            throw new GlobalException(GlobalEnum.AUTH_DENIED);
        }
        // todo 有没有更好的判断对象的方法  除了使用反射
        // 暂时没找到
        Integer add = null;
        // 获取用户信息
        UserResultDTO userMsg = this.userMessage.getUserMsg(shippingAddressParamsDTO.getToken());
        if (userMsg==null){
            throw new GlobalException(GlobalEnum.NOT_LOG_IN);
        }
        shippingAddressParamsDTO.setUserId(userMsg.getUserId());

        if (shippingAddressParamsDTO.getUserId() != null && StringUtils.isNotEmpty(shippingAddressParamsDTO.getMobile())
                && StringUtils.isNotEmpty(shippingAddressParamsDTO.getLinkman()) && StringUtils.isNotEmpty(shippingAddressParamsDTO.getAddress())) {
            addParams(shippingAddressParamsDTO);
            add = shippingAddressMapper.add(shippingAddressParamsDTO);
        }
        return add;
    }

    @Override
    public Integer update(ShippingAddressParamsDTO shippingAddressParamsDTO) {
        if (shippingAddressParamsDTO == null) {
            throw new GlobalException(GlobalEnum.AUTH_DENIED);
        }
        Integer update = null;
        if (shippingAddressParamsDTO.getId() != null) {
            shippingAddressParamsDTO.setUpdateTime(new Date());
            update = shippingAddressMapper.update(shippingAddressParamsDTO);
        }
        return update;
    }

    private ShippingAddressParamsDTO addParams(ShippingAddressParamsDTO shippingAddressParamsDTO) {
        shippingAddressParamsDTO.setStatus(0);
        shippingAddressParamsDTO.setUpdateTime(new Date());
        return shippingAddressParamsDTO;
    }
}
