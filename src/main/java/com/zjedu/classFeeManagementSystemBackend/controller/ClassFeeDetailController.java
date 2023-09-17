package com.zjedu.classFeeManagementSystemBackend.controller;


import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Feedetail;
import com.zjedu.classFeeManagementSystemBackend.service.FeedetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/feedetail")
public class ClassFeeDetailController {

    @Resource
    private FeedetailService feedetailService;

    //展示具体班级的具体班费使用情况
    @GetMapping("/list")
    public Result<List<Feedetail>> getFeeDetailList() {
        List<Feedetail> list = feedetailService.list();
        return Result.success(list);
    }

    //添加班费的具体使用情况
    @PostMapping("/add")
    public Result<?> addFeeDetail(@RequestBody Feedetail feedetail) {

        Result<?> result = feedetailService.addFeedetail(feedetail);
        return Result.success(result);
    }
}
