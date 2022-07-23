package com.optimus.dto.results;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetailResultsDTO implements Serializable {
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
