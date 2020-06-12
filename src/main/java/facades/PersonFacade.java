package facades;

import dtos.PersonDTO;
import entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class PersonFacade {
    private static EntityManagerFactory entityManagerFactory;
    private static PersonFacade instance;

    private PersonFacade(){}

    public static PersonFacade getUserFacade (EntityManagerFactory other) {
        if (instance == null) {
            entityManagerFactory = other;
            instance = new PersonFacade();
        }
        return instance;
    }

    public PersonDTO findPerson(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        PersonDTO result;
        try {
            Person found = entityManager.find(Person.class, id);
            result = new PersonDTO(found);
        } finally {
            entityManager.close();
        }
        return result;
    }

    public List<PersonDTO> findPersonByName(String firstName, String lastName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        if(firstName.isEmpty())
            firstName = "%";
        else
            firstName = "%"+firstName+"%";
        if(lastName.isEmpty())
            lastName = "%";
        else
            lastName = "%"+lastName+"%";
        List<PersonDTO> result = new ArrayList<>();
        try {
            List<Person> found = entityManager
                    .createNamedQuery("Person.findByFullName",Person.class)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .getResultList();
            if(!found.isEmpty()) {
                found.forEach((person) -> {
                    result.add(new PersonDTO(person));
                });
            }
        } finally {
            entityManager.close();
        }
        return result;
    }

    public PersonDTO createPerson(PersonDTO personDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Person person = new Person(personDTO);
        try {
         entityManager.getTransaction().begin();
         entityManager.persist(person);
         entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        personDTO.setId(person.getId());
        return personDTO;
    }

    public PersonDTO updatePerson(PersonDTO personDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        PersonDTO result;
        try {
            Person person = new Person(personDTO);
            entityManager.getTransaction().begin();
            entityManager.refresh(person);
            entityManager.getTransaction().commit();
            result = new PersonDTO(person);
        } finally {
            entityManager.close();
        }
        return result;
    }

    public void deletePerson(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Person found = entityManager.find(Person.class, id);
            if(found != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(found);
                entityManager.getTransaction().commit();
            }
        } finally {
            entityManager.close();
        }
    }

}
