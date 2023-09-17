package com.zjedu.classFeeManagementSystemBackend.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 班级管理员用户表
 * @TableName classadmin
 */
@TableName(value ="classadmin")
@Data
public class Classadmin implements Serializable {
    /**
     * 用户Id
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
