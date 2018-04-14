package com.yw.bos.service;

import com.yw.bos.domain.User;

public interface IUserService {

    public User login(User model);

    void editPassword(String id, String password);
}
