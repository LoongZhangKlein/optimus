package com.optimus.dto.params;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressParamsDTO implements Serializable {
    private Integer id;
    private Integer level;
    private String name;
    private String parentId;
    private String currentId;
    private String status;
}
