package interfaceses;

import entity.Person;
import java.util.List;

public interface IPersonFacade {

    public Person addPerson(Person p);

    public Person deletePerson(int id);

    public Person getPerson(int id);

    public List<Person> getPersons();

    public Person editPerson(Person p);
}
