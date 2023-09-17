package com.zjedu.classFeeManagementSystemBackend.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 班级表
 * @TableName class
 */
@TableName(value ="class")
@Data
public class Grade implements Serializable {
    /**
     * 班级Id
     */
    @TableId
    private String classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 学院名称
     */
    private String academy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}