package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "carts")
public class CartEntity {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "userID")
    Integer userID;
    @ColumnInfo(name = "finishedCart")
    String finishedCart;

    public CartEntity(Integer userID, String name) {
        this.userID = userID;
        this.name = name;
        finishedCart = "no";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFinishedCart() {
        return finishedCart;
    }

    public void setFinishedCart(String finishedCart) {
        this.finishedCart = finishedCart;
    }
}
