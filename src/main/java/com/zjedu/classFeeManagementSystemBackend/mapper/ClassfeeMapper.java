package com.zjedu.classFeeManagementSystemBackend.mapper;

import com.zjedu.classFeeManagementSystemBackend.model.domain.Classfee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author Lay
* @description 针对表【classfee(班费表)】的数据库操作Mapper
* @createDate 2023-08-08 15:09:40
* @Entity generator.domain.Classfee
*/
@Mapper
public interface ClassfeeMapper extends BaseMapper<Classfee> {


    Double findLatestRemainingAmount(@Param("classId") String classId);
}




