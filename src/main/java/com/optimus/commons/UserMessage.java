package com.optimus.commons;

import com.optimus.dto.results.UserResultDTO;
import com.optimus.utils.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
@Component
public class UserMessage {
    @Resource
    RedisUtil redisUtil;
    public UserResultDTO getUserMsg(String token){
        Map<Object, Object> hmget = redisUtil.hmget(token);
        UserResultDTO userResultDTO = (UserResultDTO) hmget.get(token);
        return userResultDTO;
    }


}
