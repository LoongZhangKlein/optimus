package com.optimus.entity;

import lombok.Data;

@Data
public class ProductDetailDo {

  private long id;
  private long productId;
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
  private double promotionPrice;

  

}
