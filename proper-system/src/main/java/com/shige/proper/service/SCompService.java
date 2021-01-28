package com.shige.proper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shige.proper.entity.system.SComp;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
public interface SCompService extends IService<SComp> {

    boolean save(SComp company,String token);

    boolean saveOrUpdate(SComp company,String token);

    boolean removeById(Long id ,String token);

    Page<SComp> listCompany(Map<String,String> map, Integer pageNo, Integer size);

    Page<SComp> getListCompany(Map<String,String> map, String token);

    List<SComp> getComp(String token);

}
