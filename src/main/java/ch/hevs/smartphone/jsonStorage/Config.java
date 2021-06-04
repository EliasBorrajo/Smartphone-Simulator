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
    private File config;
    private String storePath;

    //*****************************************************************************
    // C O N S T R U C T E U R - PRIVATE SINGLETON
    //*****************************************************************************
    private Config()
    {
        config = new File("src/main/java/ch/hevs/smartphone/jsonStorage/config.txt");
        configDoesExist(); //Vérifie que le fichier existe, si il n'exoste pas, le crée, sinon le lit
        //this.storePath = readConfigFile();
    }

    //*****************************************************************************
    // S I N G L E T O N  -  A C C E S   T O   O B J E C T
    //*****************************************************************************
    /**
     * Si l'objet n'existe pas, on crée l'objet
     * VOIR THEORIE SINGLETON
     */
    public static Config getConfig()
    {
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
            if (config.createNewFile())
            {
                //Création
                System.out.println("File config has been created ! : " + config.getName());
                writeConfigFile();

            }
            else
            {
                // Existe déja
                storePath = readConfigFile();
                System.out.println("File config already exist, read it ! : " + config.getName());


            }
        } catch (IOException e)
        {
            e.printStackTrace();
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
            BufferedReader br = new BufferedReader( new FileReader("src/main/java/ch/hevs/smartphone/jsonStorage/config.txt"));
            line = br.readLine();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
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
            BufferedWriter bw = new BufferedWriter( new FileWriter(config) );
            bw.write("src/main/java/ch/hevs/smartphone/jsonStorage/");

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getStorePath()
    {
        return storePath;
    }
}

