package com.shige.proper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shige.proper.entity.system.SMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
public interface SMenuMapper extends BaseMapper<SMenu> {
    List<SMenu> findMenuList(@Param("compId")Long name);

}
