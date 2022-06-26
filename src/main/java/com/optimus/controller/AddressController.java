package com.optimus.controller;

import com.optimus.commons.R;
import com.optimus.dto.params.AddressParamsDTO;
import com.optimus.dto.params.UserParamsDTO;
import com.optimus.dto.results.AddressResultDTO;
import com.optimus.dto.results.UserResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.service.AddressService;
import com.optimus.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    AddressService addressService;
    @RequestMapping("/address")
    public R address(@RequestBody AddressParamsDTO addressParamsDTO) {
        List<AddressResultDTO> query = addressService.query(addressParamsDTO);
        return R.creatR(query, GlobalEnum.SUCCESS);
    }


}
