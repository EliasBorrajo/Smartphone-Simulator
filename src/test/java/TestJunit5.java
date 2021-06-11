import ch.hevs.smartphone.applications.contacts.Contact;
import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.errors.ErrorCode;
import ch.hevs.smartphone.applications.contacts.serialization.JSONStorageContact;
import ch.hevs.smartphone.parameters.jsonStorage.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class TestJunit5
{
    @Test
    public void testSerialization() throws IOException, BusinessException {
        JSONStorageContact jsonStorageContact = new JSONStorageContact();
        ArrayList<Contact> contactArray = new ArrayList<>();
        List<Contact> contactList;
        ObjectMapper om = new ObjectMapper();

        File myFile = new File(jsonStorageContact.getJsonPath());
        myFile.createNewFile();
        //System.out.println(myFile);
        // compter le nombre d'objet dans le fichier json, puis sérialiser l'objet
         //   et comparer la taille de l'array avec celle du fichier json. si les deux correspondent la sérialisation s'est bien déroulée

        contactList = om.readValue(myFile, new TypeReference<List<Contact>>() { });
        contactArray = (ArrayList<Contact>) contactList;
        System.out.println(contactArray);



    }




    /**
     * test si une erreur de désérialisation retourne bien le bon message d'erreur
     * @throws IOException
     * @throws BusinessException
     */
    @Test
    void testDeserializationCorruptedFile() throws IOException, BusinessException {

        JSONStorageContact jsonStorageContact = new JSONStorageContact();
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        BusinessException e; // erreur qui sera testée

        File testFileContact = jsonStorageContact.getMyObj();
        Random garbage = new Random(); // objet contenant des nombres pseudo aléatoire qu'on va utiliser pour corrompre notre fichier json

        writegarbage(testFileContact,garbage.nextInt()); // methode qui écrit ces nombres pseudo aléatoire à la place de l'information sur les contacts

        // retourne l'erreur générée par la lecture du fichier corrompu
        e = assertThrows(BusinessException.class, new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        jsonStorageContact.read();
                    }
                });

        assertEquals(ErrorCode.READING_JSON_STORAGE_ERROR.getCode(), e.getErrorCode()); // vérifie que le code d'erreur est le même que celui qui devrait apparaitre lors d'une erreur de lecture du fichier json

    }

    private void writegarbage(File testFile, int garbageBytes) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(testFile, garbageBytes);
    }

}
