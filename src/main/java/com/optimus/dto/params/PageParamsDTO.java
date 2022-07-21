package com.optimus.dto.params;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel
@Data
public class PageParamsDTO<T> implements Serializable {

    private static final long serialVersionUID = 1412759446332294208L;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;

    @ApiModelProperty(value = "当前页")
    private Integer curPage;

    @ApiModelProperty(value = "数据总条数")
    private long totalCount;


    @ApiModelProperty(value = "总页数")
    private Integer totalPage;


    @ApiModelProperty(value = "数据LIST")
    private List<T> list = new ArrayList<T>();

    @ApiModelProperty(value = "数据合计")
    private Object dataTotal;
    @ApiModelProperty(value = "价格总计")
    private Double sumPrice;
    @ApiModelProperty(value = "折扣总计")
    private Double sumDisCount;
    public PageParamsDTO() {
    }

    public PageParamsDTO(Integer curPage, Integer pageSize, long totalItems) {
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.totalCount = totalItems;
        this.totalPage = (int) Math.ceil((double) totalItems / pageSize);
    }


    public PageParamsDTO(PageInfo page) {
        this.list.addAll(page.getList());
        this.totalCount = (int) page.getTotal();
        this.pageSize = page.getSize();
        this.curPage = page.getPageNum();
        this.totalPage = page.getPages();
    }

}
