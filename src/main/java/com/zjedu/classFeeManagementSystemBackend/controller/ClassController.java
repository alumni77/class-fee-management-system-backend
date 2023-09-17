package com.zjedu.classFeeManagementSystemBackend.controller;

import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Grade;
import com.zjedu.classFeeManagementSystemBackend.service.GradeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Resource
    private GradeService gradeService;

    //添加班级信息
    @PostMapping("/add")
    public Result<?> addClass(@RequestBody Grade grade) {
        Result<?> result = gradeService.addGrade(grade);
        return Result.success(result);
    }

    @PutMapping("/update")
    public Result<?> updateClass(@RequestBody Grade grade) {
        Result<?> result = gradeService.updateClass(grade);
        return Result.success(result);
    }

    //更新班级信息
    @DeleteMapping("/delete")
    public Result<?> deleteClass(@RequestBody Grade grade) {

        Result<?> result = gradeService.deleteClass(grade);
        return Result.success(result);
    }

    //展示所有班级信息
    @GetMapping("/list")
    public Result<List<Grade>> getClassList() {
        List<Grade> list = gradeService.list();
        return Result.success(list);
    }

    //模糊查询班级信息
    @GetMapping("/search")
    public Result<?> searchClassList(@RequestParam(value = "className", required = false) String className) {
        if (StringUtils.isAnyBlank(className)) {
            List<Grade> list = gradeService.list();
            return Result.success(list);
        }
        Result<?> result = gradeService.searchClass("%" + className + "%");
        return Result.success(result);
    }
}
