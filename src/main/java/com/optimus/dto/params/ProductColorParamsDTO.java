package com.optimus.dto.params;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductColorParamsDTO implements Serializable {
  private static final long serialVersionUID = 2495695292844778594L;
  private Integer id;
  private Long productId;
  private String color;
  private Long status;
  private Date createTime;
  private Date updateTime;
  private Integer createBy;

}
