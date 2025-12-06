package io.yigitucun.eventflow.auth.service.service;

import io.yigitucun.eventflow.auth.service.client.UserClientService;
import io.yigitucun.eventflow.auth.service.dto.User;
import io.yigitucun.eventflow.auth.service.dto.client.responses.UserAuth;
import io.yigitucun.eventflow.exceptions.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService  implements UserDetailsService {
    private final UserClientService userClientService;

    public UserDetailService(UserClientService userClientService) {
        this.userClientService = userClientService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            UserAuth userAuth = userClientService.findUserByUsername(username);
            return new User(userAuth);
        } catch (Exception e) {
            throw new GlobalException("Username or password wrong.",HttpStatus.FORBIDDEN);
        }
    }


}
