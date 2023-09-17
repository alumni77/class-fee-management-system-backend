package com.zjedu.classFeeManagementSystemBackend.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 班费明细表
 * @TableName feedetail
 */
@TableName(value ="feedetail")
@Data
public class Feedetail implements Serializable {
    /**
     * 明细Id
     */
    @TableId(type = IdType.AUTO)
    private Integer detailId;

    /**
     * 班费编号
     */
    private Integer feeId;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 支出/收入金额 +代表收入 -代表支出
     */
    private Double amount;

    /**
     * 支出/收入时间
     */
    private Date tranTime;

    /**
     * 使用说明
     */
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}