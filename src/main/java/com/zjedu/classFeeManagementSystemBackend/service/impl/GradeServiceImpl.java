package com.zjedu.classFeeManagementSystemBackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.mapper.StudentMapper;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Grade;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Student;
import com.zjedu.classFeeManagementSystemBackend.service.GradeService;
import com.zjedu.classFeeManagementSystemBackend.mapper.GradeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lay
 * @description 针对表【class(班级表)】的数据库操作Service实现
 * @createDate 2023-08-08 15:09:40
 */
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade>
        implements GradeService {

    @Resource
    private GradeMapper gradeMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Result<?> addGrade(Grade grade) {
        if (StringUtils.isAnyBlank(grade.getClassId(), grade.getClassName(), grade.getAcademy())) {
            return Result.fail("班级Id或班级名称或学院不能为空");
        }

        if (!StringUtils.isNumeric(grade.getClassId())) {
            return Result.fail("班级Id需为正整数");
        }

        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("classId", grade.getClassId());
        Long count = gradeMapper.selectCount(queryWrapper);
        if (count > 0) {
            return Result.fail("班级Id重复了");
        }

        Grade aGrade = new Grade();
        aGrade.setClassId(grade.getClassId());
        aGrade.setClassName(grade.getClassName());
        aGrade.setAcademy(grade.getAcademy());
        boolean saveResult = this.save(aGrade);
        if (!saveResult) {
            return Result.fail("保存失败");
        }
        return Result.success(aGrade.getClassId() + "保存成功");
    }

    @Override
    public Result<?> updateClass(Grade grade) {

        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("classId", grade.getClassId());
        Long count = gradeMapper.selectCount(queryWrapper);
        if (count == 0) {
            return Result.fail("查询不到该班级");
        }
        boolean update = this.update(grade, queryWrapper);

        if (!update) {
            return Result.fail("更新失败");
        }

        return Result.success(grade.getClassId(), "更新成功");
    }

    @Override
    public Result<?> deleteClass(Grade grade) {
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("classId", grade.getClassId());
        Long count = gradeMapper.selectCount(queryWrapper);
        if (count == 0) {
            return Result.fail("查询不到该班级");
        }
        QueryWrapper<Student> query = new QueryWrapper<>();
        query.eq("classId", grade.getClassId());
        Long count1 = studentMapper.selectCount(query);
        if(count1!=0){
            return Result.fail("删除失败, 改班级表存在学生名单");
        }
        boolean remove = this.removeById(grade.getClassId());
        if (!remove) {
            return Result.fail("删除失败");
        }
        return Result.success("删除成功");
    }

    @Override
    public Result<?> searchClass(String className) {
        List<Grade> grades = gradeMapper.searchClass(className);
        return Result.success(grades, "搜索成功");
    }
}




