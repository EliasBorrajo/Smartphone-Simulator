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

/**
 * Contient le carnet d'adresse des differents contactes.
 * Récupère le fichier JSON sur le PC afin de créer nos contactes
 */
public class JSONStorageContact implements StorableContact
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // ARRAY LIST - Ce sera le carnet d'adresse
    private ArrayList<Contact> contactArray = new ArrayList<>();

    // Liste qui permet de lire le JSOn et sera converti ensuite en ArrayList du carnet d'adresse
    private List<Contact> contactList;

    // myObj FILE
    File myObj = new File("contactList.json");


    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public JSONStorageContact() throws IOException, BusinessException
    {
        // System.out.println(myObj.getAbsolutePath());
        this.read();
        sortDescending(contactArray);
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************

    /**
     * SERIALISATION READ DATA IN A JSON FILE
     * @return
     * @throws BusinessException
     * @throws IOException
     */
    @Override
    public ArrayList<Contact> read( ) throws BusinessException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();       // Mapper n'aime pas les fichiers vides !!
        try
        {
            // Verifie que le fichier existe pas & Crée le ficher
            if (myObj.createNewFile())
            {
                System.out.println("File created: " + myObj.getName());
            }
            // verifie que le ficher n'est pas vide (NULL)
            else if (myObj.length() > 0)
            {
                contactArray.clear();
                System.out.println("test1");
                contactList = mapper.readValue(myObj, new TypeReference<List<Contact>>() { });
                System.out.println("test2");
                contactArray = (ArrayList<Contact>) contactList;        // casting de la LISTE en ARRAYLISTE
            } else
            {
                System.out.println("Empty file");
            }
        } catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return contactArray;
    }

    /**
     * SERIALISATION WRITE DATA IN A JSON FILE
     * @param destination
     * @param contacts
     * @throws BusinessException
     */
    @Override
    public void write(File destination, ArrayList<Contact> contacts) throws BusinessException
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            mapper.writeValue(destination, contacts);
        } catch (IOException e)
        {
            // System.out.println("Error writing");
            throw new BusinessException("failed to save", e, ErrorCode.IO_ERROR); //@TODO : ERRORCODE n'est pas IO_ERROR mais BUSINES NON ?
        }
    }

    /**
     * Trie un array d'objet Contacts par ordre alphabétique en fonction du prénom
     *
     * @param contacts
     */
    public void sortDescending(ArrayList<Contact> contacts)
    {
        Collections.sort(contacts, Comparator.comparing(Contact::getFirstName));
    }

    /**
     * Add contact in AddressBook
     * @param contact
     */
    public void addContact(Contact contact)
    {
        contactArray.add(contact);
    }


    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public ArrayList<Contact> getContactArray()
    {
        return contactArray;
    }

    public File getmyObj()
    {
        return myObj;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    public void setTabContact(ArrayList<Contact> tabContact)
    {
        this.contactArray = tabContact;
    }
}

