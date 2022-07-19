package com.optimus.service.Impl;


import com.github.pagehelper.PageHelper;
import com.optimus.dto.params.UserParamsDTO;
import com.optimus.dto.results.UserResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.exception.GlobalException;
import com.optimus.mapper.UserMapper;
import com.optimus.service.UserService;
import com.optimus.utils.RedisUtil;
import com.optimus.utils.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    RedisUtil redisUtil;

    @Override
    public Map<String, Object> login(UserParamsDTO userParamsDTO) {
        if (userParamsDTO == null) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        List<UserResultDTO> query = new ArrayList<>();
        if (StringUtils.isNotEmpty(userParamsDTO.getUserName()) && StringUtils.isNotEmpty(userParamsDTO.getPassWord())) {
            query = userMapper.query(userParamsDTO);
        } else if (StringUtils.isNotEmpty(userParamsDTO.getPhone()) && StringUtils.isNotEmpty(userParamsDTO.getPassWord())) {
            query = userMapper.query(userParamsDTO);
        }
        if (query == null || query.size() == 0) {
            throw new GlobalException(GlobalEnum.ERROR);
        } else {
            String token = TokenUtils.token(userParamsDTO.getUserName(), userParamsDTO.getPassWord());
            query.get(0).setToken(token);
            // 隐藏用户密码 后期可改为加密
            query.get(0).setPassWord(null);
            Map<String, Object> redisMap = new HashMap();
            redisMap.put(token, query.get(0));
            System.out.println(redisMap);
            redisUtil.hmset(token, redisMap, 3600);
            HashMap<String, Object> userMsgMap = new HashMap<>(2);
            userMsgMap.put("token", token);
            userMsgMap.put("userMsg", query.get(0));
            return userMsgMap;
        }

    }

    @Override
    public List<UserResultDTO> paging(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserParamsDTO userParamsDTO = new UserParamsDTO();
        List<UserResultDTO> paging = userMapper.query(userParamsDTO);
        return paging;
    }

    @Override
    public Integer add(UserParamsDTO userParamsDTO) {
        if (userParamsDTO == null) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        if (StringUtils.isEmpty(userParamsDTO.getPhone()) || StringUtils.isEmpty(userParamsDTO.getPassWord()) || userParamsDTO.getPhone() == null) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        userParamsDTO.setCreateTime(new Date());
        userParamsDTO.setStatus(0);
        userParamsDTO.setUpdateTime(new Date());
        userParamsDTO.setEnable(1);
        Integer add = userMapper.add(userParamsDTO);
        return add;
    }

    public boolean loginCheck() {
        String token = (String) redisUtil.get("token");
        boolean verify = TokenUtils.verify(token);
        return verify;
    }

}
