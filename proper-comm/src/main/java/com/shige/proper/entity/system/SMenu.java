package com.shige.proper.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单id
     */
    @TableField("parentId")
    private Long parentId;

    /**
     * 父菜单id列表
     */
    @TableField("parentIdList")
    private String parentIdList;

    /**
     * url
     */
    private String url;

    /**
     * 排序
     */
    @TableField("orderNo")
    private Integer orderNo;

    /**
     * 类型
     */
    private String type;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 创建时间
     */
    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人id
     */
    @TableField("createUserId")
    private Long createUserId;

    /**
     * 创建人姓名
     */
    @TableField("createUserName")
    private String createUserName;

    /**
     * 子菜单集合
     */
    @TableField(exist = false)
    private List<SMenu> chirldMenuList;
}
