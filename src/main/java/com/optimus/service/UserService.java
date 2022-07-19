package com.optimus.service;

import com.optimus.dto.params.UserParamsDTO;
import com.optimus.dto.results.UserResultDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 查询
     * @param userParamsDTO
     * @return
     */
    Map<String,Object> login(UserParamsDTO userParamsDTO);

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UserResultDTO> paging(int pageNum,int pageSize);

    /**
     * 添加
     * @param userParamsDTO
     * @return
     */
    Integer add(UserParamsDTO userParamsDTO);
}
