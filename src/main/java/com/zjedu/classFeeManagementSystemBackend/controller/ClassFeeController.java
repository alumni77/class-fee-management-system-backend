package com.zjedu.classFeeManagementSystemBackend.controller;


import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Classfee;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Student;
import com.zjedu.classFeeManagementSystemBackend.service.ClassfeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/classfee")
public class ClassFeeController {

    @Resource
    private ClassfeeService classfeeService;

    //添加班费信息
    @PostMapping("/add")
    public Result<?> addClassFee(@RequestBody Classfee classfee) {
        Result<?> result = classfeeService.addClassFee(classfee);
        return Result.success(result);

    }

    //展示所有班级的班费信息
    @GetMapping("/list")
    public Result<List<Classfee>> getStudentList() {
        List<Classfee> list = classfeeService.list();
        return Result.success(list);
    }
}
