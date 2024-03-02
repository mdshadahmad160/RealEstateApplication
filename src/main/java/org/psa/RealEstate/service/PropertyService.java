package org.psa.RealEstate.service;

import org.psa.RealEstate.payload.PropertyDto;

import java.util.List;

public interface PropertyService {
    PropertyDto createProperty(long agentId, PropertyDto propertyDto);
    PropertyDto updateProperty(long agentId, long propertyId,PropertyDto propertyDto);
    PropertyDto findPropertyById(long propertyId,long agentId);
    List<PropertyDto> getAllProperty();
    void deleteProperty(long agentId,long propertyId);
}
