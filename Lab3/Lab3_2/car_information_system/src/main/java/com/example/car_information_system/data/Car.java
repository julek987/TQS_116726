package com.example.car_information_system.data;

import jakarta.persistence.*;

import org.jetbrains.annotations.NotNull;
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carid;
    private String maker;
    private String model;
    public Car(){
    }
    public Car(String maker , String model){
        this.maker=maker;
        this.model=model;
    }
    public void setId(Long id) {
        this.carid = id;
    }

    public Long getCarId() {
        return carid;
    }
    public String getMaker(){
        return maker;
    }
    public String getModel(){
        return model;
    }
    public void setCarId(Long id){
        this.carid=id;
    }
    public void setMaker(String s){
        this.maker=s;
    }
    public void setModel(String s){
        this.model=s;
    }
    public String toString(){
        return "Car ID: "+carid+" Car maker: "+maker+" Car model: "+model;
    }
}