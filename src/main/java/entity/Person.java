package entity;

import dtos.PersonDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "persons")
@NamedQuery(
        name = "Person.findByFullName",
        query = "select p from Person p where p.firstName LIKE :firstName AND p.lastName LIKE :lastName")
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

    @JoinTable(name = "person_hobbies", joinColumns = {
            @JoinColumn(name = "person_id", referencedColumnName = "person_id")}, inverseJoinColumns = {
            @JoinColumn(name = "hobby_name", referencedColumnName = "hobby_name")})
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Hobby> hobbies = new ArrayList<>();

    @ManyToOne()
    private Address address;
    public Person() {}
    public Person(PersonDTO personDTO) {
        this.id = personDTO.getId();
        this.email = personDTO.getEmail();
        this.phone = personDTO.getPhone();
        this.firstName = personDTO.getFirstName();
        this.lastName = personDTO.getLastName();
        this.hobbies = personDTO.getHobbies();
        this.address = personDTO.getAddress();
    }
    public Person(String email, String phone, String firstName, String lastName) {
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
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

    public void addHobby(Hobby hobby) {
        if(!this.hobbies.contains(hobby)) {
            this.hobbies.add(hobby);
            hobby.getPersons().add(this);
        }
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(email, person.email) &&
                Objects.equals(phone, person.phone) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, phone, firstName, lastName);
    }
}
