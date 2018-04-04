package com.sinosoft.stukpisys.servsce.impl;

import com.alibaba.fastjson.JSONObject;
import com.sinosoft.stukpisys.dao.UserDao;
import com.sinosoft.stukpisys.entity.User;
import com.sinosoft.stukpisys.servsce.UserService;
import com.sinosoft.stukpisys.untils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtToken JwtToken;


    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        JSONObject res = new JSONObject();
        res.put("token",JwtToken.generateToken(userDetails));
        res.put("role",userDao.getByName(username).getRole());
        return res.toJSONString();
    }

    @Override
    public String register(User user) {
        System.out.println(user.getName());
        String username = user.getName();
        if (userDao.getByName(username) != null) {
            return "用户已存在";
        }
        //加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        user.setRole(user.getRole());
        userDao.insert(user);
        return "success";
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring("".length());
        if (!JwtToken.isTokenExpired(token)) {
            return JwtToken.refreshToken(token);
        }
        return "error";
    }

    @Override
    public String changePassword(String username, String password) {
        userDao.UpdatePassword(username,password);
        return "修改密码成功";
    }


}
