package ch.hevs.smartphone.Gallery;

import ch.hevs.smartphone.Bases.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GalleryGUI extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // PANEL
    private JPanel pnlHomeGallery;
    private JPanel pnlNorth;
    private JPanel pnlCentre;

    // SCROLLPANE
    private JScrollPane scrollPaneGallery;

    // LABEL
    private JLabel lblTitle;

    // BUTTONS
    private MyButton btnAddGallery;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public GalleryGUI(){
        add(buildpnlContentGallery());
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private JPanel buildpnlContentGallery(){

        pnlHomeGallery = new JPanel(new BorderLayout());

        pnlNorth = new JPanel();
        pnlCentre = new JPanel();
        lblTitle = new JLabel("Gallery");
        scrollPaneGallery = new JScrollPane();
        btnAddGallery = new MyButton("+");

        pnlCentre.add(scrollPaneGallery);
        pnlNorth.add(lblTitle);
        pnlNorth.add(btnAddGallery);
        pnlHomeGallery.add(pnlNorth,BorderLayout.NORTH);
        pnlHomeGallery.add(pnlCentre,BorderLayout.CENTER);

        return pnlHomeGallery;
    }
    //Méthode pour aller chercher les images sur l'ordi
    //Array de photo ?
    //historiser a chaque action
    //Enregistrer
    //Modifirer
    //supprimer
    //Importer photos dans contacts
    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************

    public MyButton getBtnAddGallery() {
        return btnAddGallery;
    }
}