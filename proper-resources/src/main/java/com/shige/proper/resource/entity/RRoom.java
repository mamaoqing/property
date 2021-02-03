package com.shige.proper.resource.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
public class RRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long unitId;

    /**
     * 房屋编号
     */
    private String roomNo;

    /**
     * 楼层
     */
    private Integer floor;

    /**
     * 三室两厅，两室两厅
     */
    private String roomType;

    /**
     * 产权
     */
    private String propertyRight;

    /**
     * 朝向
     */
    private String direction;

    /**
     * 装修情况
     */
    private String renovationLevel;

    /**
     * 建筑面积
     */
    private BigDecimal buildingArea;

    /**
     * 使用面积
     */
    private BigDecimal usableArea;

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


}
