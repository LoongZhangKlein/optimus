package com.optimus.controller;

import com.alibaba.fastjson.JSON;
import com.optimus.commons.R;
import com.optimus.dto.params.CartParamsDTO;
import com.optimus.dto.results.CartResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.service.CartService;
import com.optimus.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/cart")
public class CartController extends HttpServlet{
    private final String token="token";
    @Resource
    CartService cartService;
    @Resource
    HttpServletRequest req;
    @Resource
    RedisUtil redisUtil;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public R add(@RequestBody Map<String,String> cartMap){

        if (CollectionUtils.isEmpty(cartMap)){
            return R.fail(GlobalEnum.NOT_LOG_IN);
        }
        Integer add = cartService.add(cartMap);
        if (add>0){
            return R.creatR(add, GlobalEnum.SUCCESS);
        }
        return R.fail(GlobalEnum.MSG_BLANK);
    }
    @RequestMapping(value = "queryAll",method = RequestMethod.GET)
    public R query(CartParamsDTO cartParamsDTO){
        List<CartResultDTO> query = cartService.query(cartParamsDTO);
        if (CollectionUtils.isEmpty(query)){
            return R.fail(GlobalEnum.MSG_BLANK);
        }
        return R.creatR(query,GlobalEnum.SUCCESS);
    }
}
