package com.sinosoft.stukpisys.servsce.impl;

import com.sinosoft.stukpisys.dao.UserDao;
import com.sinosoft.stukpisys.entity.JwtUser;
import com.sinosoft.stukpisys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户验证方法
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public JwtUserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            List<String> role = new ArrayList<>();
            role.add(user.getRole());
            return new JwtUser(user.getName(), user.getPassword(), role.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }
    }

}
