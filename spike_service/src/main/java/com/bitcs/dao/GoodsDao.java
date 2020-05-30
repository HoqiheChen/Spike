package com.bitcs.dao;

import com.bitcs.VO.GoodsVO;
import com.bitcs.domain.SpikeGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author GeChen
 */
@Mapper
public interface GoodsDao {
    /**
     * 查出所有的秒杀商品信息
     *
     * @return
     */
    @Select("select g.*,mg.stock_count,mg.start_date,mg.miaosha_price,mg.end_date from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    List<GoodsVO> listGoodsVO();

    /**
     * 根据商品id查出秒杀商品信息
     *
     * @param goodsId
     * @return
     */
    @Select("select g.*,mg.stock_count,mg.start_date,mg.miaosha_price,mg.end_date from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    GoodsVO getGoodsVOByGoodsId(@Param("goodsId") long goodsId);

    /**
     * 根据商品信息减少商品的库存
     *
     * @param goods
     * @return
     */
    @Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
    int reduce(SpikeGoods goods);
}
