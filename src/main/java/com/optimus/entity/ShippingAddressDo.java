package com.optimus.entity;


import lombok.Data;

import java.util.Date;
@Data
public class ShippingAddressDo {

  private Integer id;
  private Integer userId;
  private String mobile;
  private String linkman;
  private String address;
  private String addressDetail;
  private Integer defaultAddress;
  private Integer status;
  private Date createTime;
  private Date updateTime;
  private Integer createBy;
  private Integer updateBy;



}
