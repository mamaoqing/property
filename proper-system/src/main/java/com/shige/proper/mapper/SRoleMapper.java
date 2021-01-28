package com.shige.proper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shige.proper.entity.system.SRole;
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
public interface SRoleMapper extends BaseMapper<SRole> {
    List<SRole> findRoleList(@Param("name")String name, @Param("type")String type, @Param("compId")List compId , @Param("compid")String compid ,
                             @Param("pageNo")Integer pageNo, @Param("size")Integer size);
    Integer findRoleListNum(@Param("name")String name, @Param("type")String type, @Param("compId")List compId ,@Param("compid")String compid ,
                            @Param("pageNo")Integer pageNo, @Param("size")Integer size);

}
