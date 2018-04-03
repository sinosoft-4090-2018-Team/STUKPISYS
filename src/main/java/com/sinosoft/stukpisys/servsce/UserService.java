package com.sinosoft.stukpisys.servsce;

import com.sinosoft.stukpisys.entity.User;
import org.springframework.security.access.method.P;

public interface UserService {
    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     */
    String login(String username, String password);

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 操作结果
     */
    String register(User user);

    /**
     * 刷新密钥
     *
     * @param oldToken 原密钥
     * @return 新密钥
     */
    String refreshToken(String oldToken);
    /**
     * 用户修改密码
     *
     * @param username 用户名
     * @para
     * @return 操作结果
     */
    String changePassword(String username, String password);
}
