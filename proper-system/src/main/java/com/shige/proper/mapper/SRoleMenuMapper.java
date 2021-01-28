package com.shige.proper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shige.proper.entity.system.SMenu;
import com.shige.proper.entity.system.SRoleMenu;
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
public interface SRoleMenuMapper extends BaseMapper<SRoleMenu> {
    List<SMenu> listMenu(@Param("id")Long userId);
    List<SMenu> listMenuAll();
}
