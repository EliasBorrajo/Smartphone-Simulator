package ch.hevs.smartphone.applications.gallery.listeners;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.gallery.GalleryGUI;
import ch.hevs.smartphone.applications.gallery.Photo;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GalleryActionListener implements ActionListener {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    //Panel
    GalleryGUI galleryGUI;

    //Int
    private int cpt;
    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public GalleryActionListener(GalleryGUI galleryGUI){
        this.galleryGUI = galleryGUI;
    }
    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    public void actionPerformed(ActionEvent e) {

        // Action pour l'ajout d'une photo
        if(e.getSource() == galleryGUI.getBtnAddPhoto()){
            String path = null;
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, GIF & PNG Images", "jpg", "gif", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(chooser);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                cpt = galleryGUI.getPhotosArray().size();
                path = chooser.getSelectedFile().getPath();
                String photoNameDef = "photo"+cpt;
                Photo photo = new Photo(path, photoNameDef);
                galleryGUI.getJsonPhotoBook().addPhoto(photo);
                try {
                    // SERIALISATION
                    galleryGUI.getJsonPhotoBook().write(galleryGUI.getJsonPhotoBook().getmyObj(),
                            galleryGUI.getJsonPhotoBook().getPhotosArray());
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                    System.out.println("COULD NOT SAVE PHOTO -- INNER CLASS CONTENTLAYOUT LISTENERS");
                }
                rebuildApp();
            }
        }

        for(int i = 0; i < galleryGUI.getPhotosArray().size(); i++){
            // Action pour montrer les infos de la photo en question
            if(e.getSource() == galleryGUI.getBtnPhoto()[i]){  //On met le i pour savoir quel btn c'est
                // Récupère le bon
                String photoName = galleryGUI.getPhotoName()[i];
                galleryGUI.getCardGallHome().show(galleryGUI, photoName);
            }

            // Action pour modifier le nom de la photo
            if(e.getSource() == galleryGUI.getPnlShowPhoto()[i].getBtnSave()){
                galleryGUI.getJsonPhotoBook().getPhotosArray().get(i).setName(galleryGUI.getPnlShowPhoto()[i].getTfNamePhoto().getText());
                try {
                    // Sérialisation
                    galleryGUI.getJsonPhotoBook().write(galleryGUI.getJsonPhotoBook().getmyObj(), galleryGUI.getJsonPhotoBook().getPhotosArray());
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                    System.out.println("Rename contact error");
                }
                rebuildApp();
            }

            //Action pour annuler les changements
            if(e.getSource() == galleryGUI.getPnlShowPhoto()[i].getBtnCancel()){
                rebuildApp();
            }
            //@TODO Donner la possibilité de changer de photo depuis le panel show photo?
            //Action pour supprimer la photo
            if(e.getSource() == galleryGUI.getPnlShowPhoto()[i].getBtnDelete()){
                galleryGUI.getJsonPhotoBook().getPhotosArray().remove(i);
                try {
                    // SERIALISATION
                    galleryGUI.getJsonPhotoBook().write(galleryGUI.getJsonPhotoBook().getmyObj(),
                            galleryGUI.getJsonPhotoBook().getPhotosArray());
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                    System.out.println("Deleting contact error");
                }
                rebuildApp();
            }
        }
    }
    /**
     * Méthode qui permet de reconstruire l'application
     * */
    private void rebuildApp(){
        galleryGUI.removeAll();
        galleryGUI.validate();
        galleryGUI.buildPnlHomeGall();
        galleryGUI.buildCardLayout();
        galleryGUI.buildListeners();
        galleryGUI.revalidate();
        galleryGUI.repaint();
    }
}