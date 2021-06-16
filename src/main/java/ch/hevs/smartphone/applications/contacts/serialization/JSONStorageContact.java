package ch.hevs.smartphone.applications.contacts.serialization;

import ch.hevs.smartphone.applications.contacts.Contact;
import ch.hevs.smartphone.errors.BusinessException;
import ch.hevs.smartphone.errors.ErrorCode;
import ch.hevs.smartphone.parameters.jsonStorage.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Contains the adress book
 * Retrieve the JSON file on the PC in order to create our contacts
 *
 * @author Borrajo Elias, Bourquin Jonathan
 */
public class JSONStorageContact implements StorableContact {

    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // ARRAY LIST - It will be our Address book
    private ArrayList<Contact> contactArray = new ArrayList<>();

    // List which allows to read the JSON and will then be converted into an ArrayList of the address book
    private List<Contact> contactList;

    //PATH
    private String storePath;       // Allows to store the content of our SYSTEM ENVIRONMENT VARIABLE
    private String jsonPath;        // Is the variable which will contain the FINAl path on the PC and depending on the OS,
                                    // to the storage location of our JSON file
    // myObj FILE
    private File myObj;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************

    /**
     * Constructor
     */
    public JSONStorageContact() {
        definePathToStoreData();
        try {
            read();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        sortDescending(contactArray);
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    /**
     * Allows you to retrieve the value stored on the app user's PC.
     * The user will create an ENVIRONMENT VARIABLE on his OS / PC, to decide where he wants to store the JSON files.
     * <p>
     * 1) We need to retrieve this VARIABLE using our SINGLETON
     * <p>
     * 2) Use the PATH class to create a correct path no matter the OS.
     * This class uses the java.nio import, which will greatly help us to homogenize our code.
     * <p>
     * Then use the PATH class to CONCATENATE the path of the VARIABLE + the file name we want.
     * <p>
     * 3) Define in a string the final path created by PATH, and use it for the creation of our FILE.Allows us to retrieve the value stored on the app user's PC.
     */
    private void definePathToStoreData() {
        // Retrieves the contents of the ENVIRONMENT VARIABLE
        storePath = Config.getConfig().getStorePath();

        // Will write the path consistently thanks to PATH & PATHS
        // Correctly concatenate my PATH which will be stored in the STRING
        Path path = Paths.get(storePath, "contactsList.json");

        jsonPath = path.toString();

        myObj = new File(jsonPath);
        System.out.println("REAL REAL PATH OBJECT CONTACT FILE IS : " + myObj.getAbsolutePath());
    }

    /**
     * SERIALISATION READ DATA IN A JSON FILE
     *
     * If the file is empty, it reads without problem.
     * If the file does not exist, it creates it.
     * If the file is corrupted, it does not read it,
     * and the file will be overwrite during the next serialization when we turn off the smartphone.
     *
     * @return an arrayList of our contacts
     * @throws BusinessException
     */
    @Override
    public ArrayList<Contact> read() throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();       // Mapper doesn't like empty file
        try {
            // Check that the file does not exist & Create the file
            if (!myObj.exists()) {
                myObj.createNewFile();
                System.out.println("File created: " + myObj.getName());
            }
            // Check that the file is not empty (NULL)
            else if (myObj.length() > 0) {
                contactArray.clear();

                contactList = mapper.readValue(myObj, new TypeReference<List<Contact>>() {
                });

                contactArray = (ArrayList<Contact>) contactList;        // casting the LIST in ARRAYLIST
            } else {
                System.out.println("Empty file");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while retrieving the address book." +
                    "                                                 \nA blank address book has been initialized" +
                    "                                                 \nPossible causes: JSON file corruption");

           throw new BusinessException("An error occurred while READING JSON STORAGE CONTACT.", ErrorCode.READING_JSON_STORAGE_CONTACT_ERROR);
        }
        return contactArray;
    }

    /**
     * SERIALIZATION WRITE DATA IN A JSON FILE
     *
     * @param destination
     * @param contacts
     */
    @Override
    public void write(File destination, ArrayList<Contact> contacts) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(destination, contacts);
        } catch (IOException e) {
            System.out.println("SERIALISATION of contactList.JSON has failed : ");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while writing / serializing the address book." +
                    "                                                   \n The address book is not saved, restart the smartphone.");
        }
    }

    /**
     * Sorts an array of Contacts object alphabetically by first name
     *
     * @param contacts
     */
    public void sortDescending(ArrayList<Contact> contacts) {
        Collections.sort(contacts, Comparator.comparing(Contact::getFirstName));
    }

    /**
     * Add contact in AddressBook
     *
     * @param contact
     */
    public void addContact(Contact contact) {
        contactArray.add(contact);
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public ArrayList<Contact> getContactArray() {
        return contactArray;
    }

    public File getMyObj() {
        return myObj;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    public void setTabContact(ArrayList<Contact> tabContact) {
        this.contactArray = tabContact;
    }
}

