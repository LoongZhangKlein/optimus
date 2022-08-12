package com.optimus.dto.params;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductStockParamsDTO implements Serializable {
  private static final long serialVersionUID = -6651401155420405023L;
  private Long id;
  private Long productId;
  private Integer number;
  private Integer status;
  private Date createTime;
  private Date updateTime;
  private Integer createBy;
  private Integer updateBy;



}
