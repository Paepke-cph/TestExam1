package utils;

import dtos.PersonDTO;
import entity.Address;
import entity.Hobby;
import entity.Person;
import facades.PersonFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                EMF_Creator.createEntityManagerFactory(
                        EMF_Creator.DbSelector.TEST,
                        EMF_Creator.Strategy.DROP_AND_CREATE);
        EntityManager em = emf.createEntityManager();

        Person person = new Person("pLarsen@gmail.com","22222222","Peter","Larsen");
        Person person2 = new Person("nLarsen@gmail.com", "33333333", "Niels", "Larsen");
        Hobby hobby = new Hobby("Chess", "We are playing chess");
        Hobby hobby2 = new Hobby("Boxing", "We are boxing");
        Address address = new Address("Street1", "City1", 1000);
        
        em.getTransaction().begin();
        em.persist(hobby);
        em.persist(address);
        em.getTransaction().commit();

        person.addHobby(hobby);
        person2.addHobby(hobby);
        person2.addHobby(hobby2);

        em.getTransaction().begin();
        em.persist(person);
        em.persist(person2);
        address.addPerson(person);
        address.addPerson(person2);
        em.getTransaction().commit();

        em.close();

        PersonFacade personFacade = PersonFacade.getUserFacade(emf);
        List<PersonDTO> personDTO = personFacade.findPersonByName("P", "Larsen");

        emf.close();
    }
}
