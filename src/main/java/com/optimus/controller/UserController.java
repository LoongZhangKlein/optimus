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
/**
 * @author DragonZhang
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController  {
    @Resource
    UserService userService;
    @Resource
    RedisUtil redisUtil;

    /**
     * 登录
     * @param userParamsDTO
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public R login(@RequestBody UserParamsDTO userParamsDTO) {
        // 调用service层登录接口
        Map<String, Object> login = userService.login(userParamsDTO);
        // 判断返回信息是否为空 为空
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
