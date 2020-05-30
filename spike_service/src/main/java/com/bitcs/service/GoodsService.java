package com.bitcs.service;

import com.bitcs.VO.GoodsVO;
import com.bitcs.dao.GoodsDao;
import com.bitcs.domain.SpikeGoods;
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

    /**
     * 得到所有的商品
     *
     * @return
     */
    public List<GoodsVO> listGoodsVO() {
        return goodsDao.listGoodsVO();
    }

    /**
     * 通过商品id获取商品信息
     *
     * @param goodsId
     * @return
     */
    public GoodsVO getGoodsVOByGoodsId(long goodsId) {
        return goodsDao.getGoodsVOByGoodsId(goodsId);
    }

    /**
     * 通过商品信息来减少库存
     *
     * @param goodsVO
     */
    public void reduceStock(GoodsVO goodsVO) {
        SpikeGoods spikeGoods = new SpikeGoods();
        spikeGoods.setGoodsId(goodsVO.getId());
        goodsDao.reduce(spikeGoods);
    }
}
