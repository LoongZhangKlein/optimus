package com.optimus.service.Impl;


import com.github.pagehelper.PageHelper;
import com.optimus.dto.params.AddressParamsDTO;
import com.optimus.dto.params.UserParamsDTO;
import com.optimus.dto.results.AddressResultDTO;
import com.optimus.dto.results.UserResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.exception.GlobalException;
import com.optimus.mapper.AddressMapper;
import com.optimus.service.AddressService;
import com.optimus.service.UserService;
import com.optimus.utils.RedisUtil;
import com.optimus.utils.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    AddressMapper addressMapper;
    @Resource
    RedisUtil redisUtil;


    @Override
    public List<AddressResultDTO> query(AddressParamsDTO addressParamsDTO) {
        List<AddressResultDTO> query = addressMapper.query(addressParamsDTO);
        return query;
    }
}
