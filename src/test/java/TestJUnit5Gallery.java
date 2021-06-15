import ch.hevs.smartphone.applications.contacts.Contact;
import ch.hevs.smartphone.applications.gallery.Photo;
import ch.hevs.smartphone.applications.gallery.serialisation.JSONStoragePhoto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static ch.hevs.smartphone.parameters.utils.TempFile.getTempFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @BeforeAll
    static void init() {
        photo1 = new Photo("path1", "photo1");
        photo2 = new Photo("path2", "photo2");
        photo3 = new Photo("path3", "photo3");
    }

    @Test
    public void testSerializationDeserialization() {
        ArrayList<Photo> photosArray = new ArrayList<>();
        ArrayList<Photo> photosArrayDeserialized = null;

        photosArray.add(photo1);
        photosArray.add(photo2);
        photosArray.add(photo3);

        File tmp = getTempFile(true);

        /*// Serialize data
        writeForTesting(tmp, contactArrayList);

        // Read JSON file
        deserializedContactArrayList =  readForTesting(tmp, deserializedContactArrayList);

        assertEquals(contactArrayList.toString(), deserializedContactArrayList.toString());*/
    }


}
