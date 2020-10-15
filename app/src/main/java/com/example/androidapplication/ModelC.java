package com.example.androidapplication;

//IT19118246
//Wijesekera S.M
public class ModelC {
    String id;
    String image;
    String brand;
    String Model;
    String transmission;
    String ModYear;
    String mileage;
    String fuel;
    String contact;
    String add_car;
    String update_car;

    public ModelC(String id, String image, String brand, String model, String transmission, String modYear, String mileage, String fuel, String contact, String add_car, String update_car) {
        this.id = id;
        this.image = image;
        this.brand = brand;
        Model = model;
        this.transmission = transmission;
        ModYear = modYear;
        this.mileage = mileage;
        this.fuel = fuel;
        this.contact = contact;
        this.add_car = add_car;
        this.update_car= update_car;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getModYear() {
        return ModYear;
    }

    public void setModYear(String modYear) {
        ModYear = modYear;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAdd_car() {
        return add_car;
    }

    public void setAdd_car(String add_car) {
        this.add_car = add_car;
    }

    public String getUpdate_car() {
        return update_car;
    }

    public void setUpdate_car(String update_car) {
        this.update_car = update_car;
    }
    public void toLowerCase() {
    }
}
