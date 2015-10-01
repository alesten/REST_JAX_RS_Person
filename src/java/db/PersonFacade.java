package db;

import entity.Person;
import interfaceses.IPersonFacade;
import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PersonFacade implements IPersonFacade {

    private EntityManagerFactory emf;

    public PersonFacade() {
        emf = Persistence.createEntityManagerFactory("REST_JAX_RS_PersonPU");
    }

    @Override
    public Person addPerson(Person p) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(p);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return p;
    }

    @Override
    public Person deletePerson(int id) {
        EntityManager em = emf.createEntityManager();

        Person p;

        try {
            em.getTransaction().begin();

            p = em.find(Person.class, id);

            em.remove(p);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
        return p;
    }

    @Override
    public Person getPerson(int id) {
        EntityManager em = emf.createEntityManager();

        Person p;

        try {
            em.getTransaction().begin();

            p = em.find(Person.class, id);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
        return p;
    }

    @Override
    public List<Person> getPersons() {
        EntityManager em = emf.createEntityManager();

        List<Person> persons;

        try {
            em.getTransaction().begin();

            persons = em.createNamedQuery("Person.findAll").getResultList();

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return persons;
    }

    @Override
    public Person editPerson(Person p) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            em.merge(p);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }

}
