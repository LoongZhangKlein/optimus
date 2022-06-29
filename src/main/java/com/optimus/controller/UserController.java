package com.optimus.controller;

import com.optimus.commons.R;
import com.optimus.dto.params.UserParamsDTO;
import com.optimus.dto.results.UserResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.service.UserService;
import com.optimus.utils.RedisUtil;
import com.optimus.utils.TokenUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @RequestMapping("/login")
    public R login(@RequestBody UserParamsDTO userParamsDTO) { Map<String,Object> login = userService.login(userParamsDTO);
           if (login == null) {
                  return R.fail();
        }
        return R.creatR(login, GlobalEnum.SUCCESS);
    }

    @RequestMapping("/paging")
    public R paging(@RequestParam int pageNum, @RequestParam int pageSize) {
        List<UserResultDTO> paging = userService.paging(pageNum, pageSize);
        return R.ok(paging);
    }

    @RequestMapping("/register")
    public R register(@RequestBody UserParamsDTO userParamsDTO){
        Integer add = userService.add(userParamsDTO);
        if (add>0){
            return R.creatR(add,GlobalEnum.SUCCESS);
        }else {
            return R.fail();
        }

    }
}
