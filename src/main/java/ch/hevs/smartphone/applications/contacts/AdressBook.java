package ch.hevs.smartphone.applications.contacts;

import java.util.ArrayList;

public class AdressBook {
    // ARRAY LIST
    private ArrayList<Contact> tabContact = new ArrayList<>();

    // Add contact in AdressBook
    void addContact(Contact contact){tabContact.add(contact);}

    //get AdressBook
    ArrayList<Contact> getTabContact(){return tabContact;}
}
