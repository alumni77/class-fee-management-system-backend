package com.zjedu.classFeeManagementSystemBackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.mapper.GradeMapper;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Grade;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Student;
import com.zjedu.classFeeManagementSystemBackend.service.StudentService;
import com.zjedu.classFeeManagementSystemBackend.mapper.StudentMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lay
 * @description 针对表【student(学生表)】的数据库操作Service实现
 * @createDate 2023-08-08 15:09:40
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
        implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private GradeMapper gradeMapper;

    @Override
    public Result<?> addStudent(Student student) {
        if (StringUtils.isAnyBlank(student.getStudentId(), student.getClassId(), student.getSname(), student.getGender(), student.getPhone())) {
            return Result.fail("参数为空");
        }
        if (student.getStudentId().length() >= 10) {
            return Result.fail("学号Id超出最大范围");
        }
        QueryWrapper<Student> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("studentId", student.getStudentId());
        Long count1 = studentMapper.selectCount(queryWrapper1);
        if (count1 > 0) {
            return Result.fail("查询到重复学号Id");
        }
        QueryWrapper<Grade> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("classId", student.getClassId());
        Long count2 = gradeMapper.selectCount(queryWrapper2);
        if (count2 == 0) {
            return Result.fail("查询不到班级Id");
        }
        if (!student.getGender().equals("男") && !student.getGender().equals("女")) {
            return Result.fail("学生性别填写问题");
        }
        boolean save = this.save(student);
        if (!save) {
            return Result.fail("导入信息失败");
        }
        return Result.success("添加学生信息成功!");
    }
}




