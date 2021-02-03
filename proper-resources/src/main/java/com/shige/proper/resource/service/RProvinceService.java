package com.shige.proper.resource.service;

import com.shige.proper.resource.entity.RProvince;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 省 服务类
 * </p>
 *
 * @author mq
 * @since 2021-02-03
 */
public interface RProvinceService extends IService<RProvince> {

    List<RProvince> listProvince();

}
