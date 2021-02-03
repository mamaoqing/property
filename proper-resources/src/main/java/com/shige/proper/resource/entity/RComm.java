package com.shige.proper.resource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mq
 * @since 2021-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RComm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long compId;

    /**
     * 社区名称
     */
    private String commName;

    /**
     * 社区地址
     */
    private String commAddress;

    /**
     * 省
     */
    private Long provinceId;

    /**
     * 城市
     */
    private Long cityId;

    /**
     * 区
     */
    private Long districtId;

    private String province;

    private String city;

    private String district;

    /**
     * 状态1,在用0，不再用
     */
    private Integer state;

    /**
     * 维度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

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

    @TableField(exist = false)
    private List<RArea> childList;

    public void setChildList(List<RArea> childList) {
        this.childList = childList;
    }
}
