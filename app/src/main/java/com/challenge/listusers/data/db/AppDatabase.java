package com.challenge.listusers.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.challenge.listusers.util.Utils;
import com.challenge.listusers.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
@TypeConverters(Utils.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
