package com.example.plantingshedule_admin;

public class PlantingShedule {

    private String plantID;
    private String plantName;
    private String description;

    public PlantingShedule() {
    }

    public PlantingShedule(String plantID, String plantName, String description) {
        this.plantID = plantID;
        this.plantName = plantName;
        this.description = description;
    }

    public String getPlantID() {
        return plantID;
    }

    public void setPlantID(String plantID) {
        this.plantID = plantID;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
