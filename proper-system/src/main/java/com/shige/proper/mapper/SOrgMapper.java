package com.shige.proper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shige.proper.entity.system.SOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
public interface SOrgMapper extends BaseMapper<SOrg> {
    List<Map<String,String>> getOnlyChildOrg(@Param("id") Long id);

}
