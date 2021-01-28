package com.shige.proper.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author mq
 * @description: TODO
 * @title: MybatisPlusConfig
 * @projectName his
 * @date 2020/12/79:39
 */
@Configuration
@Slf4j
@MapperScan("com.shige.proper.mapper")
public class MybatisPlusConfig implements MetaObjectHandler {
    static {
        log.info("MybatisPlusConfig is start ...");
    }

    /**
     * 分页插件
     * @return 返回值
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    // 在数据库信息创建的时候自动添加创建时间和更新时间
    @Override
    public void insertFill(MetaObject metaObject) {
        if(metaObject.hasSetter("createTime")){
            this.setFieldValByName("createTime",new Date(),metaObject);
        }
    }

    // 在数据库信息更新的时候，自动更改更新时间
    @Override
    public void updateFill(MetaObject metaObject) {
        if(metaObject.hasSetter("modifiedAt")){
            this.setFieldValByName("modifiedAt",new Date(),metaObject);
        }
    }

}
