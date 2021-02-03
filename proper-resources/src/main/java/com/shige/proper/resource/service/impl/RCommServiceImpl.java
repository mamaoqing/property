package com.shige.proper.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.entity.system.SUser;
import com.shige.proper.enums.ShigeExceptionEnum;
import com.shige.proper.exception.ShigeException;
import com.shige.proper.resource.entity.RComm;
import com.shige.proper.resource.mapper.RCommMapper;
import com.shige.proper.resource.service.RCommService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mq
 * @since 2021-02-03
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class RCommServiceImpl extends ServiceImpl<RCommMapper, RComm> implements RCommService {

    private final RCommMapper commMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    public Page<RComm> listComm(Map<String, String> map, String token) {
        SUser user = getUserByToken(token);
        if (StringUtils.isEmpty(map.get("pageNo"))) {
            throw new ShigeException(ShigeExceptionEnum.PAGE_NO_MISS_ERROR);
        }
        int pageNo = Integer.parseInt(map.get("pageNo"));
        int size = StringUtils.isEmpty(map.get("size")) ? 10 : Integer.parseInt(map.get("size"));
        Page<RComm> page = new Page<RComm>(pageNo, size);
        QueryWrapper<RComm> queryWrapper = new QueryWrapper<RComm>();
        if(!ShigeConstant.SUPER_ADMIN.equals(user.getType())){
            queryWrapper.eq("comp_id",user.getCompId());
        }
        queryWrapper.isNotNull("id").eq(!StringUtils.isEmpty(map.get("name")),"comm_name",map.get("name"));

        return commMapper.listComm(page, queryWrapper);
    }

    public boolean save(RComm comm, String token) {
        SUser user = getUserByToken(token);
        comm.setCreateUserId(user.getId());
        comm.setCreateUserName(user.getUserName());
        int insert = commMapper.insert(comm);
        if (insert > 0) {
            return true;
        }
        throw new ShigeException(ShigeExceptionEnum.SYSTEM_INSERT_ERROR);
    }

    public boolean update(RComm comm, String token) {

        int i = commMapper.updateById(comm);
        if (i > 0) {
            return true;
        }
        throw new ShigeException(ShigeExceptionEnum.SYSTEM_UPDATE_ERROR);
    }

    public boolean delete(Long id, String token) {

        int i = commMapper.deleteById(id);
        if (i > 0) {
            return true;
        }
        throw new ShigeException(ShigeExceptionEnum.SYSTEM_DELETE_ERROR);
    }

    private SUser getUserByToken(String token) {
        Object o = redisTemplate.opsForValue().get(token);
        if (null == o) {
            log.error("登录失效，请重新登录。");
            throw new ShigeException(ShigeExceptionEnum.LOGIN_TIME_OUT);
        }
        return (SUser) o;
    }
}
