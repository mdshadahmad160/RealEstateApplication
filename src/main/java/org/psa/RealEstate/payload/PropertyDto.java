package org.psa.RealEstate.payload;

import lombok.Data;

@Data
public class PropertyDto {


    private String propertyType;
    private String location;
    private double price;
    private int bedrooms;
    private int bathrooms;
    private double propertySize;
    private int yearBuilt;
    //private String amenities;
    private String propertyFeatures;
    //private String schoolDistrict;
    private String nearbyFacilities;
    private String propertyStatus;
    private String propertyDescription;
}
