package com.optimus.dto.results;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class SecondMenuDTO implements Serializable {
    private Integer id;
    private String parentId;
    private String name;
    private Integer sortOrder;
    private Integer isShow;
    private String image;
    private String status;
    private Date createTime;
    private Date updateTime;
    private BigInteger createBy;
    private BigInteger updateBy;
    private ThirdResultDTO thirdResultDTO;
    private List<ThirdResultDTO> thirdResultDTOList;
}
