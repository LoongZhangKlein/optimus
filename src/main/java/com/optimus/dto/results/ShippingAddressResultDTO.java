package com.optimus.dto.results;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ShippingAddressResultDTO implements Serializable {

  private static final long serialVersionUID = -9103393816005741610L;
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
