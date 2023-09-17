package com.zjedu.classFeeManagementSystemBackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.mapper.GradeMapper;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Classfee;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Grade;
import com.zjedu.classFeeManagementSystemBackend.service.ClassfeeService;
import com.zjedu.classFeeManagementSystemBackend.mapper.ClassfeeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lay
 * @description 针对表【classfee(班费表)】的数据库操作Service实现
 * @createDate 2023-08-08 15:09:40
 */
@Service
public class ClassfeeServiceImpl extends ServiceImpl<ClassfeeMapper, Classfee>
        implements ClassfeeService {

    @Resource
    private ClassfeeMapper classfeeMapper;

    @Resource
    private GradeMapper gradeMapper;

    //添加每个班级的班费查询情况
    @Override
    public Result<?> addClassFee(Classfee classfee) {
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("classId", classfee.getClassId());
        Long count = gradeMapper.selectCount(queryWrapper);
        if (count == 0) {
            return Result.fail("无该班级Id");
        }
        QueryWrapper<Classfee> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("classId", classfee.getClassId());
        Long count1 = classfeeMapper.selectCount(queryWrapper1);
        if (count1 == 0) {
            System.out.println("无该班级支出记录,创建新的记录数据");
            if (classfee.getRemainAmount() == null) {
                classfee.setRemainAmount(classfee.getSubAmount());
            }
            boolean save = this.save(classfee);
            if (!save) {
                return Result.fail("保存失败");
            }
            return Result.success(classfee.getRemainAmount(), "新班级记录的剩余金额");
        } else {
            Classfee classfee1 = new Classfee();
            classfee1.setClassId(classfee.getClassId());
            classfee1.setSubDate(classfee.getSubDate());
            classfee1.setSubCount(classfee.getSubCount());
            classfee1.setSubAmount(classfee.getSubAmount());


            Double latestRemainingAmount = classfeeMapper.findLatestRemainingAmount(classfee.getClassId());
            if (classfee.getSubAmount() >= 0) {
                classfee1.setRemainAmount(classfee.getSubAmount() + latestRemainingAmount);
            } else {
                classfee1.setRemainAmount(latestRemainingAmount - Math.abs(classfee.getSubAmount()));
            }

            boolean save = this.save(classfee1);
            if (!save) {
                return Result.fail("添加失败");
            }
            return Result.success(classfee1.getRemainAmount(), "添加成功");
        }
    }

}



