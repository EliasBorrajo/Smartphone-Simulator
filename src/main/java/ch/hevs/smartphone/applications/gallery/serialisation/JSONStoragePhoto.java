package ch.hevs.smartphone.applications.gallery.serialisation;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.errors.ErrorCode;
import ch.hevs.smartphone.applications.gallery.Photo;
import ch.hevs.smartphone.jsonStorage.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Contient la galerie
 * Récupère le fichier JSON sur le PC afin de créer nos photos
 */
public class JSONStoragePhoto implements StorablePhoto {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // ARRAY LIST - Ce sera le carnet d'adresse
    private ArrayList<Photo> photosArray = new ArrayList<>();

    // Liste qui permet de lire le JSOn et sera converti ensuite en ArrayList du carnet d'adresse
    private List<Photo> photoList;

    //PATH
    /**
     * Classe qui gère la configuration, s'execute à la création
     * permet de voir si config.TXT existe, si il n'existe pas, le créer,
     * sinon le lire.
     * Config doit RESTER ou il est.
     */
    private String storePath ;
    public String jsonPath;//= (Config.getConfig().getStorePath() + "photosList.json");

    // myObj FILE
    private File myObj ;//= new File(PATH);

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public JSONStoragePhoto() throws IOException, BusinessException
    {
        definePathToStoreData();
        this.read();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************

    /**
     * NE FONCTIONNE PAS COMME JE VEUX - A SUPPRIMER
     */
    private void definePathToStoreData()
    {
        System.out.println("\nstoring PATH 1 is : "+ storePath);
        storePath = Config.getConfig().getStorePath();
        System.out.println("\nstoring PATH 2 is : "+ storePath);

        // va m'écrire le chemin d'accès de manière coherente, et non faire du bricolage
        Path path = Paths.get(storePath , "photosList.json");

        jsonPath = path.toString();
        System.out.println("Final path is : "+ jsonPath);


        myObj = new File(jsonPath);
        System.out.println("REAL REAL PATH OBJECT FILE IS : "+ myObj.getAbsolutePath() );
        System.out.println("REAL REAL PATH OBJECT FILE IS : "+ myObj.getPath() );
    }

    /**
     * SERIALISATION READ DATA IN A JSON FILE
     *
     * @return
     * @throws BusinessException
     * @throws IOException
     */
    @Override
    public ArrayList<Photo> read() throws BusinessException, IOException {

        ObjectMapper mapper = new ObjectMapper();       // Mapper n'aime pas les fichiers vides !!

        try {
            // Verifie que le fichier existe pas & Crée le ficher
            if (!myObj.exists())
            {
                myObj.createNewFile();
                System.out.println("File created: " + myObj.getName());
            }
            // verifie que le ficher n'est pas vide (NULL)
            else if (myObj.length() > 0) {
                photosArray.clear();

                photoList = mapper.readValue(myObj, new TypeReference<List<Photo>>() {
                });

                photosArray = (ArrayList<Photo>) photoList;        // casting de la LISTE en ARRAYLISTE
            } else {
                System.out.println("Empty file");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while READING JSON STORAGE PHOTOS.");
            e.printStackTrace();
        }
        return photosArray;
    }

    /**
     * SERIALISATION WRITE DATA IN A JSON FILE
     *
     * @param destination
     * @param photosArray
     * @throws BusinessException
     */
    @Override
    public void write(File destination, ArrayList<Photo> photosArray) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(destination, photosArray);
        } catch (IOException e) {
            throw new BusinessException("failed to save", e, ErrorCode.IO_ERROR); //@TODO : ERRORCODE n'est pas IO_ERROR mais BUSINESS NON ?
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