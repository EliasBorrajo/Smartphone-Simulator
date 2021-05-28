package ch.hevs.smartphone.applications.contacts;

import java.io.*;
import java.util.ArrayList;

public class AddressBook {
    // ATTRIBUTS
    // ARRAY LIST
    private ArrayList<Contact> tabContact = new ArrayList<>();

    //Save pour serialiser et deserializer
    public static final String PATH = "saves/contactBook.se";

    // GETTERS
    ArrayList<Contact> getTabContact(){return tabContact;}

    // SETTERS
    public void setTabContact(ArrayList<Contact> tabContact) {
        this.tabContact = tabContact;
    }

    // CONSTRUCTOR
    public AddressBook() {
        this.get();
    }

    // METHODS
    // Add contact in AddressBook
    public void addContact(Contact contact){
        tabContact.add(contact);
    }

    /**
     * Save photos, sérialisation
     */
    public void save(){
        try {
            //créer un flux de sortie jusqu'à .se
            FileOutputStream reader = new FileOutputStream(PATH);
            BufferedOutputStream bFile = new BufferedOutputStream (reader);
            ObjectOutputStream obFile = new ObjectOutputStream(bFile);
            obFile.writeObject(this.tabContact); //écrire
            obFile.close(); /// fermer
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    /**
     * Récupère les informations, déssérialisation
     */
    protected void get(){
        try {
            if(new File(PATH).exists()){
                this.tabContact.clear();
                FileInputStream reader = new FileInputStream(PATH);
                BufferedInputStream bFile = new BufferedInputStream(reader);
                ObjectInputStream obFile = new ObjectInputStream(bFile);

                this.tabContact = (ArrayList<Contact>) obFile.readObject();

                obFile.close();

            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

