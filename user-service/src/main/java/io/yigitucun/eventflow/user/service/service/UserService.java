package io.yigitucun.eventflow.user.service.service;

import io.yigitucun.eventflow.exceptions.GlobalException;
import io.yigitucun.eventflow.user.service.dto.User;
import io.yigitucun.eventflow.user.service.dto.requests.CreateUserRequest;
import io.yigitucun.eventflow.user.service.dto.responses.UserDetail;
import io.yigitucun.eventflow.user.service.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public UserService(UserMapper userMapper, PasswordEncoder encoder) {
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    public List<User> getAllUsers(){
        return userMapper.findAll();
    }

    public UserDetail getById(int userId){
        return userMapper.findById(userId)
                .orElseThrow(()->new GlobalException("User not found", HttpStatus.NOT_FOUND));
    }

    public UserDetail getByUsername(String username){
        return userMapper.findByUsername(username)
                .orElseThrow(()->new GlobalException("User not found", HttpStatus.NOT_FOUND));

    }

    public void insertUser(CreateUserRequest request){
        request.setPassword(encoder.encode(request.getPassword()));
        System.out.println(request.getPassword());
        userMapper.insert(request);
    }

}
