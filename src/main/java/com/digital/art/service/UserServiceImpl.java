package com.digital.art.service;

import com.digital.art.dto.MediUserDTO;
import com.digital.art.dto.UserDTO;
import com.digital.art.jpa.UserRepository;
import com.digital.art.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;



    private Set<GrantedAuthority> getAuthority(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getUserRole().forEach(role -> {
            //authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
        });
        return authorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }


    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User save(UserDTO user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        return userRepository.save(newUser);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        MediUserDTO mediUser= null;
        if(user!=null) {
            Set<GrantedAuthority> authorities = getAuthority(user);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authorities));
            mediUser = new MediUserDTO(username,"{noop}"+user.getPassword(),true, true, true, true,authorities);
            mediUser.setUserId(user.getUserId());



        }else {
            throw new UsernameNotFoundException("User not Found");
        }
        return mediUser;
    }

}
