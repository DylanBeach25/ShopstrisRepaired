package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "carts")
public class CartEntity {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "userID")
    Integer userID;
    @ColumnInfo(name = "finishedCart")
    String finishedCart;

    public CartEntity(Integer userID) {
        this.userID = userID;
        finishedCart = "no";
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
