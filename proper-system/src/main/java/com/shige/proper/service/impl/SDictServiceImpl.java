package com.shige.proper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shige.proper.entity.system.SDict;
import com.shige.proper.entity.system.SUser;
import com.shige.proper.enums.ShigeExceptionEnum;
import com.shige.proper.exception.ShigeException;
import com.shige.proper.mapper.SDictMapper;
import com.shige.proper.service.SDictService;
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
 * 服务实现类
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class SDictServiceImpl extends ServiceImpl<SDictMapper, SDict> implements SDictService {

    private final SDictMapper dictMapper;

    @Override
    public Page<SDict> listDict(Map<String,String> map) {
        QueryWrapper<SDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("parentId");
        Page<SDict> page = new Page<>();
        Page<SDict> parents = dictMapper.selectPage(page,queryWrapper);
        List<SDict> records = parents.getRecords();
        List<SDict> dicts = dictMapper.selectList(null);
        for (int i = 0; i < records.size(); i++) {
            List<SDict> list = new ArrayList<>();
            SDict sDict = records.get(i);
            for (int j = 0, count = dicts.size(); j < count; j++) {
                if (sDict.getId().equals(dicts.get(j).getParentId())) {
                    list.add(dicts.get(j));
                }
            }
            records.get(i).setChildList(list);
        }
        parents.setRecords(records);
        return parents;
    }

    @Override
    public boolean save(SDict dict, String token) {
        int insert = dictMapper.insert(dict);
        if (insert > 0) {
            return true;
        }
        throw new ShigeException(ShigeExceptionEnum.SYSTEM_INSERT_ERROR);
    }

    @Override
    public boolean update(SDict dict, String token) {
        int insert = dictMapper.updateById(dict);
        if (insert > 0) {
            return true;
        }
        throw new ShigeException(ShigeExceptionEnum.SYSTEM_UPDATE_ERROR);
    }

    @Override
    public boolean delete(Long id, String token) {
        int insert = dictMapper.deleteById(id);
        if (insert > 0) {
            return true;
        }
        throw new ShigeException(ShigeExceptionEnum.SYSTEM_DELETE_ERROR);
    }

    @Override
    public List<SDict> childListDict(Long id) {
        QueryWrapper<SDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId",id);

        return dictMapper.selectList(queryWrapper);
    }

}
