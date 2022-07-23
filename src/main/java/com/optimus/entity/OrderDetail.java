package com.optimus.entity;

import lombok.Data;

@Data
public class OrderDetail {
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


}
