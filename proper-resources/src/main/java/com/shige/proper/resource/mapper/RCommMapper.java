package com.shige.proper.resource.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shige.proper.resource.entity.RComm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mq
 * @since 2021-02-03
 */
public interface RCommMapper extends BaseMapper<RComm> {

    Page<RComm> listComm(Page page, @Param("ew") QueryWrapper queryWrapper);
}
