package com.shige.proper.resource.mapper;

import com.shige.proper.resource.entity.RProvince;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 省 Mapper 接口
 * </p>
 *
 * @author mq
 * @since 2021-02-03
 */
public interface RProvinceMapper extends BaseMapper<RProvince> {


    List<RProvince> listProvince();

}
