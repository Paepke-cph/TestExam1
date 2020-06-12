package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @Basic(optional = false)
    @Column(name = "address_street")
    private String street;
    @Id
    @Basic(optional = false)
    @Column(name = "address_city")
    private String city;
    @Basic(optional = false)
    @Column(name = "address_zip")
    private Integer zip;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Person> persons = new ArrayList<>();

    public Address() {}

    public Address(String street, String city, Integer zip) {
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public void addPerson(Person person) {
        if(!persons.contains(person)) {
            persons.add(person);
            person.setAddress(this);
        }
    }
}
