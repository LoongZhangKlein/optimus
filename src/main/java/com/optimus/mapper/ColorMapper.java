package com.optimus.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ColorMapper {
    List<String> query(Long parentId);
}
