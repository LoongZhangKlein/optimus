package com.optimus.dto.results;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ProductSizeResultDTO implements Serializable {
  private static final long serialVersionUID = -1162665892818379025L;
  private Integer id;
  private Integer productId;
  private String size;
  private Integer status;
  private Date createTime;
  private Date updateTime;
  private Integer createBy;
  private Integer updateBy;



}
