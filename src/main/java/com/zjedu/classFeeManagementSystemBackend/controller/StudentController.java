package com.zjedu.classFeeManagementSystemBackend.controller;


import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Student;
import com.zjedu.classFeeManagementSystemBackend.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    //添加学生信息
    @PostMapping("/add")
    public Result<?> addStudent(@RequestBody Student student) {
        Result<?> result = studentService.addStudent(student);
        return Result.success(result);
    }

    //展示所有学生的具体信息
    @GetMapping("/list")
    public Result<List<Student>> getStudentList() {
        List<Student> list = studentService.list();
        return Result.success(list);
    }
}
