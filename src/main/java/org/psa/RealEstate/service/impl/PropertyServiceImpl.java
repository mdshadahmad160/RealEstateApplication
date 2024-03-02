package org.psa.RealEstate.service.impl;
import org.psa.RealEstate.entities.Agent;
import org.psa.RealEstate.entities.Property;
import org.psa.RealEstate.exception.AgentNotFoundException;
import org.psa.RealEstate.exception.PropertyNotFoundException;
import org.psa.RealEstate.payload.PropertyDto;
import org.psa.RealEstate.repository.AgentRepository;
import org.psa.RealEstate.repository.PropertyRepository;
import org.psa.RealEstate.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private ModelMapper mapper;
    public Property mapToEntity(PropertyDto propertyDto){
       /* Property property = new Property();
        property.setId();
        property.setPropertyType(propertyDto.getPropertyType());
        property.setLocation(propertyDto.getLocation());
        property.setPrice(propertyDto.getPrice());
        property.setBedrooms(propertyDto.getBedrooms());
        property.setBathrooms(propertyDto.getBathrooms());
        property.setPropertySize(propertyDto.getPropertySize());
        property.setYearBuilt(propertyDto.getYearBuilt());
        property.setPropertyFeatures(propertyDto.getPropertyFeatures());
        property.setNearbyFacilities(propertyDto.getNearbyFacilities());
        property.setPropertyStatus(propertyDto.getPropertyStatus());
        property.setPropertyDescription(propertyDto.getPropertyDescription());
        property.setAgent();
        property.setBookings();
        property.setAppointments();
        property.setPayments();
        property.setReviews();
        return property;*/
        Property property=mapper.map(propertyDto,Property.class);
        return property;
    }
    public PropertyDto mapToDto(Property property){
      /*  PropertyDto propertyDto = new PropertyDto();
        propertyDto.setPropertyType(property.getPropertyType());
        propertyDto.setLocation(property.getLocation());
        propertyDto.setPrice(property.getPrice());
        propertyDto.setBedrooms(property.getBedrooms());
        propertyDto.setBathrooms(property.getBathrooms());
        propertyDto.setPropertySize(property.getPropertySize());
        propertyDto.setYearBuilt(property.getYearBuilt());
        propertyDto.setPropertyFeatures(property.getPropertyFeatures());
        propertyDto.setNearbyFacilities(property.getNearbyFacilities());
        propertyDto.setPropertyStatus(property.getPropertyStatus());
        propertyDto.setPropertyDescription(property.getPropertyDescription());
        return propertyDto;*/
        PropertyDto propertyDto=mapper.map(property,PropertyDto.class);
        return propertyDto;
    }


    @Override
    public PropertyDto createProperty(long agentId, PropertyDto propertyDto) {
        Agent agent=agentRepository.findById(agentId).orElseThrow(
                ()-> new AgentNotFoundException("Invalid Agent Id: ")
        );
        Property property=mapToEntity(propertyDto);
        property.setAgent(agent);
        Property newProperty=propertyRepository.save(property);
        PropertyDto dto=mapToDto(newProperty);
        return dto;
    }

    @Override
    public PropertyDto updateProperty(long agentId, long propertyId, PropertyDto propertyDto) {
        Agent agent=agentRepository.findById(agentId).orElseThrow(
                ()-> new AgentNotFoundException("Agent Invalid With This Id: ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Invalid Id For Property Entity: ")
        );
        property.setPropertySize(propertyDto.getPropertySize());
        property.setPropertyDescription(propertyDto.getPropertyDescription());
        property.setPropertyFeatures(propertyDto.getPropertyFeatures());
        property.setPropertyType(propertyDto.getPropertyType());
        property.setPropertyStatus(propertyDto.getPropertyStatus());
        property.setBathrooms(propertyDto.getBathrooms());
        property.setBedrooms(propertyDto.getBedrooms());
        property.setNearbyFacilities(propertyDto.getNearbyFacilities());
        property.setLocation(propertyDto.getLocation());
        property.setYearBuilt(propertyDto.getYearBuilt());
        property.setPrice(propertyDto.getPrice());
        Property newProperty=propertyRepository.save(property);
        return mapToDto(newProperty);
    }

    @Override
    public PropertyDto findPropertyById(long propertyId,long agentId) {
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Property Not Found With This Id: ")
        );
        Agent agent=agentRepository.findById(agentId).orElseThrow(
                ()-> new AgentNotFoundException("Invalid Agent Id: ")
        );
        PropertyDto dto=mapToDto(property);
        return dto;
    }

    @Override
    public List<PropertyDto> getAllProperty() {
        List<Property> properties=propertyRepository.findAll();
        List<PropertyDto> propertyDto=properties.stream().map(property -> mapToDto(property))
                .collect(Collectors.toList());
        return propertyDto;
    }

    @Override
    public void deleteProperty(long agentId, long propertyId) {
        Agent agent=agentRepository.findById(agentId).orElseThrow(
                ()-> new AgentNotFoundException("Invalid Agent Id: ")
        );

        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Invalid Property Id: ")
        );
        propertyRepository.delete(property);

    }
}
