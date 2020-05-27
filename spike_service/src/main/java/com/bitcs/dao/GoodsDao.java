package com.bitcs.dao;

import com.bitcs.VO.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
