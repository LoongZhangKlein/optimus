package com.optimus.entity;


import lombok.Data;
import org.springframework.boot.context.properties.bind.DataObjectPropertyName;

import java.util.Date;
@Data
public class OrderInfoDo {

  private Long id;
  private Long orderNumber;
  private Long userId;
  private String storeName;
  private Integer storeId;
  private Double orderMoney;
  private Double payMoney;
  private Integer productNumber;
  private String orderDesc;
  private String payType;
  private String orderStatus;
  private String consigneeAddress;
  private String consigneeName;
  private String thirdPayNumber;
  private String payDetail;
  private String consigneeDetail;
  private Integer status;
  private Date createTime;
  private Date updateTime;
  private Integer createBy;
  private Integer updateBy;





}
