package com.zjedu.classFeeManagementSystemBackend.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.mapper.ClassadminMapper;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Classadmin;
import com.zjedu.classFeeManagementSystemBackend.service.ClassadminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lay
 * @description 针对表【classadmin(班级管理员用户表)】的数据库操作Service实现
 * @createDate 2023-08-08 15:09:40
 */
@Service
@Slf4j
public class ClassadminServiceImpl extends ServiceImpl<ClassadminMapper, Classadmin>
        implements ClassadminService {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private ClassadminMapper classadminMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    //管理员用户的添加
    @Override
    public Result<?> addClassAdmin(Classadmin classadmin) {
        //校验 用户名或用户密码不能为空
        if (StringUtils.isAnyBlank(classadmin.getUsername(), classadmin.getPassword())) {
            return Result.fail("用户名或用户密码不能为空");
        }

        //用户名不能存在特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(classadmin.getUsername());
        if (matcher.find()) {
            return Result.fail("用户名存在特殊字符");
        }

        //不能相同用户名
        QueryWrapper<Classadmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", classadmin.getUsername());
        Long count = classadminMapper.selectCount(queryWrapper);
        if (count > 0) {
            return Result.fail("查询到了相同用户名");
        }

        //对用户的密码添加进行加密
        final String SALT = "ZJ"; //盐值
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + classadmin.getPassword()).getBytes());

        Classadmin aClassadmin = new Classadmin();
        aClassadmin.setUsername(classadmin.getUsername());
        aClassadmin.setPassword(encryptPassword);
        boolean save = this.save(aClassadmin);
        if (!save) {
            return Result.fail("添加失败");
        }
        return Result.success(aClassadmin.getUserId(), "添加成功");
    }

    //更新管理员信息
    @Override
    public Result<?> updateClassAdmin(Classadmin classadmin) {
        Classadmin aClassadmin = new Classadmin();
        //
        if (StringUtils.isAnyBlank(classadmin.getUsername())) {
            //用户名为空,只修改密码
            //对用户所更新的密码进行加密
            final String SALT = "ZJ";
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + classadmin.getPassword()).getBytes());
            aClassadmin.setUserId(classadmin.getUserId());
            aClassadmin.setUsername(classadmin.getUsername());
            aClassadmin.setPassword(encryptPassword);
            int i = classadminMapper.updateById(aClassadmin);
            if (i == 0) {
                return Result.fail("更新失败");
            }
            return Result.success(aClassadmin.getUserId(), "更新成功");
        } else {
            //用户名不为空,存在同时修改用户名或密码的可能

            //用户名不能存在特殊字符
            String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
            Matcher matcher = Pattern.compile(validPattern).matcher(classadmin.getUsername());
            if (matcher.find()) {
                return Result.fail("用户名存在特殊字符");
            }
            //用户名不能相同
            QueryWrapper<Classadmin> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userName", classadmin.getUsername());
            Long count = classadminMapper.selectCount(queryWrapper);
            if (count > 0) {
                return Result.fail("查询到了相同用户名");
            }

            if (!StringUtils.isAnyBlank(classadmin.getPassword())) {

                final String SALT = "ZJ";
                String encryptPassword = DigestUtils.md5DigestAsHex((SALT + classadmin.getPassword()).getBytes());
                aClassadmin.setPassword(encryptPassword);
            } else {
                aClassadmin.setPassword(null);
            }
            aClassadmin.setUserId(classadmin.getUserId());
            aClassadmin.setUsername(classadmin.getUsername());
            int i = classadminMapper.updateById(aClassadmin);
            if (i == 0) {
                return Result.fail("更新失败");
            }
            return Result.success(aClassadmin.getUserId(), "更新成功");
        }
    }

    //删除管理员用户信息
    @Override
    public Result<?> deleteClassAdmin(Classadmin classadmin) {
        QueryWrapper<Classadmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", classadmin.getUserId());
        Long count = classadminMapper.selectCount(queryWrapper);
        if (count == 0) {
            return Result.fail("查询不到该用户Id");
        }
        boolean remove = this.removeById(classadmin.getUserId());
        if (!remove) {
            return Result.fail("删除失败");
        }
        return Result.success(classadmin.getUserId(), "删除成功");
    }

    //登录功能
    @Override
    public Result<Map<String, Object>> login(Classadmin classadmin) {
        //对前端用户输入的密码进行加密, 与数据库信息进行验证匹配
        final String SALT = "ZJ";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + classadmin.getPassword()).getBytes());
        classadmin.setPassword(encryptPassword);
        // 根据用户名查询
        QueryWrapper<Classadmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", classadmin.getUsername());
        queryWrapper.eq("password", encryptPassword);
        Classadmin loginAdmin = classadminMapper.selectOne(queryWrapper);
        System.out.println("loginAdmin " + loginAdmin);
        System.out.println("classadmin " + classadmin);
        if (loginAdmin != null && passwordEncoder.matches(classadmin.getPassword(), passwordEncoder.encode(loginAdmin.getPassword()))) {
            String key = "admin:" + UUID.randomUUID();

            loginAdmin.setPassword(null);
            redisTemplate.opsForValue().set(key, loginAdmin, 30, TimeUnit.MINUTES);
            Map<String, Object> data = new HashMap<>();
            data.put("token", key);
            return Result.success(data);
        }
        return Result.success("登录成功");

    }

    //获取管理员用户的登录信息字段
    @Override
    public Result<Map<String, Object>> getAdminInfo(String token) {
        // 根据token获取用户信息，redis
        Object obj = redisTemplate.opsForValue().get(token);
        if (obj != null) {
            Classadmin classadmin = JSON.parseObject(JSON.toJSONString(obj), Classadmin.class);
            Map<String, Object> data = new HashMap<>();
            data.put("name", classadmin.getUsername());
            return Result.success(data);
        }
        return null;
    }

    //退出 注销功能
    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }
}




