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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    /**
     *     在创建类的多个对象时，用static修饰的常量在内存中只有一份拷贝。不用static修饰则可能有多份拷贝。
     */
    private static final int effective=0;
    private static final int zeroSize=0;
    private static final int second=3600;
    @Resource
    UserMapper userMapper;
    @Resource
    RedisUtil redisUtil;

    /**
     * 登录接口
     * @param userParamsDTO
     * @return
     */
    @Override
    public Map<String, Object> login(UserParamsDTO userParamsDTO) {
        // todo 申请小程序后换用一键登录
        if (userParamsDTO == null) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        List<UserResultDTO> query = new ArrayList<>();
        // 判断信息是否为空
        if (StringUtils.isNotEmpty(userParamsDTO.getUserName()) && StringUtils.isNotEmpty(userParamsDTO.getPassWord())) {
            // 通过账号密码登录
            query = userMapper.query(userParamsDTO);
        } else if (StringUtils.isNotEmpty(userParamsDTO.getPhone()) && StringUtils.isNotEmpty(userParamsDTO.getPassWord())) {
            // 通过手机号密码登录
            query = userMapper.query(userParamsDTO);
        }
        // 查询用户信息为空直接报错
        if (query == null || query.size() == zeroSize) {
            throw new GlobalException(GlobalEnum.ERROR);
        } else {
            // 登录成功后生成用户唯一标识
            String token = TokenUtils.token(userParamsDTO.getUserName(), userParamsDTO.getPassWord());
            query.get(0).setToken(token);
            // todo 隐藏用户密码 后期可改为加密
            query.get(0).setPassWord(null);
            // 生成用户信息 用于存放到redis
            Map<String, Object> redisMap = new HashMap();
            redisMap.put(token, query.get(0));
            log.debug("token信息:{}",token);
            log.debug("redisMap:{}",redisMap);
            //存放到redis
            redisUtil.hmset(token, redisMap, second);
            // todo 其实这里是废话  只发送token就可 用户信息需要调用接口重新查询  不够严谨!!!
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

    /**
     * 获取微信用户信息
     * @param userParamsDTO
     * @return
     */
    @Override
    public Integer add(UserParamsDTO userParamsDTO) {
        if (userParamsDTO == null) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        // 用户必要信息 未填入直接报错
        if (StringUtils.isEmpty(userParamsDTO.getPhone()) || StringUtils.isEmpty(userParamsDTO.getPassWord()) || userParamsDTO.getPhone() == null) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        List<UserResultDTO> query = userMapper.query(userParamsDTO);
        // 用户已存在直接报错
        if (query!=null){
            throw new GlobalException(GlobalEnum.USER_EXISTS);
        }
        // 设置用户相关信息
        userParamsDTO.setCreateTime(new Date());
        userParamsDTO.setStatus(effective);
        userParamsDTO.setUpdateTime(new Date());
        userParamsDTO.setEnable(effective);
        Integer add = userMapper.add(userParamsDTO);
        return add;
    }

    public boolean loginCheck() {
        String token = (String) redisUtil.get("token");
        boolean verify = TokenUtils.verify(token);
        return verify;
    }

}
