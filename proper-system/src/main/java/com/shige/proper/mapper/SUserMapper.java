package com.shige.proper.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shige.proper.entity.system.SUser;
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
public interface SUserMapper extends BaseMapper<SUser> {
    List<SUser> findOne(@Param("id") Integer id);
    SUser findByUserName(@Param("username") String username);

    Page<SUser> listUser(Page page, @Param("ew") QueryWrapper queryWrapper);

    Page<SUser> findUserList(Page page, @Param("ew") QueryWrapper queryWrapper);

    Page<SUser> findUserCommList(Page page,@Param("compId") String compId,@Param("userName")String userName,@Param("name")String name,
                                 @Param("commId")String commId,@Param("userId")Long userId);
    /**
     * 根据组织id将删除的组织人员组织置空
     * @param id
     * @return
     */
    Integer updateUserOrg(Long id);

}
