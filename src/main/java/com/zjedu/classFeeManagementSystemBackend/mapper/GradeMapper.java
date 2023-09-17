package com.zjedu.classFeeManagementSystemBackend.mapper;

import com.zjedu.classFeeManagementSystemBackend.model.domain.Grade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author Lay
 * @description 针对表【class(班级表)】的数据库操作Mapper
 * @createDate 2023-08-08 15:09:40
 * @Entity generator.domain.Class
 */
public interface GradeMapper extends BaseMapper<Grade> {

    List<Grade> searchClass(String className);
}




