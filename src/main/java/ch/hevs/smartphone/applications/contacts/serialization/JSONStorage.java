package ch.hevs.smartphone.applications.contacts.serialization;

import ch.hevs.smartphone.applications.contacts.Contact;
import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.errors.ErrorCode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JSONStorage implements StorableContact {
    // ATTRIBUTS

    // ARRAY LIST
    private ArrayList<Contact> contacts = new ArrayList<>();

    // TMP FILE
    private File tmp = File.createTempFile("contactsList", ".json");

    // GETTERS
    public ArrayList<Contact> getContacts(){return contacts;}

    public File getTmp() {
        return tmp;
    }

    // SETTERS
    public void setTabContact(ArrayList<Contact> tabContact) {
        this.contacts = tabContact;
    }

    // CONSTRUCTOR
    public JSONStorage() throws IOException, BusinessException {
        this.read(tmp);
        sortDescending(contacts);
    }

    // METHODS
    // Add contact in AddressBook
    public void addContact(Contact contact){
        contacts.add(contact);
    }

    @Override
    public ArrayList<Contact> read(File source) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        List<Contact> contactList;

        try {
            System.out.println("test1");
            contactList = mapper.readValue(source, new TypeReference<List<Contact>>(){});
            System.out.println("test2");
            contacts = (ArrayList<Contact>) contactList;
        } catch (IOException e) {
            // System.out.println("Error reading");
            throw new BusinessException("read error", e, ErrorCode.IO_ERROR);
        }

        return contacts;
    }

    @Override
    public void write(File destination, ArrayList<Contact> contacts) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(destination, contacts);
        } catch (IOException e) {
            // System.out.println("Error writing");
            throw new BusinessException("failed to save", e, ErrorCode.IO_ERROR);
        }
    }

    /**
     * Trie un array d'objet Contacts par ordre alphabétique en fonction du prénom
     * @param contacts
     */
    public void sortDescending(ArrayList<Contact> contacts) {
        Collections.sort(contacts, Comparator.comparing(Contact::getFirstName));
    }
}
