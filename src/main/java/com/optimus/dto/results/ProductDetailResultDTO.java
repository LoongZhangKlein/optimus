package com.optimus.dto.results;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetailResultDTO {

  private long id;
  private long productId;
  private String title;
  private String images;
  private String size;
  private String color;
  private List<String> sizeList;
  private List<String> colorList;
  private String activity;
  private String service;
  private Integer status;
  private Data createTime;
  private Data updateTime;
  private Integer createBy;
  private Integer updateBy;
  private double promotionPrice;

  

}
