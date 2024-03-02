package org.psa.RealEstate.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;
    @OneToMany(mappedBy = "property",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookings;
    @OneToMany(mappedBy = "property",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "property",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;
    @OneToMany(mappedBy = "property",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;







}

