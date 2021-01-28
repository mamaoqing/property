package com.shige.proper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shige.proper.entity.system.SComp;
import com.shige.proper.entity.system.SOrg;

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
public interface SOrgService extends IService<SOrg> {
    /**
     * 自动添加组织机构
     * @param company
     * @return
     */
    boolean autoSave(SComp company);

    List<SOrg> listOrg(String token);

    boolean save(SOrg org,String token);
    boolean saveOrUpdate(SOrg org,String token);
    boolean removeById(Long id,String token);

    List<SOrg> getBaseOrg(Long compId);

    List<Map<String, String>> getOnlyChildOrg(Long id);

}
