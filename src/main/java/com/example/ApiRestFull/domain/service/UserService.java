package com.example.ApiRestFull.domain.service;

import com.example.ApiRestFull.domain.entity.User;
import com.example.ApiRestFull.domain.repository.UserDAO;
import com.example.ApiRestFull.dto.request.RequestUser;
import com.example.ApiRestFull.dto.response.ResponseUser;
import com.example.ApiRestFull.exception.BusinessException;
import com.example.ApiRestFull.exception.NotFoundException;
import com.example.ApiRestFull.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final UserMapper userMapper;

    public UserService (UserDAO userDAO, UserMapper userMapper) {
        this.userDAO = userDAO;
        this.userMapper = userMapper;
    }

    public List<ResponseUser> findAllUsers() {
        return userMapper.toListResponseUser(userDAO.findAll());
    }

    @Transactional
    public ResponseUser save(RequestUser requestUser) {
        this.verifyIfUsernameAlreadyExists(requestUser.username());
        this.verifyIfEmailAlreadyExists(requestUser.email());
        User user = userDAO.save(userMapper.toUser(requestUser));
        return userMapper.toResponseUser(user);
    }

    @Transactional
    public void updateUser(RequestUser requestUser, Long id) {
    User user = userDAO.findById(id).orElseThrow(() ->
            new NotFoundException("User with ID: "+ id +" not found"));

    user.setUsername(requestUser.username());
    user.setEmail(requestUser.email());
    user.setPassword(requestUser.password());
    user.setFullName(requestUser.fullName());
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userDAO.findById(id).orElseThrow(() ->
                new NotFoundException("User with ID: "+ id +" not found"));

        userDAO.delete(user);
    }

    private void verifyIfEmailAlreadyExists(String email) {
        if (userDAO.existsByEmail(email)) {
            throw new BusinessException("E-mail já cadastrado");
        }
    }

    private void verifyIfUsernameAlreadyExists(String username) {
        if (userDAO.existsByUsername(username)) {
            throw new BusinessException("Username já cadastrado");
        }
    }
}
