package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);
    @Insert
    void registerCart(CartEntity cartEntity);

    @Query("SELECT * from users where name=(:name) and password=(:password)")
    UserEntity login(String name, String password);

    @Query("SELECT * from users where name=(:name)")
    UserEntity getUser(String name);

    @Query("Select * from users")
    List<UserEntity> getAllUsers();

    @Query("SELECT * from products where productName=(:productName)")
    List<ProductEntity> getProductsName(String productName);

    @Query("SELECT * from carts where userID=(:userID)")
    List<CartEntity> getCartsByID(Integer userID);

}
