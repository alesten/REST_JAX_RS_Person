package test;

import db.PersonFacade;
import entity.Person;

public class Tester {
    public static void main(String[] args) {
        PersonFacade pf = new PersonFacade();
        
        Person p = new Person();
        p.setfName("Peter");
        p.setlName("Hansen");
        
        pf.addPerson(p);
    }
           
}
