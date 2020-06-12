package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "hobbies")
public class Hobby {
    @Id
    @Basic(optional = false)
    @Column(name = "hobby_name")
    private String name;
    @Basic(optional = false)
    @Column(name = "hobby_description")
    private String description;

    @ManyToMany(mappedBy = "hobbies")
    private List<Person> persons = new ArrayList<>();

    public Hobby()  {}
    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addPerson(Person person) {
        person.addHobby(this);
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hobby hobby = (Hobby) o;
        return Objects.equals(name, hobby.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
