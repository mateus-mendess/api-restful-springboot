package com.example.ApiRestFull.mapper;

import com.example.ApiRestFull.domain.entity.User;
import com.example.ApiRestFull.dto.request.RequestUser;
import com.example.ApiRestFull.dto.response.ResponseUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RequestUser requestUser);

    ResponseUser toResponseUser(User user);

    List<ResponseUser> toListResponseUser(List<User> users);
}
