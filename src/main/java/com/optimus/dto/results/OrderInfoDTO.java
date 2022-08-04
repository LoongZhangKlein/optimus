package com.optimus.dto.results;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderInfoDTO implements Serializable {

  private static final long serialVersionUID = 2299708890524521676L;
  private Long id;
  private Integer orderNumber;
  private Integer userId;
  private String storeName;
  private Integer storeId;
  private String orderMoney;
  private String payMoney;
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
