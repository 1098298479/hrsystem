package edu.ecit.mapper;

import org.apache.ibatis.annotations.Param;
import edu.ecit.bean.Role;

import java.util.List;


public interface RoleMapper {
    List<Role> roles();

    int addNewRole(@Param("role") String role, @Param("roleZh") String roleZh);

    int deleteRoleById(Long rid);
}
