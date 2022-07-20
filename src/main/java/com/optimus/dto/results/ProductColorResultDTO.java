package com.optimus.dto.results;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductColorResultDTO implements Serializable {
  private static final long serialVersionUID = 5748348555677009741L;
  private Integer id;
  private Long productId;
  private String color;
  private Long status;
  private Date createTime;
  private Date updateTime;
  private Integer createBy;

}
