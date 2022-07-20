package com.optimus.dto.results;


import lombok.Data;

import java.util.Date;

@Data
public class CartResultDTO {
  private Long id;
  private Long productId;
  private Long userId;
  private String name;
  private Integer size;
  private String sizeStr;
  private Integer color;
  private String colorStr;
  private Double price;
  private Integer number;
  private String images;
  private Integer status;
  private Date createTime;
  private Date updateTime;
  private Integer createBy;
  private Integer updateBy;



}
