package com.example.EazyPG.owner.DetailsClasses;

public class ComplaintDetails {

    public String firstLevel, secondLevel, thirdLevel, description, imageName, status, uploadId, availabilityTime, complaintId;
    public String name, roomNo;

    public ComplaintDetails(){

    }

    public ComplaintDetails(String uploadId, String secondLevel, String thirdLevel, String description, String imageName, String status, String availabilityTime, String complaintId) {
        this.uploadId = uploadId;
        this.secondLevel = secondLevel;
        this.thirdLevel = thirdLevel;
        this.description = description;
        this.imageName = imageName;
        this.status = status;
        this.availabilityTime = availabilityTime;
        this.complaintId = complaintId;
    }

    public ComplaintDetails(String name, String roomNo, String uploadId, String firstLevel, String secondLevel, String thirdLevel, String description, String imageName, String status, String availabilityTime, String complaintId) {

        this.name = name;
        this.roomNo = roomNo;
        this.uploadId = uploadId;
        this.firstLevel = firstLevel;
        this.secondLevel = secondLevel;
        this.thirdLevel = thirdLevel;
        this.description = description;
        this.imageName = imageName;
        this.status = status;
        this.availabilityTime = availabilityTime;
        this.complaintId = complaintId;
    }
}
