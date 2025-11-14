package io.yigitucun.eventflow.user.service.mapper;

import io.yigitucun.eventflow.user.service.dto.User;
import io.yigitucun.eventflow.user.service.dto.requests.CreateUserRequest;
import io.yigitucun.eventflow.user.service.dto.responses.UserDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT id,name,username,email FROM users")
    List<User> findAll();

    @Select("""
        SELECT u.id,u.username,u.name,u.email,u.password,r.name as role ,r.id,u.created_at as role_id FROM users u
        INNER JOIN roles r ON r.id=u.role_id WHERE u.id=#{userId}
    """)
    Optional<UserDetail> findById(@Param("userId") int userId);

    @Select("""
        SELECT u.id,u.username,u.name,u.email,u.password,r.name as role ,r.id,u.created_at as role_id FROM users u
        INNER JOIN roles r ON r.id=u.role_id WHERE u.username=#{username}
    """)
    Optional<UserDetail> findByUsername(@Param("username") String username);

    @Select("SELECT EXISTS(SELECT 1 FROM users WHERE username=#{username})")
    boolean existsByUsername(@Param("username") String username);

    @Select("SELECT EXISTS(SELECT 1 FROM users WHERE email=#{email})")
    boolean existsByEmail(@Param("email") String email);

    @Insert("INSERT INTO users(name,username,email,password,role_id) VALUES(#{name},#{username},#{email},#{password},2)")
    void insert(CreateUserRequest request);


}
