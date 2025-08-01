package ch.hevs.smartphone.applications.gallery.listeners;

import ch.hevs.smartphone.applications.gallery.GalleryGUI;
import ch.hevs.smartphone.applications.gallery.Photo;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * This class is for every ActionListeners we have in our gallery application
 * The first part concern the main panel of the application
 * The second part concern the ShowPhotoInfo panel
 *
 * @author Lonfat Milena
 */

public class GalleryActionListener implements ActionListener {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Layout
    private GalleryGUI galleryGUI;

    // Int
    private int cpt;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     *
     * @param galleryGUI
     */
    public GalleryActionListener(GalleryGUI galleryGUI) {
        this.galleryGUI = galleryGUI;
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    /**
     * method to set listeners to most of the buttons used in the contact app
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    // First part : ActionListener on main panel of gallery application ************************************************

        // ActionListener on btnAdd : add picture
        if (e.getSource() == galleryGUI.getBtnAddPhoto()) {
            String path = null;
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, GIF & PNG Images", "jpg", "gif", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(chooser.getParent());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File[] fs=chooser.getSelectedFiles();
                for (File f: fs ) {
                    cpt = galleryGUI.getPhotosArray().size();
                    path = f.getPath();
                    String photoNameDef = "photo" + cpt;
                    Photo photo = new Photo(path, photoNameDef);
                    galleryGUI.getJsonPhotoBook().addPhoto(photo);
                }
                rebuildApp();
            }
        }

    // Second part : ActionListener on panel ShowPhotoInfo *************************************************************

        for (int i = 0; i < galleryGUI.getPhotosArray().size(); i++) {

            // ActionListener on each buttons of our gallery : open pnlShowPhoto
            if (e.getSource() == galleryGUI.getBtnPhoto()[i]) {
                // Récupère le bon
                String photoName = galleryGUI.getPhotoName()[i];
                galleryGUI.getCardGallHome().show(galleryGUI, photoName);
            }

            // ActionListener on btnSave : save change of name
            if (e.getSource() == galleryGUI.getPnlShowPhoto()[i].getBtnSave()) {
                galleryGUI.getJsonPhotoBook().getPhotosArray().get(i).setName(galleryGUI.getPnlShowPhoto()[i].getTfNamePhoto().getText());
                rebuildApp();
            }

            // ActionListener on btnCancel : cancel change
            if (e.getSource() == galleryGUI.getPnlShowPhoto()[i].getBtnCancel()) {
                galleryGUI.getCardGallHome().show(galleryGUI, "HomeGallery");
            }

            // ActionListener on btnDelete : delete photo
            if (e.getSource() == galleryGUI.getPnlShowPhoto()[i].getBtnDelete()) {
                galleryGUI.getJsonPhotoBook().getPhotosArray().remove(i);
                rebuildApp();
                break;
            }
        }
    }

    /**
     * This method is to rebuild our application
     */
    public void rebuildApp() {
        galleryGUI.removeAll();
        galleryGUI.validate();
        galleryGUI.buildPnlHomeGall();
        galleryGUI.buildCardLayout();
        galleryGUI.buildListeners();
        galleryGUI.revalidate();
        galleryGUI.repaint();
    }
}