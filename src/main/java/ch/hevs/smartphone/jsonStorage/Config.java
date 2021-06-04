package ch.hevs.smartphone.jsonStorage;


import java.io.*;

/**
 * Classe permetant de lire le fichier d'INIT CONFIG
 * Permet de récuperer le chemin d'accès d'ou doivent se stoquer les fichers JSON
 *
 * CETTE CLASSE EST UN SINGLETON, EXISTE PARTOUT DANS LE PROJET
 * On a accès à cette classe de partout dans notre projet, grâce à l'import de la classe.
 */
public class Config
{
    private static Config configSingleton = null;
    private File config;
    private String storePath;

    private Config()
    {
        configDoesExist(); //Vérifie que le fichier existe, si il n'exoste pas, le crée, sinon le lit
        //this.storePath = readConfigFile();
    }

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
            }
            else
            {
                // Existe déja
                readConfigFile();

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

    public String getStorePath()
    {
        return storePath;
    }
}

