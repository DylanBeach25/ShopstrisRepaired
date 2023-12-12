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
    @Insert
    void addProduct(ProductEntity productEntity);

    @Query("SELECT * from users where name=(:name) and password=(:password)")
    UserEntity login(String name, String password);

    @Query("SELECT * from users where name=(:name)")
    UserEntity getUser(String name);

    @Query("Select * from users")
    List<UserEntity> getAllUsers();

    @Query("SELECT * from products where productName=(:productName)")
    List<ProductEntity> getProductsName(String productName);
    @Query("SELECT * from products where productName=(:productName) and cartID=(:cartID)")
    List<ProductEntity> getProductsNameCartID(String productName,Integer cartID);

   @Query("SELECT*from products where productName=(:productName) and cartID=(:cartID)")
   ProductEntity getSingularProductNameCartID(String productName,Integer cartID);
    @Query("SELECT * from products where cartID=(:cartID)")
    List<ProductEntity> getProductsCartID(Integer cartID);
    @Query("SELECT * from products where cartID=(:cartID) and userID=(:userID)")
    List<ProductEntity> getProductsCartIDUserID(Integer cartID,Integer userID);
    @Query("DELETE FROM products where productName=(:name) and cartID=(:cartID)")
    void deleteItem(String name, Integer cartID);

    @Query("SELECT * from carts where userID=(:userID)")
    List<CartEntity> getCartsByID(Integer userID);

    @Query("SELECT * from carts where userID=(:userID) and finishedCart=(:finishedCart)")
    List<CartEntity> getCartsByCartState(Integer userID, String finishedCart);
    @Query("SELECT * from carts where userID=(:userID) and name=(:name)")
    List<CartEntity> getCartsByUserIDandName(Integer userID,String name);

    @Query("SELECT * from carts where userID=(:userID) and name=(:name)")
    CartEntity getSingularCartByUserIDandName(Integer userID, String name);

    @Query("UPDATE carts SET finishedCart=(:finishedCart) WHERE userID=(:userID) and id=(:id)")
    void updateCart(String finishedCart, Integer userID, Integer id);


}
