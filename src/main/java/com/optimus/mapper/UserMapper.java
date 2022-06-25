package com.optimus.mapper;

import com.optimus.dto.params.UserParamsDTO;
import com.optimus.dto.results.UserResultDTO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
@Mapper
public interface UserMapper {
    /**
     * 查询
     * @param userParamsDTO
     * @return
     */
    List<UserResultDTO> query(UserParamsDTO userParamsDTO);
    /**
     * 注册
     */
    Integer add(UserParamsDTO userParamsDTO);
    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UserResultDTO> paging(int pageNum,int pageSize);
}
