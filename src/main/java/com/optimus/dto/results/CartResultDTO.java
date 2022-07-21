package com.optimus.dto.results;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CartResultDTO implements Serializable {
  private static final long serialVersionUID = -2516795303830752186L;
  private Long id;
  private Long productId;
  private Long userId;
  private String name;
  private Integer size;
  private String sizeStr;
  private Double sumPrice;
  private Double sumDisCountPrice;
  private Integer color;
  private String colorStr;
  private Double price;
  private Double disCountPrice;
  private Integer number;
  private String images;
  private Integer status;
  private Date createTime;
  private Date updateTime;
  private Integer createBy;
  private Integer updateBy;



}
