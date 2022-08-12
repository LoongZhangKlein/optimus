package com.optimus.entity;


import lombok.Data;

import java.util.Date;
@Data
public class ProductStockDO {
  private Long id;
  private Long productId;
  private Integer number;
  private Integer status;
  private Date createTime;
  private Date updateTime;
  private Integer createBy;
  private Integer updateBy;



}
