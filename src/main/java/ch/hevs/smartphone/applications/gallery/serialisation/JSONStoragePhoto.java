package ch.hevs.smartphone.applications.gallery.serialisation;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.errors.ErrorCode;
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
 * @author Lonfat Milena, Borrajo Elias
 * Contains photos gallery
 * Retrieve the JSON file on the PC in order to create our photos
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
     * Permet de récuperer la valeur stoqué sur le PC de l'utilisateur de l'app.
     * L'utilisateur va créer une VARIABLE D'ENVIRONNEMENT sur son OS / PC, pour décider à quel emplacement
     * il shouaite stoquer les fichiers JSON.
     * <p>
     * 1) Il faut récuperer cette VARIABLE grâce à notre SINGLETON
     * <p>
     * 2) Utiliser la classe PATH pour créer un chemin d'accès correcte peu importe l'OS.
     * Cette classe utilise l'import java.nio, qui va grandement nous aider pour homogeneiser notre code.
     * <p>
     * Puis utiliser la clate PATHS pour CONCATENER le chemin d'accès de la VAARIABLE + le nom du fichier que l'on veut.
     * <p>
     * 3) Définir dans une string le chemin d'accès finale crée par PATH, et l'utiliser pour la création de notre FILE.
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
     * DE-SERIALISATION READ DATA IN A JSON FILE
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

            throw new BusinessException("An error occurred while READING JSON STORAGE PHOTOS.", ErrorCode.READING_JSON_STORAGE_ERROR);

         }
        return photosArray;

    }

    /**
     * SERIALISATION WRITE DATA IN A JSON FILE
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

    public File getmyObj() {
        return myObj;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    public void setTabPhoto(ArrayList<Photo> tabContact) {
        this.photosArray = tabContact;
    }
}