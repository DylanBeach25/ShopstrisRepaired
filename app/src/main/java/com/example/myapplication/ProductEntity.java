package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductEntity {

    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "productName")
    String productName;
    @ColumnInfo(name = "price")
    Double productPrice;
    @ColumnInfo(name = "description")
    String description;

    @ColumnInfo(name = "userID")
    Integer userID;

    @ColumnInfo(name = "cartID")
    Integer cartID;

    public ProductEntity(String productName, Double productPrice, String description, Integer userID, Integer cartID) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.userID = userID;
        this.cartID = cartID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getCartID() {
        return cartID;
    }

    public void setCartID(Integer cartID) {
        this.cartID = cartID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setName(String name) {
        this.productName = name;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return "name='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", description='" + description + '\'' +
                '}';
    }
}
