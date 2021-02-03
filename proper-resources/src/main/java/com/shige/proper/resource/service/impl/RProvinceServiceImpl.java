package com.shige.proper.resource.service.impl;

import com.shige.proper.resource.entity.RProvince;
import com.shige.proper.resource.mapper.RProvinceMapper;
import com.shige.proper.resource.service.RProvinceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 省 服务实现类
 * </p>
 *
 * @author mq
 * @since 2021-02-03
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class RProvinceServiceImpl extends ServiceImpl<RProvinceMapper, RProvince> implements RProvinceService {

    private final RProvinceMapper provinceMapper;

    public List<RProvince> listProvince() {
        return provinceMapper.listProvince();
    }
}
