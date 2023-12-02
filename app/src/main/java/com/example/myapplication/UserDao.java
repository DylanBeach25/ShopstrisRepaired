package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * from users where name=(:name) and password=(:password)")
    UserEntity login(String name, String password);

    @Query("SELECT * from users where name=(:name)")
    UserEntity getUser(String name);

}
