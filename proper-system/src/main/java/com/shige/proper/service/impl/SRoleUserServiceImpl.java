package com.shige.proper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shige.proper.entity.system.SRoleUser;
import com.shige.proper.entity.system.SUser;
import com.shige.proper.enums.ShigeExceptionEnum;
import com.shige.proper.exception.ShigeException;
import com.shige.proper.mapper.SRoleUserMapper;
import com.shige.proper.service.SRoleUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class SRoleUserServiceImpl extends ServiceImpl<SRoleUserMapper, SRoleUser> implements SRoleUserService {
    private final SRoleUserMapper userRoleMapper;

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Long> listUserRole(String token) {
        List<Long> list = new ArrayList<>();

        if(StringUtils.isEmpty(token)){
            throw new ShigeException(ShigeExceptionEnum.PARAMS_MISS_ERROR);
        }

        // 1. 通过token查询用户id；
        Object o = redisTemplate.opsForValue().get(token);
        if(null == o){
            return list;
        }
        Long userId = ((SUser) o).getId();

        // 2. 通过用户id查询user_role.
        QueryWrapper<SRoleUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        List<SRoleUser> sUserRoles = userRoleMapper.selectList(queryWrapper);

        for (int i = 0; i < sUserRoles.size(); i++) {
            list.add(sUserRoles.get(i).getRoleId());
        }

        return list;
    }

    @Override
    public List<Map<String, String>> listUserRole(Long userId, Long compId) {
        if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(compId)){
            throw new ShigeException(ShigeExceptionEnum.PARAMS_MISS_ERROR);
        }
        return userRoleMapper.listUserRole(userId, compId);
    }


}
