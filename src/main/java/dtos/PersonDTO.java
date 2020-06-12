package dtos;

import entity.Address;
import entity.Hobby;
import entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDTO {
    private Long id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private List<Hobby> hobbies = new ArrayList<>();
    private Address address;

    public PersonDTO() {
    }

    public PersonDTO(Long id, String email, String phone, String firstName, String lastName, List<Hobby> hobbies, Address addrees) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
        this.address = addrees;
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.phone = person.getPhone();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.hobbies = person.getHobbies();
        this.address = person.getAddress();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
