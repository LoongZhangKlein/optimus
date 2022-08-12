package com.optimus.dto.results;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ProductStockResultDTO implements Serializable {
    private static final long serialVersionUID = 785014689027874928L;
    private Long id;
    private Long productId;
    private Integer number;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private Integer createBy;
    private Integer updateBy;
}
