package ch.hevs.smartphone.parameters.jsonStorage;

import javax.swing.*;
import java.io.*;

/**
 * - WARNING
 * It is absolutely necessary to create an environment variable on the PC,
 * containing the path to where we want to store the files that our app creates.
 * If the variable is not created, the program cannot start.
 * - GOAL
 * The class makes it possible to retrieve the content of the environment variable created by the user,
 * in order to decide where his personal information will be stored. Ex: JSON file
 * - S I N G L E T O N
 * This class is a singleton, it will exist everywhere in the project if you import it. This object is unique.
 * We have access to this class from everywhere in our project, thanks to the import of the class.
 * The concept of singleton, makes it possible to avoid passing objects through constructors in too large quantities,
 * and to use the object where we need it.
 *
 * @author Borrajo Elias
 */

public class Config {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    private static Config configSingleton = null;   // Must be declared here to null for the first & only creation of the object
    private File storeFile;                         // Is the folder that we create, at the location of the VARIABLE_ENVIRONNEMENT
                                                    // All personal files in JSON format will be stored in this folder
    private String configFilePath;
    private String storePath;                       // Is the value contained in the environment variable. We return it to the getters.
    private static final String VARIABLE_ENVIRONNEMENT = "HOME";
    // Contains the NAME of the ENVIRONMENT VARIABLE.
    // The method: "System.getenv (VARIABLE_ENVIRONNEMENT)" will know WHICH VALUE of VARIABLE to look for.

    //*****************************************************************************
    // C O N S T R U C T O R - PRIVATE SINGLETON
    //*****************************************************************************
    private Config() {
        // Retrieve the contents of the environment variable on the PC.
        storePath = System.getenv(VARIABLE_ENVIRONNEMENT);
        if (storePath == null) {
            System.err.println("Aucunne variable d'environnement n'a été trouvé !");
            JOptionPane.showMessageDialog(null,"Aucunne variable d'environnement n'a été trouvé !" +
                                                                    "\nIl est nécessaire d'avoir une variable d'environnement sur le PC se nommant ''SMARTPHONEPROJECT'' " +
                                                                    "\navec en contenu de la variable, le chemin d'accès à l'emplacement du dossier ou l'on veut stocker les données personnelles" +
                                                                    "\n\nCe dossier contient les fichiers JSON génerées par l'application");
            System.exit(1); // We BRUTALLY quit the app.
        }

        // When creating the file for the first time, store it in this location.
        // This file will be a folder containing our data (such as JSON) created by the project.
        storeFile = new File(storePath);

        // Test to know if the File that we created is a folder or not.
        // If the folder where we are going to store the JSON does not exist, we will create it.
        if (!storeFile.isDirectory()) {
            // Did the creation of the folder work?
            if (!storeFile.mkdir()) {
                System.err.println("La création du dossier n'a pas fonctionné");
                System.exit(1); // We BRUTALLY quit the app.
            }
        }

        // If I arrive here, it is because my file could be created correctly and it EXISTS!
        System.out.println("DIRECTORY NAME AS : " + storeFile.getName()
                + "\n at location : " + storeFile.getAbsolutePath());
    }

    //*****************************************************************************
    // S I N G L E T O N  -  A C C E S   T O   O B J E C T
    //*****************************************************************************
    /**
     * If the object does not exist, we create the object and so we use the constructor.
     *
     * @return Config Object configSingleton
     */
    public static Config getConfig() {
        // Verifies that configSingleton has been created or not. Will only be created once.
        if (configSingleton == null) {
            configSingleton = new Config();
        }
        return configSingleton;
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public String getStorePath() {
        return storePath;
    }
}

