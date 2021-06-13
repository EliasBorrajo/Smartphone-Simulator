package ch.hevs.smartphone.applications.gallery.listeners;

import ch.hevs.smartphone.applications.gallery.GalleryGUI;
import ch.hevs.smartphone.applications.gallery.Photo;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Lonfat Milena
 * Classe pour gérer tous les listeners qui concernent l'application galerie
 */

public class GalleryActionListener implements ActionListener {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Layout
    GalleryGUI galleryGUI;

    // Int
    private int cpt;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    /**
     * Constructeur
     *
     * @param galleryGUI
     */
    public GalleryActionListener(GalleryGUI galleryGUI) {
        this.galleryGUI = galleryGUI;
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    public void actionPerformed(ActionEvent e) {

        // Action de btnAdd pour l'ajout d'une photo
        if (e.getSource() == galleryGUI.getBtnAddPhoto()) {
            String path = null;
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, GIF & PNG Images", "jpg", "gif", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(chooser);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                cpt = galleryGUI.getPhotosArray().size();
                path = chooser.getSelectedFile().getPath();
                String photoNameDef = "photo" + cpt;
                Photo photo = new Photo(path, photoNameDef);
                galleryGUI.getJsonPhotoBook().addPhoto(photo);

                rebuildApp();
            }
        }

        for (int i = 0; i < galleryGUI.getPhotosArray().size(); i++) {

            // Action sur chaque bouton des photos de la gallerie ouvre pnlShowPhoto
            if (e.getSource() == galleryGUI.getBtnPhoto()[i]) {
                // Récupère le bon
                String photoName = galleryGUI.getPhotoName()[i];
                galleryGUI.getCardGallHome().show(galleryGUI, photoName);
            }

            // Action de btnSave de pnlShowPhoto pour sauvagarder le changement de nom de la photo
            if (e.getSource() == galleryGUI.getPnlShowPhoto()[i].getBtnSave()) {
                galleryGUI.getJsonPhotoBook().getPhotosArray().get(i).setName(galleryGUI.getPnlShowPhoto()[i].getTfNamePhoto().getText());
                rebuildApp();
            }

            //Action de btnCancel de pnlShowPhoto pour annuler les changements
            if (e.getSource() == galleryGUI.getPnlShowPhoto()[i].getBtnCancel()) {
                galleryGUI.getCardGallHome().show(galleryGUI, "HomeGallery");
            }

            //Action de btnDelete de pnlShowPhoto pour supprimer la photo
            if (e.getSource() == galleryGUI.getPnlShowPhoto()[i].getBtnDelete()) {
                galleryGUI.getJsonPhotoBook().getPhotosArray().remove(i);
                rebuildApp();
            }
        }
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    /**
     * Méthode qui permet de reconstruire l'application
     */
    private void rebuildApp() {
        galleryGUI.removeAll();
        galleryGUI.validate();
        galleryGUI.buildPnlHomeGall();
        galleryGUI.buildCardLayout();
        galleryGUI.buildListeners();
        galleryGUI.revalidate();
        galleryGUI.repaint();
    }
}