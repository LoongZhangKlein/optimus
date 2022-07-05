package com.optimus.mapper;

import com.optimus.dto.params.MenuParamsDTO;
import com.optimus.dto.results.MenuResultDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    /**
     * 首页菜单查询
     * @param menuParamsDTO
     * @return
     */
    List<MenuResultDTO> query(MenuParamsDTO menuParamsDTO);


}
