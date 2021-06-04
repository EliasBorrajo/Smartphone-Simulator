package ch.hevs.smartphone.jsonStorage;
import java.io.*;

/**
 * Classe permetant de lire le fichier d'CONFIG.TXT
 * Permet de récuperer le chemin d'accès d'ou doivent se stoquer les fichers JSON de nos applications
 *
 * CETTE CLASSE EST UN SINGLETON, EXISTE PARTOUT DANS LE PROJET SI ON L'IMPORTE. CET OBJET EST UNIQUE !!
 * On a accès à cette classe de partout dans notre projet, grâce à l'import de la classe.
 * Le concepte de SINGLETON, permet d'éviter de passer des objets par les constructeurs à n'en plus finir,
 * et d'utiliser l'objet à l'endroit ou nous en avons besoin.
 */
public class Config
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    private static Config configSingleton = null;
    private File configFile;
    private String configFilePath = "src/main/java/ch/hevs/smartphone/jsonStorage/config.txt";
    private String storePath;

    //*****************************************************************************
    // C O N S T R U C T E U R - PRIVATE SINGLETON
    //*****************************************************************************
    private Config()
    {
        // A la première création du fichier, le stoquer à cet emplacement.
        configFile = new File(configFilePath);
        configDoesExist(); //Vérifie que le fichier existe, si il n'exoste pas, le crée, sinon le lit

    }

    //*****************************************************************************
    // S I N G L E T O N  -  A C C E S   T O   O B J E C T
    //*****************************************************************************
    /**
     * Si l'objet n'existe pas, on crée l'objet et ainsi on utilise le ocnstructeur.
     * VOIR THEORIE SINGLETON
     */
    public static Config getConfig()
    {
        // verifie que le singleTOn CONFIG a été crée ou non.
        if(configSingleton == null)
        {
            configSingleton = new Config();
        }
        return configSingleton;
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    /**
     * MEthode qui vérifie que le fichier config existe ou pas à l'emplacement demandé
     */
    private void configDoesExist()
    {
        try
        {
            // Si le fichier n'existe pas, le créer et le remplire
            if (configFile.createNewFile())
            {
                //Création du fichier avec son contenu
                System.out.println("File config has been created ! : " + configFile.getName());
                writeConfigFile();

            }
            else
            {
                // le fichier existe déja
                storePath = readConfigFile();

                System.out.println("File config already exist, read it ! : " + configFile.getName());
                System.out.println("Congig file path : "+ storePath);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("An error has occured by verifying the configFile : METHODE configDoesExist() in Config.java");
        }
    }

    /**
     * permet de lire OU l'on veut stoquer le fichier d'après le fichier CONFIG.TXT
     * @return
     */
    private String readConfigFile()
    {
    String line = null;
        try
        {
            BufferedReader br = new BufferedReader( new FileReader(configFilePath));
            line = br.readLine();   //On ne lit que une ligne, le fichier ne contient pas plus de lignes
            System.out.println("READ CONFIG FILE : LINE CONTENT = " +line);
            br.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return line;
    }

    private void writeConfigFile()
    {
        try
        {
            BufferedWriter bw = new BufferedWriter( new FileWriter(configFilePath) );
            bw.write("src/main/java/ch/hevs/smartphone/jsonStorage/");
            bw.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public String getStorePath()
    {
        return storePath;
    }
}

