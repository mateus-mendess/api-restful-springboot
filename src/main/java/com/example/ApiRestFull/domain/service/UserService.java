package com.example.ApiRestFull.domain.service;

import com.example.ApiRestFull.domain.entity.User;
import com.example.ApiRestFull.domain.repository.UserDAO;
import com.example.ApiRestFull.dto.request.RequestUpdateUser;
import com.example.ApiRestFull.dto.request.RequestUser;
import com.example.ApiRestFull.dto.response.ResponseUser;
import com.example.ApiRestFull.exception.BusinessException;
import com.example.ApiRestFull.exception.NotFoundException;
import com.example.ApiRestFull.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder cryptPasswordEncoder;

    public UserService (UserDAO userDAO, UserMapper userMapper, BCryptPasswordEncoder cryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.userMapper = userMapper;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
    }

    public List<ResponseUser> findAllUsers() {
        return userMapper.toListResponseUser(userDAO.findAll());
    }

    @Transactional
    public ResponseUser save(RequestUser requestUser) {
        this.verifyIfUsernameAlreadyExists(requestUser.getUsername());
        this.verifyIfEmailAlreadyExists(requestUser.getEmail());
        requestUser.setPassword(cryptPasswordEncoder.encode(requestUser.getPassword()));
        User user = userDAO.save(userMapper.toUser(requestUser));
        return userMapper.toResponseUser(user);
    }

    @Transactional
    public void updateUser(RequestUser requestUser, Long id) {
    User user = userDAO.findById(id).orElseThrow(() ->
            new NotFoundException("User with ID: "+ id +" not found"));

    user.setUsername(requestUser.getUsername());
    user.setEmail(requestUser.getEmail());
    user.setPassword(cryptPasswordEncoder.encode(requestUser.getPassword()));
    user.setFullName(requestUser.getFullName());
    }

    @Transactional
    public void updatePartialUser(RequestUpdateUser requestUpdateUser, Long id) {
        User user = userDAO.findById(id).orElseThrow(()->
                new NotFoundException("User with ID: "+ id +" not found"));

        if (requestUpdateUser.getPassword() != null) {
            requestUpdateUser.setPassword(cryptPasswordEncoder.encode(requestUpdateUser.getPassword()));
        }

        userMapper.updateUserFromRequest(requestUpdateUser, user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userDAO.findById(id).orElseThrow(() ->
                new NotFoundException("User with ID: "+ id +" not found"));

        userDAO.delete(user);
    }

    private void verifyIfEmailAlreadyExists(String email) {
        if (userDAO.existsByEmail(email)) {
            throw new BusinessException("Email already registered");
        }
    }

    private void verifyIfUsernameAlreadyExists(String username) {
        if (userDAO.existsByUsername(username)) {
            throw new BusinessException("Username already registered");
        }
    }
}
