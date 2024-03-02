package org.psa.RealEstate.controller;
import org.psa.RealEstate.payload.ApiResponse;
import org.psa.RealEstate.payload.PropertyDto;
import org.psa.RealEstate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
@PostMapping("/agents/{agentId}/properties")
    public ResponseEntity<PropertyDto> createProperty(@PathVariable(value = "agentId") long agentId,
                                                      @RequestBody PropertyDto propertyDto){
         PropertyDto dto=propertyService.createProperty(agentId,propertyDto);
         return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PutMapping("/agents/{agentId}/{propertyId}/properties")
    public ResponseEntity<PropertyDto> updateProperty(@PathVariable(value = "agentId") long agentId,
                                                      @PathVariable(value = "propertyId") long propertyId,
                                                      @RequestBody PropertyDto propertyDto){
    PropertyDto dto=propertyService.updateProperty(agentId, propertyId, propertyDto);
    return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/agents/{propertyId}/{agentId}")
    public ResponseEntity<PropertyDto> findPropertyById(@PathVariable(value = "propertyId") long propertyId,
                                                        @PathVariable(value = "agentId") long agentId){
    PropertyDto dto=propertyService.findPropertyById(propertyId,agentId);
    return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDto>> getAllUser(){
    List<PropertyDto> dto=propertyService.getAllProperty();
    return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @DeleteMapping("/agents/{agentId}/{propertyId}")
    public ResponseEntity<ApiResponse> deleteProperty(@PathVariable(value = "agentId") long agentId,
                                                      @PathVariable(value = "propertyId") long propertyId){
          propertyService.deleteProperty(agentId, propertyId);
          return new ResponseEntity<ApiResponse>(new ApiResponse("Property Deleted Successfully ",true),HttpStatus.OK);
    }
}
