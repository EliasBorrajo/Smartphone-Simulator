package ch.hevs.smartphone.applications.contacts.serialization;

import ch.hevs.smartphone.applications.contacts.Contact;
import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.errors.ErrorCode;
import ch.hevs.smartphone.parameters.jsonStorage.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Elias, Jonathan
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

    //PATH
    private String storePath;        // Permet de stoquer le contenu de notre VARIABLE D'ENVIRONNEMENT SYSTEME
    private String jsonPath;         // Est la variable qui contiendra le chemin FINAl sur le PC et selon l'OS,
                                     // à l'emplacement de stockage de notre fichier JSON

    // myObj FILE
    private File myObj;



    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public JSONStorageContact() throws IOException, BusinessException
    {
        definePathToStoreData();
        read();
        sortDescending(contactArray);
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    /**
     * Permet de récuperer la valeur stoqué sur le PC de l'utilisateur de l'app.
     * L'utilisateur va créer une VARIABLE D'ENVIRONNEMENT sur son OS / PC, pour décider à quel emplacement
     * il shouaite stoquer les fichiers JSON.
     *
     * 1) Il faut récuperer cette VARIABLE grâce à notre SINGLETON
     *
     * 2) Utiliser la classe PATH pour créer un chemin d'accès correcte peu importe l'OS.
     *    Cette classe utilise l'import java.nio, qui va grandement nous aider pour homogeneiser notre code.
     *
     *    Puis utiliser la clate PATHS pour CONCATENER le chemin d'accès de la VAARIABLE + le nom du fichier que l'on veut.
     *
     * 3) Définir dans une string le chemin d'accès finale crée par PATH, et l'utiliser pour la création de notre FILE.
     */
    private void definePathToStoreData()
    {
        // Récupère le CONTENU de la VARIABLE D'ENVIRONNEMENT --> le PATH contenu dans la variable d'environnement
        storePath = Config.getConfig().getStorePath();


        // Va m'écrire le chemin d'accès de manière coherente grâce à PATH & PATHS, et non faire du bricolage
        // COncatène correctement mon PATH qui sera stoqué dans la STRING
        Path path = Paths.get(storePath, "contactsList.json");

        jsonPath = path.toString();
        //System.out.println("Final path storing my JSON file is : " + jsonPath);

        myObj = new File(jsonPath);
        System.out.println("REAL REAL PATH OBJECT CONTACT FILE IS : " + myObj.getAbsolutePath());
    }

    /**
     * SERIALISATION READ DATA IN A JSON FILE
     * @return
     * @throws BusinessException
     * @throws IOException
     */
    @Override
    public ArrayList<Contact> read( ) throws BusinessException
    {
        ObjectMapper mapper = new ObjectMapper();       // Mapper n'aime pas les fichiers vides !!
        try
        {
            // Verifie que le fichier existe pas & Crée le ficher
            if ( ! myObj.exists())
            {
                myObj.createNewFile();
                System.out.println("File created: " + myObj.getName());
            }
            // verifie que le ficher n'est pas vide (NULL)
            else if (myObj.length() > 0)
            {
                contactArray.clear();

                contactList = mapper.readValue(myObj,
                        mapper.getTypeFactory().constructCollectionType(ArrayList.class, Contact.class));

                contactArray = (ArrayList<Contact>) contactList;        // casting de la LISTE en ARRAYLISTE
            } else
            {
                System.out.println("Empty file");
            }
        } catch (IOException e)
        {
            //System.out.println("An error occurred while READING JSON STORAGE CONTACT.");
            throw new BusinessException("An error occurred while READING JSON STORAGE CONTACT.", ErrorCode.READING_JSON_STORAGE_ERROR);
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
            System.out.println("SERIALISATION of contactList.JSON has failed : ");
            e.printStackTrace();
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


    public ArrayList<Contact> getContactArray() {
        return contactArray;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public String getStorePath() {
        return storePath;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public File getMyObj() {
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

