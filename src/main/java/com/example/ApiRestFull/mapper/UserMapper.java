package com.example.ApiRestFull.mapper;

import com.example.ApiRestFull.domain.entity.User;
import com.example.ApiRestFull.dto.request.RequestUpdateUser;
import com.example.ApiRestFull.dto.request.RequestUser;
import com.example.ApiRestFull.dto.response.ResponseUser;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RolesMapper.class})
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    User toUser(RequestUser requestUser);

    ResponseUser toResponseUser(User user);

    List<ResponseUser> toListResponseUser(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(RequestUpdateUser requestUpdateUser, @MappingTarget User user);
}
