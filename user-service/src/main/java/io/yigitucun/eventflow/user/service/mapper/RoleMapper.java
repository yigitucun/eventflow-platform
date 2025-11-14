package io.yigitucun.eventflow.user.service.mapper;

import io.yigitucun.eventflow.user.service.dto.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    @Select("SELECT * FROM roles")
    List<Role> findAll();
}
