package com.shige.proper.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class SOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 公司id
     */
    private Long compId;

    /**
     * 父机构id
     */
    private Long parentId;

    /**
     * 父机构id列表
     */
    private String parentIdList;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 创建时间
     */
    @TableField(value = "createTime", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8",shape = JsonFormat.Shape.STRING)
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

    @TableField(exist = false)
    private List<SOrg> childList;

}
