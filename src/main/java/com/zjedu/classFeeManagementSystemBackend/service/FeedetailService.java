package com.zjedu.classFeeManagementSystemBackend.service;

import com.zjedu.classFeeManagementSystemBackend.common.Result;
import com.zjedu.classFeeManagementSystemBackend.model.domain.Feedetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Lay
 * @description 针对表【feedetail(班费明细表)】的数据库操作Service
 * @createDate 2023-08-08 15:09:40
 */
public interface FeedetailService extends IService<Feedetail> {

    /**
     * 添加具体班费明细
     *
     * @param feedetail
     * @return
     */
    Result<?> addFeedetail(Feedetail feedetail);

}
