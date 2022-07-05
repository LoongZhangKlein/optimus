package com.optimus.dto.results;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class MenuResultDTO implements Serializable {
    private Integer id;
    private Integer parentId;
    private String name;
    private Integer level;
    private Integer sortOrder;
    private Integer isShow;
    private String image;
    private String status;
    private Date createTime;
    private Date updateTime;
    private BigInteger createBy;
    private BigInteger updateBy;
    private SecondMenuDTO secondMenuDTO;
    private List<SecondMenuDTO> secondMenuDTOList;
    private List<MenuResultDTO> menuResultDTOList;
}
