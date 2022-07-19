package com.optimus.service.Impl;

import com.optimus.dto.params.ProductDetailParamsDTO;
import com.optimus.dto.results.ProductDetailResultDTO;
import com.optimus.enums.GlobalEnum;
import com.optimus.exception.GlobalException;
import com.optimus.mapper.ColorMapper;
import com.optimus.mapper.ProductDetailMapper;
import com.optimus.mapper.SizeMapper;
import com.optimus.service.ProductDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DragonZhang
 */
@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Resource
    ProductDetailMapper productDetailMapper;
    @Resource
    ColorMapper colorMapper;
    @Resource
    SizeMapper sizeMapper;

    @Override
    public ProductDetailResultDTO query(ProductDetailParamsDTO productDetailParamsDTO) {
        if (productDetailParamsDTO == null ||productDetailParamsDTO.getProductId()==0) {
            throw new GlobalException(GlobalEnum.MSG_NOT_FULL);
        }
        ProductDetailResultDTO productDetailResultDTO = new ProductDetailResultDTO();

        // 拿到实体类
        List<String> sizeList = colorMapper.query(productDetailParamsDTO.getProductId());
        List<String> colorList = sizeMapper.query(productDetailParamsDTO.getProductId());
        productDetailResultDTO.setColorList(colorList);
        productDetailResultDTO.setSizeList(sizeList);
        //找到颜色字符串
        //截取字符串
        //返回装填
        return productDetailResultDTO;
    }

    private List<String> assembleList(String str) {
        List<String> strList = new ArrayList();
        String[] split = str.split("-");
        for (String childStr : split) {
            strList.add(childStr);
        }
        return strList;
    }
}
