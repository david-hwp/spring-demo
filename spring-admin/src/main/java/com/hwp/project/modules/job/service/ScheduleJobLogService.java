package com.hwp.project.modules.job.service;

import com.baomidou.mybatisplus.service.IService;
import com.hwp.project.common.utils.PageUtils;
import com.hwp.project.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Mark wolaiwod@gmail.com
 * @since 1.2.0 2016-11-28
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
