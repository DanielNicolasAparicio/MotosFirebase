package com.example.motosfirebase;

public class PersonModel {

    String id, ProductName, brand, motor, color, modelo, velocidad, price;

    public PersonModel(String id, String ProductName, String brand, String motor, String color, String modelo, String velocidad, String price) {
        this.id = id;
        this.ProductName = ProductName;
        this.brand = brand;
        this.motor = motor;
        this.color = color;
        this.modelo = modelo;
        this.velocidad = velocidad;
        this.price = price;
    }

    public PersonModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
