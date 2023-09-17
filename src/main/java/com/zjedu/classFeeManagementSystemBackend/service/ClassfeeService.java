package com.zjedu.classFeeManagementSystemBackend.service;

import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Classfee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Feedetail;

/**
 * @author Lay
 * @description 针对表【classfee(班费表)】的数据库操作Service
 * @createDate 2023-08-08 15:09:40
 */
public interface ClassfeeService extends IService<Classfee> {

    /**
     * 添加班费信息
     * @param classfee
     * @return
     */
    Result<?> addClassFee(Classfee classfee);

}
