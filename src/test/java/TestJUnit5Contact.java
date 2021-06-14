import ch.hevs.smartphone.applications.contacts.Contact;
import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.errors.ErrorCode;
import ch.hevs.smartphone.applications.contacts.serialization.JSONStorageContact;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import javax.swing.*;

import static ch.hevs.smartphone.parameters.utils.TempFile.getTempFile;
import static org.junit.jupiter.api.Assertions.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Testing for contact app:
 * - Serialization and deserialization
 * - Error code testing for corrupted .json contact file
 *
 */
public class TestJUnit5Contact
{
    private JSONStorageContact jsonStorageContact = new JSONStorageContact();

    private Contact contact1;
    private Contact contact2;
    private Contact contact3;

    /**
     * variables initialization for testing
     */
    @BeforeEach
    void setup() {
        contact1 = new Contact("Contact1", "Contact1", "1111", null);
        contact2 = new Contact("Contact2", "Contact2", "2222", null);
        contact3 = new Contact("Contact3", "Contact3", "3333", null);
    }

    /**
     * Testing serialization and deserialization of a temporary File
     * @throws IOException
     * @throws BusinessException
     */
    @Test
    public void testSerializationDeserialization() {
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        ArrayList<Contact> deserializedContactArrayList = null;

        contactArrayList.add(contact1);
        contactArrayList.add(contact2);
        contactArrayList.add(contact3);

        File tmp = getTempFile(true);

        // Serialize data
        writeForTesting(tmp, contactArrayList);

        // Read JSON file
       deserializedContactArrayList =  readForTesting(tmp, deserializedContactArrayList);

        assertEquals(contactArrayList.toString(), deserializedContactArrayList.toString());
    }

    /**
     * Testing error code return after .json file deserialization of a corrupted file
     * WARNING: this test code will empty your .json file for the contact app and replace it with random numbers
     * @throws IOException
     *
     */
    @Test
    void testDeserializationCorruptedFile() throws IOException {

        BusinessException e; // Error tested
        File testFileContact = jsonStorageContact.getMyObj();
        Random garbage = new Random(); // Object with pseudo random integer used to corrupt the .json file

        writeGarbage(testFileContact,garbage.nextInt()); // Method to write the pseudo random numbers instead of contact infos

        // Return the error generate by the read of the corrupted file
        e = assertThrows(BusinessException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                jsonStorageContact.read();
            }
        });

        assertEquals(ErrorCode.READING_JSON_STORAGE_ERROR.getCode(), e.getErrorCode());
        // check that the error code got in the read of the corrupted .json fil match the one assigned in the ErrorCode class
    }

    /**
     * Method to write pseudo random integer in an object mapper
     * @param testFile
     * @param garbageBytes
     * @throws IOException
     */
    private void writeGarbage(File testFile, int garbageBytes) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(testFile, garbageBytes);
    }

    /**
     * Method test to deserialize a .json file in an ArrayList of Contact
     * @param tmp
     */
    private ArrayList<Contact> readForTesting(File tmp, ArrayList<Contact> contactArray) {
        ObjectMapper mapper = new ObjectMapper();

        // Read JSON file
        try {
            contactArray = mapper.readValue(tmp,
                    mapper.getTypeFactory().constructCollectionType(ArrayList.class, Contact.class));
        } catch (Exception e) {
            fail("Failed to deserialize the ArrayListe");
        }
        return contactArray;
    }

    /**
     * Method test to serialize an ArrayList of Contact in a .json file
     * @param tmp
     * @param contactArrayList
     */
    private void writeForTesting(File tmp, ArrayList<Contact> contactArrayList) {
        ObjectMapper mapper = new ObjectMapper();
        // Serialize data
        try {
            mapper.writeValue(tmp, contactArrayList);
        } catch(Exception e) {
            fail("Failed to serialize the Arraylist");
        }
    }

}

