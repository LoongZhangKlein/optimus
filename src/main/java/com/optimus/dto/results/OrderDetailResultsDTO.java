package com.optimus.dto.results;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderDetailResultsDTO implements Serializable {
  private static final long serialVersionUID = 5492390941698795667L;
  private Long id;
  private Integer orderId;
  private Long orderNumber;
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
