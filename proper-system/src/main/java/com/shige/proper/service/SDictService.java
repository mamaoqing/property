package com.shige.proper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shige.proper.entity.system.SDict;

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
public interface SDictService extends IService<SDict> {

    Page<SDict> listDict(Map<String,String> map);

    boolean save(SDict dict,String token);
    boolean update(SDict dict,String token);
    boolean delete(Long id,String token);

    List<SDict> childListDict(Long id);


}
