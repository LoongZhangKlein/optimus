package com.optimus.entity;


import lombok.Data;

import java.util.Date;
@Data
public class CartDo {
  private long id;
  private long productId;
  private Long userId;
  private String name;
  private double size;
  private String color;
  private double price;
  private long number;
  private String images;
  private Integer status;
  private Date createTime;
  private Date updateTime;
  private Integer createBy;
  private Integer updateBy;



}
