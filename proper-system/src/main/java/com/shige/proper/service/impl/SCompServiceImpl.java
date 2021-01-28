package com.shige.proper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.entity.system.SComp;
import com.shige.proper.entity.system.SUser;
import com.shige.proper.enums.ShigeExceptionEnum;
import com.shige.proper.exception.ShigeException;
import com.shige.proper.mapper.SCompMapper;
import com.shige.proper.service.SCompService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2021-01-27
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class SCompServiceImpl extends ServiceImpl<SCompMapper, SComp> implements SCompService {
    private final SCompMapper companyMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean save(SComp company, String token) {
        SUser user = getUserByToken(token);
        if (null == company) {
            throw new ShigeException(ShigeExceptionEnum.PARAMS_MISS_ERROR);
        }
        company.setCreateUserId(user.getId());
        company.setCreateUserName(user.getUserName());
        int insert = companyMapper.insert(company);
        if (insert > 0) {
            log.info("公司添加成功，添加人={}", user.getUserName());
            return true;
        }

        throw new ShigeException(ShigeExceptionEnum.SYSTEM_INSERT_ERROR);

    }

    @Override
    public boolean saveOrUpdate(SComp company, String token) {
        SUser user = getUserByToken(token);
        if (null == company) {
            throw new ShigeException(ShigeExceptionEnum.PARAMS_MISS_ERROR);
        }
        int insert = companyMapper.updateById(company);
        if (insert > 0) {
            log.info("{} 修改成功，修改人={}", company.getCompName(), user.getUserName());
            return true;
        }
        throw new ShigeException(ShigeExceptionEnum.SYSTEM_UPDATE_ERROR);

    }

    @Override
    public boolean removeById(Long id, String token) {
        SUser user = getUserByToken(token);
        if (StringUtils.isEmpty(id)) {
            throw new ShigeException(ShigeExceptionEnum.PARAMS_MISS_ERROR);
        }
        int delete = companyMapper.deleteById(id);
        if (delete > 0) {
            log.info("公司删除成功，删除人={}", user.getUserName());
            return true;
        }
        throw new ShigeException(ShigeExceptionEnum.SYSTEM_DELETE_ERROR);

    }

    @Override
    public Page<SComp> listCompany(Map<String, String> map, Integer pageNo, Integer size) {
        if (StringUtils.isEmpty(pageNo)) {
            throw new ShigeException(ShigeExceptionEnum.PAGE_NO_MISS_ERROR);
        }
        if (StringUtils.isEmpty(size)) {
            size = 10;
        }
        Page<SComp> page = new Page<>(pageNo, size);
        QueryWrapper<SComp> queryWrapper = new QueryWrapper<>();
        // 下面放查询条件
        // 名称查询
        queryWrapper.like(!StringUtils.isEmpty(map.get("name")), "compName", map.get("name"))
        // 省
        .eq(!StringUtils.isEmpty(map.get("provinceId")), "province_id", map.get("provinceId"))
        // 市
        .eq(!StringUtils.isEmpty(map.get("cityId")), "city_id", map.get("cityId"))
        // 区
        .eq(!StringUtils.isEmpty(map.get("districtId")), "district_id", map.get("districtId"));


        return companyMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<SComp> getListCompany(Map<String, String> map, String token) {
        SUser use = getUserByToken(token);
        Page<SComp> page = new Page<>();
        QueryWrapper<SComp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(map.get("district")), "district", map.get("district"));
        if (!(use.getCompId() == 0)) {
            queryWrapper.eq("id", use.getCompId());
        }
        return companyMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<SComp> getComp(String token) {
        SUser user = getUserByToken(token);

        QueryWrapper<SComp> queryWrapper = new QueryWrapper<>();
        log.info("当前角色为->{}", user.getType());
        if (!ShigeConstant.SUPER_ADMIN.equals(user.getType())) {
            queryWrapper.eq("id", user.getCompId());
        }
        queryWrapper.select("name", "id");
        return companyMapper.selectList(queryWrapper);
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
