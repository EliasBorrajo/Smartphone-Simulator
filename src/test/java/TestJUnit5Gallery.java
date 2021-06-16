import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.errors.ErrorCode;
import ch.hevs.smartphone.applications.gallery.Photo;
import ch.hevs.smartphone.applications.gallery.serialisation.JSONStoragePhoto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static ch.hevs.smartphone.parameters.utils.TempFile.getTempFile;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing for gallery app:
 *  * - Serialization and deserialization
 *  * - Error code testing for corrupted .json gallery file
 */
public class TestJUnit5Gallery {
    private JSONStoragePhoto jsonStoragePhoto = new JSONStoragePhoto();

    private static Photo photo1;
    private static Photo photo2;
    private static Photo photo3;

    /**
     * Variables initialization for testing
     */
    @BeforeAll
    static void init() {
        photo1 = new Photo("path1", "photo1");
        photo2 = new Photo("path2", "photo2");
        photo3 = new Photo("path3", "photo3");
    }

    /**
     * Testing serialization and deserialization of a temporary File
     */
    @Test
    public void testSerializationDeserialization() {
        ArrayList<Photo> photosArraylist = new ArrayList<>();
        ArrayList<Photo> deserializedPhotosArraylist = null;

        photosArraylist.add(photo1);
        photosArraylist.add(photo2);
        photosArraylist.add(photo3);

        File tmp = getTempFile(true);

        // Serialize data
        writeForTesting(tmp, photosArraylist);

        // Read JSON file
        deserializedPhotosArraylist =  readForTesting(tmp, deserializedPhotosArraylist);

        assertEquals(photosArraylist.toString(), deserializedPhotosArraylist.toString());
    }

    @Test
    void testDeserializationCorruptedFile() throws IOException {

        BusinessException e; // Error tested
        File testFileContact = jsonStoragePhoto.getMyObj();
        Random garbage = new Random(); // Object with pseudo random integer used to corrupt the .json file

        writeGarbage(testFileContact,garbage.nextInt()); // Method to write the pseudo random numbers instead of contact infos

        // Return the error generate by the read of the corrupted file
        e = assertThrows(BusinessException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                jsonStoragePhoto.read();
            }
        });

        assertEquals(ErrorCode.READING_JSON_STORAGE_GALLERY_ERROR.getCode(), e.getErrorCode());
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
     * Method test to deserialize a .json file in an ArrayList of Photo
     * @param tmp
     */
    private ArrayList<Photo> readForTesting(File tmp, ArrayList<Photo> photoArrayList) {
        ObjectMapper mapper = new ObjectMapper();

        // Read JSON file
        try {
            photoArrayList = mapper.readValue(tmp,
                    mapper.getTypeFactory().constructCollectionType(ArrayList.class, Photo.class));
        } catch (Exception e) {
            fail("Failed to deserialize the ArrayListe");
        }
        return photoArrayList;
    }

    /**
     * Method test to serialize an ArrayList of Photo in a .json file
     * @param tmp
     * @param photoArrayList
     */
    private void writeForTesting(File tmp, ArrayList<Photo> photoArrayList) {
        ObjectMapper mapper = new ObjectMapper();
        // Serialize data
        try {
            mapper.writeValue(tmp, photoArrayList);
        } catch(Exception e) {
            fail("Failed to serialize the Arraylist");
        }
    }
}
