package com.zfq.service;

import com.zfq.common.Result;
import com.zfq.model.dto.UserDTO;
import com.zfq.model.po.User;

import javax.servlet.http.HttpSession;

/**
 * @ClassName IUserService
 * @Author zfq
 * @Date 2025/12/7
 */
public interface IUserService {
    Result add(User user);

    Result login(User user, HttpSession session);

    Result loginEmail(User user);

    Result resertPasswd(UserDTO user);

    Result sendCode(String email);
}
