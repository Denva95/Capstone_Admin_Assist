package org.ndissandea.adminassist.service;

import org.ndissandea.adminassist.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public UserDetails loadUserByUsername(String userName);
    public void create(User user);
    //public User findUserByName(String name);

}