package com.zjedu.classFeeManagementSystemBackend.service;

import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Classadmin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author Lay
 * @description 针对表【classadmin(班级管理员用户表)】的数据库操作Service
 * @createDate 2023-08-08 15:09:40
 */
public interface ClassadminService extends IService<Classadmin> {

    /**
     * 添加管理员信息
     *
     * @param classadmin
     * @return
     */
    Result<?> addClassAdmin(Classadmin classadmin);

    /**
     * 更新管理员信息
     *
     * @param classadmin
     * @return
     */
    Result<?> updateClassAdmin(Classadmin classadmin);

    /**
     * 删除管理员信息
     *
     * @param classadmin
     * @return
     */
    Result<?> deleteClassAdmin(Classadmin classadmin);

    /**
     * 管理员验证登录
     *
     * @param classadmin
     * @return
     */
    Result<Map<String, Object>> login(Classadmin classadmin);

    /**
     * 获取管理员信息缓存
     *
     * @param token
     * @return
     */
    Result<Map<String, Object>> getAdminInfo(String token);

    /**
     * 注销
     *
     * @param token
     */
    void logout(String token);
}
