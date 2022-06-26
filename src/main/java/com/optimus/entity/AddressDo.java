package com.optimus.entity;

import lombok.Data;

@Data
public class AddressDo {
    private Integer id;
    private Integer level;
    private String name;
    private String parentId;
    private String currentId;
    private String status;
}
