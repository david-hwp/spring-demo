package com.hwp.project.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.hwp.project.common.utils.PageUtils;
import com.hwp.project.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 * @author david-hwp
 * @email wolaiwod@gmail.com
 * @date 2017-03-08 10:40:56
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
