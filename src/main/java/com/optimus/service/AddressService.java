package com.optimus.service;

import com.optimus.dto.params.AddressParamsDTO;
import com.optimus.dto.results.AddressResultDTO;

import java.util.List;

public interface AddressService {
    List<AddressResultDTO> query(AddressParamsDTO addressParamsDTO);
}
