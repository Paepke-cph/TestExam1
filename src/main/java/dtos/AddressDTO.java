package dtos;

import entity.Address;

public class AddressDTO {
    private String street;
    private String city;
    private Integer zip;

    public AddressDTO() {}

    public AddressDTO(String street, String city, Integer zip) {
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    public AddressDTO(Address address) {
        this.street = address.getStreet();
        this.city = address.getCity();
        this.zip = address.getZip();
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
}
