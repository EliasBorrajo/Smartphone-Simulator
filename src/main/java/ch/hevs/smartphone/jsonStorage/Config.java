package ch.hevs.smartphone.jsonStorage;
import java.io.*;

/**
 * ! ! !   A T T E N T I O N   ! ! !
 * IL EST ABSOLUMENT, NECESSAIRE, DE CREER UNE VARIABLE D'ENVIRONNEMENT SUR LE PC,
 * CONTENANT LE CHEMIN D'ACCES DE OU NOUS VOULONS STOQUER LES FICHIERS QUE CREE NOTRE APPLICATION !!
 * SI LA VARIABLE N'EST PAS CREE, LE PROGRAMME NE PEUT ABSOLUMENT PAS DEMARER !
 *
 * @author Elias
 * - B U T
 * Classe permettant de récuperer le contenu de la VARIABLE D'ENVIRONNEMENT crée par l'utilisateur,
 * afin de décider OU seront stoquées ses informations personelles. EX : FICHIERS JSON.
 *
 *
 * - S I N G L E T O N
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
    private static Config configSingleton = null;   // nécessaire d'être déclaré ici à null pour la première & unique création de l'objet
    private File storeFile;        // Est le dossier que l'on crée, à l'emplacement de la VARIABLE_ENVIRONNEMENT.
                                   // Tous les fichiers personnels en format JSON seront stoquées dans ce dossier
    private String configFilePath;
    private String storePath;      // Est la valeur conetnue dans la variable d'environnement. On la retourne aux getters.
    private static final String VARIABLE_ENVIRONNEMENT = "SMARTPHONEPROJECT";
                         // Contient le NOM de la VARIABLE D'ENVIRONNEMENT.
                         // Ainsi la methode : "System.getenv(VARIABLE_ENVIRONNEMENT)" saura QUELLE VALEUR de VARIABLE aller chercher.


    //*****************************************************************************
    // C O N S T R U C T E U R - PRIVATE SINGLETON
    //*****************************************************************************
    private Config()
    {
        // Recuperer le CONTENU de la VARIABLE D'ENVIRONNEMENT sur le PC.
        storePath = System.getenv(VARIABLE_ENVIRONNEMENT);
        if (storePath == null)
        {
            System.err.println("Aucunne variable d'environnement n'a été trouvé !");
            System.exit(1); // On quitte BRUTALEMENT l'application.
        }

        // A la première création du fichier, le stoquer à cet emplacement.
        // Ce fichier sera un dossier contenant nos DATAS (tel queles JSON) crées par le projet.
        storeFile = new File(storePath);


        // Test pour savoir si le FILE que l'on a crée est un DOSSIER ou non.
        // Si le dossier ou l'on va stoquer les JSON n'existe pas, on va le créer.
        if(! storeFile.isDirectory())
        {
            // Est ce que la création du DOSSIER a fonctionné ?
            if(! storeFile.mkdir())
            {
                System.err.println("La création du dossier n'a pas fonctionné");
                System.exit(1); // On quitte BRUTALEMENT l'application.
            }
        }

        // Si j'arrive ici, c'est que mon dossier a pu être crée correctement et il EXISTE !
        System.out.println("DIRECTORY NAME AS : "+ storeFile.getName()
                            +"\n at location : "+storeFile.getAbsolutePath());

    }

    //*****************************************************************************
    // S I N G L E T O N  -  A C C E S   T O   O B J E C T
    //*****************************************************************************
    /**
     * Si l'objet n'existe pas, on crée l'objet et ainsi on utilise le constructeur.
     * VOIR THEORIE SINGLETON
     */
    public static Config getConfig()
    {
        // verifie que le singleTOn CONFIG a été crée ou non. Ne sera crée qu'une seule et unique fois.
        if(configSingleton == null)
        {
            configSingleton = new Config();
        }
        return configSingleton;
    }


    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public String getStorePath()
    {
        return storePath;
    }
}

