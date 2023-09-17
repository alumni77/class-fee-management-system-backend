package com.zjedu.classFeeManagementSystemBackend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Classadmin;
import com.zjedu.classFeeManagementSystemBackend.service.ClassadminService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private ClassadminService classadminService;

    //管理员登录
    @PostMapping(value = "/login")
    public Result<Map<String, Object>> login(@RequestBody Classadmin classadmin) {
        Result<Map<String, Object>> data = classadminService.login(classadmin);
        if (data != null) {
            return Result.success(data.getData());
        }
        return Result.fail(20002, "用户名或密码错误");
    }

    //获取管理员的登录信息
    @GetMapping("/info")
    public Result<Map<String, Object>> getUserInfo(@RequestParam("token") String token) {
        // 根据token获取管理员信息，redis
        Result<Map<String, Object>> data = classadminService.getAdminInfo(token);
        if (data != null) {
            return Result.success(data.getData());
        }
        return Result.fail(20003, "登录信息无效，请重新登录");
    }

    //注销,退出登录
    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token) {
        classadminService.logout(token);
        return Result.success();
    }

    //展示所有管理员用户
    @GetMapping("/list")
    public Result<java.util.List<Classadmin>> getAdminList(@RequestParam(value = "username", required = false) String username,
                                                           @RequestParam(value = "password", required = false) String password
    ) {
        QueryWrapper<Classadmin> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(username)) {
            queryWrapper.eq("username", username);
        }
        if (StringUtils.hasLength(password)) {
            queryWrapper.eq("password)", password);
        }
        queryWrapper.orderByAsc("userId");

        List<Classadmin> data = classadminService.list(queryWrapper);
        return Result.success(data);
    }
    //添加管理员
    @PostMapping("/add")
    public Result<?> addAdmin(@RequestBody Classadmin classadmin) {
        Result<?> result = classadminService.addClassAdmin(classadmin);
        return result;
    }

    //更新管理员信息
    @PutMapping("/update")
    public Result<?> updateAdmin(@RequestBody Classadmin classadmin) {
        Result<?> result = classadminService.updateClassAdmin(classadmin);
        return result;

    }

    //删除管理员信息
    @DeleteMapping("/delete")
    public Result<?> deleteAdminById(@RequestBody Classadmin classadmin) {
        Result<?> result = classadminService.deleteClassAdmin(classadmin);
        return result;
    }
}
