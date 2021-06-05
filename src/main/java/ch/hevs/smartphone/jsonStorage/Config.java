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
    private String configFilePath;
    private String storePath;
    private static final String VARIABLE_ENVIRONNEMENT = "SMARTPHONEPROJECT";

    //*****************************************************************************
    // C O N S T R U C T E U R - PRIVATE SINGLETON
    //*****************************************************************************
    private Config()
    {
        // A la première création du fichier, le stoquer à cet emplacement.
        storePath = System.getenv(VARIABLE_ENVIRONNEMENT);

        if (storePath == null)
        {
            System.err.println("Aucunne variable d'environnement n'a été trouvé !");
            System.exit(1);
        }

        configFile = new File(storePath);

        // Si le dossier ou l'on va stoquer les JSON n'existe pas, on va le créer
        if(! configFile.isDirectory())
        {
            // Est ce que la création a fonctionné ?
            if(! configFile.mkdir())
            {
                System.err.println("La création du dossier n'a pas fonctionné");
                System.exit(1);

            }
        }

        // Si j'arrive ici, c'est que mon dossier a pu être crée correctement et il EXISTE !


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



    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public String getStorePath()
    {
        return storePath;
    }
}

