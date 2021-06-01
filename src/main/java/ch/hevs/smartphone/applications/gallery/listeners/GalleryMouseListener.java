package ch.hevs.smartphone.applications.gallery.listeners;

import ch.hevs.smartphone.applications.gallery.GalleryGUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GalleryMouseListener implements MouseListener {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    //Panel
    GalleryGUI galleryGUI;

    //Boolean
    private boolean activate;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public GalleryMouseListener(GalleryGUI galleryGUI) {
        this.galleryGUI = galleryGUI;
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i = 0; i < galleryGUI.getPhotosArray().size(); i++) {
            if(activate == false) {
                galleryGUI.getPnlShowPhoto()[i].getTfNamePhoto().setText("");
            }
            activate = true;
            galleryGUI.getPnlShowPhoto()[i].getTfNamePhoto().setForeground(Color.black);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}