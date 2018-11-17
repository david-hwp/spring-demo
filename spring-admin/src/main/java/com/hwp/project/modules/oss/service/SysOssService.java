package com.hwp.project.modules.oss.service;

import com.baomidou.mybatisplus.service.IService;
import com.hwp.project.common.utils.PageUtils;
import com.hwp.project.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 *
 * @author david-hwp
 * @email wolaiwod@gmail.com
 * @date 2017-03-25 12:13:26
 */
public interface SysOssService extends IService<SysOssEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
