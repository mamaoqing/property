package com.shige.proper.resource.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shige.proper.resource.entity.RComm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mq
 * @since 2021-02-03
 */
public interface RCommService extends IService<RComm> {


    Page<RComm> listComm(Map<String,String> map ,String token);

    boolean save(RComm comm ,String token);
    boolean update(RComm comm ,String token);
    boolean delete(Long id  ,String token);


}
