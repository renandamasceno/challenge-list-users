package com.challenge.listusers.di.module;

import android.app.Application;

import androidx.room.Room;

import com.challenge.listusers.data.db.AppDatabase;
import com.challenge.listusers.data.db.UserDao;
import com.challenge.listusers.data.repository.UserRepository;
import com.challenge.listusers.network.service.UserService;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    public AppDatabase provideAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "user_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    public UserDao provideUserDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }

    @Provides
    public UserRepository provideUserRepository(UserDao userDao) {
        return new UserRepository(userDao);
    }

    @Provides
    public UserService provideUserService() {
        return new UserService();
    }
}
