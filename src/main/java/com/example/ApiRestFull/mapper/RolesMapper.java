package com.example.ApiRestFull.mapper;

import com.example.ApiRestFull.domain.entity.Roles;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RolesMapper {
    Roles toRole(String name);

    Set<Roles> toRoles(Set<String> roles);
}
