package com.optimus.service.Impl;

import com.optimus.dto.params.PageParamsDTO;
import com.optimus.dto.params.ProductParamsDTO;
import com.optimus.dto.results.ProductResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.exception.GlobalException;
import com.optimus.mapper.ProductMapper;
import com.optimus.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DragonZhang
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    ProductMapper productMapper;
    @Override
    public List<ProductResultDTO> queryAll(ProductParamsDTO producParamstDTO) {
        List<ProductResultDTO> productResultDTOSList = productMapper.query(producParamstDTO);
        return null;
    }

    @Override
    public PageParamsDTO queryPage(PageParamsDTO pageParamsDTO, ProductParamsDTO productParamsDTO) {
        int curPage= pageParamsDTO.getCurPage();
        int pageSize= pageParamsDTO.getPageSize();
        if (curPage<1 || pageSize<=0){
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        Integer totalCount=null;
        if (totalCount==null){
            totalCount=productMapper.count(productParamsDTO);
        }
        int totalPages=(totalCount  +  pageSize  - 1) / pageSize;
        int offset=(curPage-1)*pageSize;
        List<ProductResultDTO> productResultDTOS = productMapper.queryPage(offset, pageSize, productParamsDTO);
        pageParamsDTO.setCurPage(pageParamsDTO.getCurPage());
        pageParamsDTO.setList(productResultDTOS);
        pageParamsDTO.setTotalCount(totalCount);
        pageParamsDTO.setTotalPage(totalPages);
        return pageParamsDTO;
    }

    @Override
    public PageParamsDTO sort(PageParamsDTO pageParamsDTO, ProductParamsDTO productParamsDTO) {
        if (pageParamsDTO ==null || productParamsDTO==null){
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        // 当前页
        int curPage= pageParamsDTO.getCurPage();
        // 页数
        int pageSize= pageParamsDTO.getPageSize();
        if (curPage<1 || pageSize<=0){
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        //总条数
        Integer totalCount=null;
        if (totalCount==null){
            totalCount=productMapper.count(productParamsDTO);
        }
        // 总页数
        int totalPages=(totalCount  +  pageSize  - 1) / pageSize;
        // 分页入参
        int offset=(curPage-1)*pageSize;
        List<ProductResultDTO> sortResList=null;
        if (productParamsDTO.getCategoryId()!=null){
            sortResList = productMapper.sort(offset, pageSize, productParamsDTO);
        }
        pageParamsDTO.setList(sortResList);
        pageParamsDTO.setTotalCount(totalCount);
        pageParamsDTO.setCurPage(curPage);
        return pageParamsDTO;
    }



}
