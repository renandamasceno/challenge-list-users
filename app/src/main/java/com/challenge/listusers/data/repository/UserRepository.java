package com.challenge.listusers.data.repository;

import androidx.lifecycle.LiveData;

import com.challenge.listusers.data.db.UserDao;
import com.challenge.listusers.model.User;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class UserRepository {
    private final UserDao userDao;

    @Inject
    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public LiveData<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void insert(User user) {
        Executors.newSingleThreadExecutor().execute(() -> userDao.insert(user));
    }

    public void update(User user) {
        Executors.newSingleThreadExecutor().execute(() -> userDao.update(user));
    }

    public void delete(User user) {
        Executors.newSingleThreadExecutor().execute(() -> userDao.delete(user));
    }
}
