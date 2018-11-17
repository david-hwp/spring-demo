package com.hwp.project.service;

import com.baomidou.mybatisplus.service.IService;
import com.hwp.project.entity.TokenEntity;

/**
 * 用户Token
 *
 * @author david-hwp
 * @email wolaiwod@gmail.com
 * @date 2017-03-23 15:22:07
 */
public interface TokenService extends IService<TokenEntity> {

    TokenEntity queryByToken(String token);

    /**
     * 生成token
     *
     * @param userId 用户ID
     * @return 返回token信息
     */
    TokenEntity createToken(long userId);

    /**
     * 设置token过期
     *
     * @param userId 用户ID
     */
    void expireToken(long userId);

}
