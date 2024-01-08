package com.challenge.listusers.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.challenge.listusers.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
