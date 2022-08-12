package com.optimus.dto.params;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class OrderInfoParamsDTO implements Serializable {
  private static final long serialVersionUID = -5941146101068732891L;
  private Long id;
  private Long orderNumber;
  private Long userId;
  private String token;
  private String storeName;
  private Integer storeId;
  private Double orderMoney;
  private Double payMoney;
  private Integer productNumber;
  private String orderDesc;
  private String payType;
  private Integer orderStatus;
  private Integer addressID;
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
  private List<Long> idList;





}
