package ru.dz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.dz.entity.User;
import ru.dz.repository.UserRepository;


@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        System.out.print(username);
        if (user == null) throw new UsernameNotFoundException("User with name " + username + " not found");
        return new MyUserDetail(user);
    }
}
