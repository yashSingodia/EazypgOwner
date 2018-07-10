package com.example.ainesh.eazypg_owner;

public class TVDetails {

    private String roomNo, brand, model, timeSinceInstallation, typeOfTV, screenSize, resolution;

    public TVDetails(String roomNo, String brand, String model, String timeSinceInstallation, String typeOfTV, String screenSize, String resolution) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.typeOfTV = typeOfTV;
        this.screenSize = screenSize;
        this.resolution = resolution;
    }

    public TVDetails() {

        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        typeOfTV = "";
        screenSize = "";
        resolution = "";
    }
}
