package com.optimus.dto.params;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDetailParamsDTO implements Serializable {

  private static final long serialVersionUID = 4572578411623573943L;
  private Long id;
  private Long productId;
  private String title;
  private String images;
  private String size;
  private String color;
  private String activity;
  private String service;
  private Integer status;
  private Data createTime;
  private Data updateTime;
  private Integer createBy;
  private Integer updateBy;
  private Double promotionPrice;

  

}
