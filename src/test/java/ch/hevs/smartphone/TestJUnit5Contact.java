package ch.hevs.smartphone;

import ch.hevs.smartphone.applications.contacts.Contact;
import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.errors.ErrorCode;
import ch.hevs.smartphone.applications.contacts.serialization.JSONStorageContact;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static ch.hevs.smartphone.TempFile.TempFile.getTempFile;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Le testing permet, en cas de MAJ de Java, de vérifer rapidement que tout le projet fonctionne,
 * et si ce n'est pas le cas, de voir quelle partie ne fonctionne plus.
 *
 * Le test devra fournir un resultat, qui doit être PAREILLE que le résultat attendu.
 *
 * Tester la partie fonctionelle NON_Graphique
 * - Serialisation
 * - Tester l'API APP, Si il arrive à se connecter, récuperer les data correctement.
 * - Gallery totalement UI, donc
 */
public class TestJUnit5Contact
{

    private JSONStorageContact jsonStorageContact = new JSONStorageContact();
    private ArrayList<Contact> contactArrayList = new ArrayList<>();
    private Contact contact1;
    private Contact contact2;
    private Contact contact3;


    public TestJUnit5Contact() throws IOException, BusinessException {
    }

    @BeforeEach
    void setup() {
        contact1 = new Contact("Contact1", "Contact1", "1111");
        contact2 = new Contact("Contact2", "Contact2", "2222");
        contact3 = new Contact("Contact3", "Contact3", "3333");
    }

    /**
     * Test la sérialisation et la désérialisation d'un fichier temp
     * @throws IOException
     * @throws BusinessException
     */
    @Test
    public void testSerializationDeserialization() throws IOException, BusinessException {
        ArrayList<Contact> deserializedContactArrayList = null;
        ObjectMapper om = new ObjectMapper();

        contactArrayList.add(contact1);
        contactArrayList.add(contact2);
        contactArrayList.add(contact3);

        File tmp = getTempFile(true);

        // Serialize data
        try {
            om.writeValue(tmp, contactArrayList);
        } catch(Exception e) {
            fail("Erreur de sérialisation de l'ArrayList");
        }

        // Read JSON file
        try {
            deserializedContactArrayList = om.readValue(tmp,
                    om.getTypeFactory().constructCollectionType(ArrayList.class, Contact.class));
        } catch (Exception e) {
            fail("Erreur de désérialisation de l'ArrayList");
        }

        assertEquals(contactArrayList.toString(), deserializedContactArrayList.toString());
    }

    /**
     * Test si une erreur de désérialisation retourne bien le bon message d'erreur
     * @throws IOException
     *
     */
    @Test
    void testDeserializationCorruptedFile() throws IOException {

        BusinessException e; // erreur qui sera testée

        File testFileContact = jsonStorageContact.getMyObj();
        Random garbage = new Random(); // objet contenant des nombres pseudo aléatoire qu'on va utiliser pour corrompre notre fichier json

        writeGarbage(testFileContact,garbage.nextInt()); // methode qui écrit ces nombres pseudo aléatoire à la place de l'information sur les contacts

        // retourne l'erreur générée par la lecture du fichier corrompu
        e = assertThrows(BusinessException.class, new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        jsonStorageContact.read();
                    }
                });

        assertEquals(ErrorCode.READING_JSON_STORAGE_ERROR.getCode(), e.getErrorCode()); // vérifie que le code d'erreur est le même que celui qui devrait apparaitre lors d'une erreur de lecture du fichier json

    }

    /**
     * Méthode qui écrit des int pseudo aléatoire dans un fichier de type File
     * @param testFile
     * @param garbageBytes
     * @throws IOException
     */
    private void writeGarbage(File testFile, int garbageBytes) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(testFile, garbageBytes);
    }

}
