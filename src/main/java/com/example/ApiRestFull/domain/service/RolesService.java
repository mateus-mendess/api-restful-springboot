package com.example.ApiRestFull.domain.service;

import com.example.ApiRestFull.domain.entity.Roles;
import com.example.ApiRestFull.domain.repository.RolesDAO;
import com.example.ApiRestFull.exception.NotFoundException;
import com.example.ApiRestFull.mapper.RolesMapper;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    private final RolesDAO rolesDAO;
    private final RolesMapper rolesMapper;

    public RolesService(RolesDAO rolesDAO, RolesMapper rolesMapper) {
        this.rolesDAO = rolesDAO;
        this.rolesMapper = rolesMapper;
    }

    protected Roles getRoleByName(String name) {
        return rolesDAO.findByName(name.toUpperCase()).orElseThrow(() ->
                new NotFoundException("Role with name "+name+" not found."));
    }
}
