//package com.mobileshop.security;
//
//import com.mobileshop.entities.User;
//import com.mobileshop.repos.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    UserRepo userRepo;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.getUser(username);
//        if(user==null)
//        {
//            throw new UsernameNotFoundException("No User present with the given id");
//        }
//        return new MyUserDetails(user);
//    }
//}
