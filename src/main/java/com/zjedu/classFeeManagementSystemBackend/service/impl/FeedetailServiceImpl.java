package com.zjedu.classFeeManagementSystemBackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.mapper.ClassfeeMapper;
import com.zjedu.classFeeManagementSystemBackend.mapper.StudentMapper;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Classfee;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Feedetail;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Student;
import com.zjedu.classFeeManagementSystemBackend.service.FeedetailService;
import com.zjedu.classFeeManagementSystemBackend.mapper.FeedetailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lay
 * @description 针对表【feedetail(班费明细表)】的数据库操作Service实现
 * @createDate 2023-08-08 15:09:40
 */
@Service
public class FeedetailServiceImpl extends ServiceImpl<FeedetailMapper, Feedetail>
        implements FeedetailService {
    @Resource
    private ClassfeeMapper classfeeMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Result<?> addFeedetail(Feedetail feedetail) {
        QueryWrapper<Classfee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("feeId", feedetail.getFeeId());
        Long count = classfeeMapper.selectCount(queryWrapper);
        if (count == 0) {
            return Result.fail("查询不到相关收支记录");
        }
        QueryWrapper<Student> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("studentId", feedetail.getStudentId());
        Long count1 = studentMapper.selectCount(queryWrapper1);
        if (count1 == 0) {
            return Result.fail("查询不到学生数据");
        }
        boolean save = this.save(feedetail);
        if (!save) {
            return Result.fail("添加失败");
        }
        return Result.success(feedetail.getDetailId(), "添加成功");
    }
}




