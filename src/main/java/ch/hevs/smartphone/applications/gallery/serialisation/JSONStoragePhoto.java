package ch.hevs.smartphone.applications.gallery.serialisation;

import ch.hevs.smartphone.errors.BusinessException;
import ch.hevs.smartphone.errors.ErrorCode;
import ch.hevs.smartphone.applications.gallery.Photo;
import ch.hevs.smartphone.parameters.jsonStorage.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains photos gallery
 * Retrieve the JSON file on the PC in order to create our photos
 * @author Lonfat Milena, Borrajo Elias
 */
public class JSONStoragePhoto implements StorablePhoto {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // ARRAY LIST - It will be our gallery
    private ArrayList<Photo> photosArray = new ArrayList<>();

    // List which allows to read the JSON and will then be converted into an ArrayList of photos
    private List<Photo> photoList;

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
     * constructor
     */
    public JSONStoragePhoto() {
        definePathToStoreData();
        try {
            read();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    //*****************************************************************************
    // M E T H O D E S
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
        Path path = Paths.get(storePath, "photosList.json");

        jsonPath = path.toString();

        myObj = new File(jsonPath);
        System.out.println("REAL REAL PATH OBJECT PHOTO FILE IS : " + myObj.getAbsolutePath());
    }

    /**
     * DE-SERIALIZATION READ DATA IN A JSON FILE
     *
     * If the file is empty, it reads without problem.
     * If the file does not exist, it creates it.
     * If the file is corrupted, it does not read it,
     * and the file will be overwrite during the next serialization when we turn off the smartphone.
     *
     * @return an arrayList of our photos
     * @throws BusinessException
     */
    @Override
    public ArrayList<Photo> read() throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();      // Mapper doesn't like empty file
        try {
            // Check that the file does not exist & Create the file
            if (!myObj.exists()) {
                myObj.createNewFile();
                System.out.println("File created: " + myObj.getName());
            }
            // Check that the file is not empty (NULL)
            else if (myObj.length() > 0) {
                photosArray.clear();

                photoList = mapper.readValue(myObj, new TypeReference<List<Photo>>() { });

                photosArray = (ArrayList<Photo>) photoList;        // casting de la LISTE en ARRAYLISTE
            } else {
                System.out.println("Empty file");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la récuperation de la gallerie d'images." +
                    "                                                   \nUne gallerie d'images vierge a été initialisé" +
                    "                                                   \nPossibles causes : Corruption du fichiers JSON");

            throw new BusinessException("An error occurred while READING JSON STORAGE PHOTOS.", ErrorCode.READING_JSON_STORAGE_GALLERY_ERROR);
        }
        return photosArray;

    }

    /**
     * SERIALIZATION WRITE DATA IN A JSON FILE
     *
     * @param destination
     * @param photosArray
     * @throws IOException
     */
    @Override
    public void write(File destination, ArrayList<Photo> photosArray) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(destination, photosArray);
        } catch (IOException e) {
            System.out.println("SERIALISATION of photoList.JSON has failed : ");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while writing / serializing the gallery." +
                    "                                                 \nThe gallery is not saved, restart the smartphone.");
        }
    }

    /**
     * Add photo in galleryPhoto
     *
     * @param photo
     */
    public void addPhoto(Photo photo) {
        photosArray.add(photo);
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public ArrayList<Photo> getPhotosArray() {
        return photosArray;
    }

    public File getMyObj() {
        return myObj;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    public void setTabPhoto(ArrayList<Photo> tabContact) {
        this.photosArray = tabContact;
    }
}