package com.optimus.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SizeMapper {
    /**
     * 查找所有颜色
     * @param parentId
     * @return
     */
    List<String> query(Long parentId);
}
