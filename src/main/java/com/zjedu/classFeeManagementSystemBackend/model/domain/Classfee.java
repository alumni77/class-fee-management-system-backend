package com.zjedu.classFeeManagementSystemBackend.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 班费表
 * @TableName classfee
 */
@TableName(value ="classfee")
@Data
public class Classfee implements Serializable {
    /**
     * 班费编号
     */
    @TableId(type = IdType.AUTO)
    private Integer feeId;

    /**
     * 班级Id
     */
    private String classId;

    /**
     * 上交日期
     */
    private Date subDate;

    /**
     * 上交人数
     */
    private Integer subCount;

    /**
     * 上交金额
     */
    private Double subAmount;

    /**
     * 剩余金额
     */
    private Double remainAmount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}