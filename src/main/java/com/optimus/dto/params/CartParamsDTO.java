package com.optimus.dto.params;


import lombok.Data;

import java.util.Date;

@Data
public class CartParamsDTO {
  private Long id;
  private Long productId;
  private Long userId;
  private String name;
  private String size;
  private String color;
  private Double price;
  private Integer number;
  private String images;
  private Integer status;
  private Date createTime;
  private Date updateTime;
  private Integer createBy;
  private Integer updateBy;



}
