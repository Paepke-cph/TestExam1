package entity;

import javax.persistence.*;

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
}
