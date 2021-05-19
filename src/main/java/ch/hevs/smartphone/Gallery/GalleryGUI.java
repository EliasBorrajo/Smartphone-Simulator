package ch.hevs.smartphone.Gallery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GalleryGUI extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // LAYOUT
    private CardLayout cardGallery;

    // PANEL
    private JPanel pnlNorth;
    private JPanel pnlCentre;
    private JPanel pnlCard;
    private JPanel pnlHomeGallery;
    private AddPhotoGallery pnlAddPhoto;
    // SCROLLPANE
    private JScrollPane scrollPaneGallery;

    // LABEL
    private JLabel lblTitle;

    // BUTTONS
    private JButton btnAdd;
    private JButton btnRetour;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public GalleryGUI(){
        cardGallery = new CardLayout();
        pnlCard = this;      //contient le card

        pnlHomeGallery = new JPanel(new BorderLayout());

        pnlNorth = new JPanel();
        pnlCentre = new JPanel();
        lblTitle = new JLabel("Gallery");
        btnAdd = new JButton("+");
        scrollPaneGallery = new JScrollPane();

        pnlAddPhoto = new AddPhotoGallery();
        btnRetour = new JButton("Retour");
        pnlAddPhoto.add(btnRetour,BorderLayout.SOUTH);

        pnlCentre.add(scrollPaneGallery);
        pnlNorth.add(lblTitle);
        pnlNorth.add(btnAdd);
        pnlHomeGallery.add(pnlNorth,BorderLayout.NORTH);
        pnlHomeGallery.add(pnlCentre,BorderLayout.CENTER);

        this.setLayout(cardGallery);
        this.add("GalleryHome", pnlHomeGallery);
        this.add("addPhoto", pnlAddPhoto);

        cardGallery.show(this,"GalleryHome");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardGallery.show(pnlCard,"addPhoto");
            }
        });

        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardGallery.show(pnlCard, "GalleryHome");
            }
        });
    }

    //Méthode pour aller chercher les images sur l'ordi
    //Array de photo ?
    //historiser a chaque action
    //Enregistrer
    //Modifirer
    //supprimer
    //Importer photos dans contacts
}