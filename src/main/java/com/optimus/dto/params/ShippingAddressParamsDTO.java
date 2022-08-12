package com.optimus.dto.params;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ShippingAddressParamsDTO implements Serializable {

  private static final long serialVersionUID = 5056590789340936838L;
  private Integer id;
  private Long userId;
  private String mobile;
  private String token;
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
