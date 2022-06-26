package com.optimus.dto.results;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressResultDTO implements Serializable {
    private Integer id;
    private Integer level;
    private String name;
    private String parentId;
    private String currentId;
    private String status;
}
