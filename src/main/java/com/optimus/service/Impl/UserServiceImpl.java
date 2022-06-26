package com.optimus.service.Impl;


import com.github.pagehelper.IPage;
import com.github.pagehelper.Page;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    RedisUtil redisUtil;

    @Override
    public String login(UserParamsDTO userParamsDTO) {
        if (userParamsDTO == null) {
            throw new GlobalException(GlobalEnum.MSG_NOTFULL);
        }
        List<UserResultDTO> query = new ArrayList<>();
        if (StringUtils.isNotEmpty(userParamsDTO.getUserName()) && StringUtils.isNotEmpty(userParamsDTO.getPassWord())) {
            query = userMapper.query(userParamsDTO);
        }
        if (query == null||query.size()==0) {
            throw new GlobalException(GlobalEnum.ERROR);
        }else {
            String token = TokenUtils.token(userParamsDTO.getUserName(), userParamsDTO.getPassWord());
            redisUtil.set("token", token, 60);
            System.out.println(redisUtil.get("token"));
            return token;
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
            throw new GlobalException(GlobalEnum.MSG_NOTFULL);
        }
        if (StringUtils.isEmpty(userParamsDTO.getUserName()) || StringUtils.isEmpty(userParamsDTO.getPassWord()) || userParamsDTO.getPhone() == null) {
            throw new GlobalException(GlobalEnum.MSG_NOTFULL);
        }
        userParamsDTO.setCreateTime(new Date());
        userParamsDTO.setStatus(1);
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
