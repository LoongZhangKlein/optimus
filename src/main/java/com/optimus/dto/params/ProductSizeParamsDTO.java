package com.optimus.dto.params;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductSizeParamsDTO implements Serializable {

  private static final long serialVersionUID = -4835354804014551200L;
  private Integer id;
  private Long productId;
  private String size;
  private Integer status;
  private Date createTime;
  private Date updateTime;
  private Integer createBy;
  private Integer updateBy;



}
