package com.optimus.dto.params;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class OrderDetailParamsDTO implements Serializable {
  private static final long serialVersionUID = -6369103002163208075L;
  private Long id;
  private Long orderNumber;
  private String storeName;
  private String productSize;
  private Integer productNumber;
  private Double productPrice;
  private Double productOrginPrice;
  private String productName;
  private Integer productId;
  private Integer storeId;
  private Integer status;
  private Date createTime;
  private Date updateTime;
  private Integer createBy;
  private Integer updateBy;


}
