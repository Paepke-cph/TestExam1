package entity;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "person_email")
    private String email;
    @Basic(optional = false)
    @Column(name = "person_phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "person_first_name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "person_last_name")
    private String lastName;

    public Person() {}
    public Person(String email, String phone, String firstName, String lastName) {
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
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
}
