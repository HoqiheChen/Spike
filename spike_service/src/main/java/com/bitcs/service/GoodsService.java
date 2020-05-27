package com.bitcs.service;

import com.bitcs.VO.GoodsVO;
import com.bitcs.dao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品的Service类
 *
 * @author GeChen
 */
@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVO> listGoodsVO() {
        return goodsDao.listGoodsVO();
    }
}
