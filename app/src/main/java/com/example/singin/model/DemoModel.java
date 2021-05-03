package com.example.singin.model;

public class DemoModel {
    public int productImage;
    public String productPrice;
    public String productWeight;
    public String productQty;

    public DemoModel( int productImage, String productPrice, String productWeight, String productQty) {
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productWeight = productWeight;
        this.productQty = productQty;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }
}
