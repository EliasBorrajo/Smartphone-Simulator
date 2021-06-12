package ch.hevs.smartphone.applications.gallery.serialisation;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
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
 * @author Milena, Elias
 * Contient la galerie de photos.
 * Récupère le fichier JSON sur le PC à l'endroit ou l'utilisateur a défini afin de créer nos photos
 */
public class JSONStoragePhoto implements StorablePhoto
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // ARRAY LIST - Ce sera le carnet d'adresse
    private ArrayList<Photo> photosArray = new ArrayList<>();

    // Liste qui permet de lire le JSON et sera converti ensuite en ArrayList du carnet d'adresse
    private List<Photo> photoList;

    //PATH
    private String storePath;        // Permet de stoquer le contenu de notre VARIABLE D'ENVIRONNEMENT SYSTEME
    private String jsonPath;         // Est la variable qui contiendra le chemin FINAl sur le PC et selon l'OS,
                                     // à l'emplacement de stockage de notre fichier JSON
    // myObj FILE
    private File myObj;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public JSONStoragePhoto()
    {
        definePathToStoreData();
        read();
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
        // Récupère le CONTENU de la VARIABLE D'ENVIRONNEMENT
        storePath = Config.getConfig().getStorePath();


        // Va m'écrire le chemin d'accès de manière coherente grâce à PATH & PATHS, et non faire du bricolage
        // COncatène correctement mon PATH qui sera stoqué dans la STRING
        Path path = Paths.get(storePath, "photosList.json");

        jsonPath = path.toString();
        //System.out.println("Final path storing my JSON file is : " + jsonPath);

        myObj = new File(jsonPath);
        System.out.println("REAL REAL PATH OBJECT PHOTO FILE IS : " + myObj.getAbsolutePath());
    }

    /**
     * DE-SERIALISATION READ DATA IN A JSON FILE
     * @return
     * @throws BusinessException
     * @throws IOException
     */
    @Override
    public ArrayList<Photo> read()
    {
        ObjectMapper mapper = new ObjectMapper();       // Mapper n'aime pas les fichiers vides !!

        try
        {
            // Verifie que le fichier existe pas & Crée le ficher
            if (!myObj.exists())
            {
                myObj.createNewFile();
                System.out.println("File created: " + myObj.getName());
            }
            // verifie que le ficher n'est pas vide (NULL)
            else if (myObj.length() > 0)
            {
                photosArray.clear();

                photoList = mapper.readValue(myObj, new TypeReference<List<Photo>>()  { });

                photosArray = (ArrayList<Photo>) photoList;        // casting de la LISTE en ARRAYLISTE
            } else
            {
                System.out.println("Empty file");
            }
        } catch (IOException e)
        {
            System.out.println("An error occurred while READING JSON STORAGE PHOTOS.");
            System.out.println("Le fichier est corrompu");
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Une erreur est survenue lors de la récuperation de la gallerie d'images." +
                    "                                                 \nUne gallerie d'images vierge a été initialisé" +
                    "                                                 \nPossibles causes : Corruption du fichiers JSON");
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
    public void write(File destination, ArrayList<Photo> photosArray)
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            mapper.writeValue(destination, photosArray);
        } catch (IOException e)
        {
            System.out.println("SERIALISATION of photoList.JSON has failed : ");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Une erreur est survenue lors de l'écriture / SERIALISATION de la gallerie d'images." +
                    "                                                 \nLa gallerie d'images n'est pas enregistré, re-démarrer le smartphone.");
        }
    }

    /**
     * Add photo in galleryPhoto
     *
     * @param photo
     */
    public void addPhoto(Photo photo)
    {
        photosArray.add(photo);
    }


    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public ArrayList<Photo> getPhotosArray()
    {
        return photosArray;
    }

    public File getmyObj()
    {
        return myObj;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    public void setTabPhoto(ArrayList<Photo> tabContact)
    {
        this.photosArray = tabContact;
    }
}