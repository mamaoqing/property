package com.shige.proper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shige.proper.entity.system.SRoleUser;
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
public interface SRoleUserMapper extends BaseMapper<SRoleUser> {
    /**
     * 查询公司下的角色
     * @param userId
     * @param compId
     * @return
     */
    List<Map<String,String>> listUserRole(@Param("userId") Long userId, @Param("compId") Long compId);
}
