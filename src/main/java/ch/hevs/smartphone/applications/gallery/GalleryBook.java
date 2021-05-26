package ch.hevs.smartphone.applications.gallery;

import java.io.*;
import java.util.ArrayList;

public class GalleryBook {
    // Array list
    public ArrayList<Photo> tabPhoto = new ArrayList<>();

    // Add photo in GalleryBook
    public void addPhoto(Photo photo){tabPhoto.add(photo);}

    // Get photo
    public GalleryBook(){
        //machin désérialise
        this.get();
    }


    public static final String PATH = "saves/gallery.se";

    /**
     * Save photos, sérialisation
     */
    public void save(){
        try {
            //créer un flux de sortie jusqu'à .se
            FileOutputStream reader = new FileOutputStream(PATH);
            BufferedOutputStream bFile = new BufferedOutputStream (reader);
            ObjectOutputStream obFile = new ObjectOutputStream(bFile);
            obFile.writeObject(this.tabPhoto); //écrire
            obFile.close(); /// fermer
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    /**
     * Récupère les informations, déssérialisation
     */
    protected void get(){
        try {
            if(new File(PATH).exists()){
                this.tabPhoto.clear();
                FileInputStream reader = new FileInputStream(PATH);
                BufferedInputStream bFile = new BufferedInputStream(reader);
                ObjectInputStream obFile = new ObjectInputStream(bFile);

                this.tabPhoto = (ArrayList<Photo>) obFile.readObject();

                obFile.close();

            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
