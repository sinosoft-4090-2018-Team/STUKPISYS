package com.sinosoft.stukpisys.servsce.impl;

import com.sinosoft.stukpisys.dao.UserDao;
import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.User;
import com.sinosoft.stukpisys.entity.UserInfo;
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

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtToken jwtToken;


    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtToken.generateToken(userDetails);
    }

    @Override
    public String register(User user) {
        System.out.println(user.getName());
        String username = user.getName();
        if (userDao.getByName(username) != null) {
            return "用户已存在";
        }
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
        if (!jwtToken.isTokenExpired(token)) {
            return jwtToken.refreshToken(token);
        }
        return "error";
    }

    public List<UserInfo> getAllInfo() {
        return userDao.getAllInfo();
    }

    public List<Education> getEduInfo() {
        return userDao.getEduInfo();
    }

    @Override
    public UserInfo getPersonInfoByName(String name) {
        return userDao.getPersonInfoByName(name);
    }

    @Override
    public List<UserInfo> getInfoByState(String state) {
        return userDao.getInfoByState(state);
    }

    @Override
    public int distPerson(String name, String dept) {
       int count=userDao.distPerson(name,dept);
       return count;

    }


}
