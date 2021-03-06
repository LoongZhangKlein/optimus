package com.optimus.controller;

import com.optimus.commons.R;
import com.optimus.dto.params.UserParamsDTO;
import com.optimus.dto.results.UserResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.service.UserService;
import com.optimus.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController  {
    @Resource
    UserService userService;
    @Resource
    RedisUtil redisUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public R login(@RequestBody UserParamsDTO userParamsDTO) {
        Map<String, Object> login = userService.login(userParamsDTO);
        if (CollectionUtils.isEmpty(login)) {
            return R.fail();
        }

        return R.creatR(login,GlobalEnum.SUCCESS);

    }

    @RequestMapping("/paging")
    public R paging(@RequestParam int pageNum, @RequestParam int pageSize) {
        List<UserResultDTO> paging = userService.paging(pageNum, pageSize);
        return R.ok(paging);
    }

    @RequestMapping("/register")
    public R register(@RequestBody UserParamsDTO userParamsDTO) {
        Integer add = userService.add(userParamsDTO);
        if (add > 0) {
            return R.creatR(add, GlobalEnum.SUCCESS);
        } else {
            return R.fail();
        }

    }
}
