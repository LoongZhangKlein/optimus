package com.optimus.dto.params;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderDetailParamsDTO implements Serializable {
  private static final long serialVersionUID = -6369103002163208075L;
  private Long id;
  private Integer orderId;
  private Integer orderNumber;
  private String storeName;
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
