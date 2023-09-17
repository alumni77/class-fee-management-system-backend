package com.zjedu.classFeeManagementSystemBackend.service;

import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Grade;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Lay
 * @description 针对表【class(班级表)】的数据库操作Service
 * @createDate 2023-08-08 15:09:40
 */
public interface GradeService extends IService<Grade> {

    /**
     * 增加班级信息
     *
     * @param grade 班级类
     * @return
     */
    Result<?> addGrade(Grade grade);

    /**
     * 更新班级信息
     *
     * @param grade      班级类
     * @param oldClassId 原班级Id
     * @return
     */
    Result<?>  updateClass(Grade grade);

    /**
     * 删除班级信息
     *
     * @param grade 班级类
     * @return
     */
    Result<?>  deleteClass(Grade grade);

    Result<?> searchClass(String className);
}
