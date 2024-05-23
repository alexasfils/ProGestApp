package io.myproject.ProGest.service;

import io.myproject.ProGest.model.User;

public interface UserService {
    public User login(String email, String password);
    public User register(User u);
    public User findUserByEmail(String email);
    public User findUserById(Integer id);
    public User updateProfileImage(Integer userId, byte[] imageData);
    public User updateUser(User u, Integer id);
    public void deleteUser(Integer id);
}

//Completare service ed controllare i metodi del Repository
