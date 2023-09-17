package com.zjedu.classFeeManagementSystemBackend.service;

import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjedu.classFeeManagementSystemBackend.service.impl.StudentServiceImpl;

/**
 * @author Lay
 * @description 针对表【student(学生表)】的数据库操作Service
 * @createDate 2023-08-08 15:09:40
 */
public interface StudentService extends IService<Student> {

    /**
     * 添加学生信息
     *
     * @param student
     * @return
     */
    Result<?> addStudent(Student student);
}
