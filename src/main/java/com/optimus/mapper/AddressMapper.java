package com.optimus.mapper;

import com.optimus.dto.params.AddressParamsDTO;
import com.optimus.dto.params.UserParamsDTO;
import com.optimus.dto.results.AddressResultDTO;
import com.optimus.dto.results.UserResultDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    /**
     * 查询
     * @param addressParamsDTO
     * @return
     */
    List<AddressResultDTO> query(AddressParamsDTO addressParamsDTO);

}
