package ch.hevs.smartphone.applications.gallery;

import ch.hevs.smartphone.applications.gallery.listeners.GalleryActionListener;
import ch.hevs.smartphone.applications.gallery.listeners.GalleryMouseListener;
import ch.hevs.smartphone.parameters.utils.Util;

import javax.swing.*;
import java.awt.*;

/**
 * @author Lonfat Milena
 * La classe ShowPhoto est le deuxième panel du card de l'application galerie.
 * Affiche la miniature et le nom de la photo séléctionner dans la galerie.
 * Il permet de changer le nom ou supprimer la photo en question.
 * Le deuxième accès se fait depuis un contact pour lui attribuer une photo.
 * Les boutons sont visibles ou non suivant l'accès pris (galerie ou contact).
 * Accès à la class des actionListeners.
 */

public class ShowPhoto extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Panel
    private JPanel pnlNamePhoto;
    private JPanel pnlShowPhoto;
    private JPanel pnlButton;

    // Layout
    private GalleryGUI galleryGUI;

    // TextField
    private JTextField tfNamePhoto;

    // Label
    private JLabel lblNamePhoto;
    private JLabel lblContentPhoto;

    // Button
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnCancel;
    private JButton btnSelect; // Permet de choisir la photo en tant qu'image de profil du contact

    // ImageIcon
    private ImageIcon icPhoto;

    // String
    private String name = "";
    private String path = "";

    // ActionListener
    private GalleryActionListener galleryActionListener;

    // MouseListener
    private GalleryMouseListener galleryMouseListener;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    /**
     * Constructeur
     *
     * @param galleryGUI
     * @param name
     * @param path
     */
    public ShowPhoto(GalleryGUI galleryGUI, String name, String path) {
        this.galleryGUI = galleryGUI;
        this.name = name;
        this.path = path;
        buildpnlShowPhoto();
        buildListeners();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    /**
     * Création initiale du panel
     */
    private void buildpnlShowPhoto() {
        buildvariables();

        // Panel nord
        pnlNamePhoto = new JPanel();
        lblNamePhoto = new JLabel("Name :");
        tfNamePhoto = new JTextField(this.name);
        tfNamePhoto.setOpaque(false);
        tfNamePhoto.setPreferredSize(new Dimension(180, 30));

        pnlNamePhoto.add(lblNamePhoto);
        pnlNamePhoto.add(tfNamePhoto);

        // Panel centre
        pnlShowPhoto = new JPanel();
        lblContentPhoto = new JLabel();

        icPhoto = new ImageIcon(this.path);
        icPhoto = Util.getScaledImageIcon(icPhoto, 250);
        lblContentPhoto.setIcon(icPhoto);
        pnlShowPhoto.add(lblContentPhoto);

        // Panel sud
        pnlButton = new JPanel();
        btnSave = new JButton("Save change");
        btnDelete = new JButton("Delete");
        btnCancel = new JButton("Cancel");
        btnSelect = new JButton("Select image as contact image");
        btnSelect.setVisible(false); // On n'affichera ce boutton que depuis l'app CONTACTES

        pnlButton.add(btnSave);
        pnlButton.add(btnDelete);
        pnlButton.add(btnCancel);
        pnlButton.add(btnSelect);

        // Panel qui contient le tout
        this.add(pnlNamePhoto);
        this.add(pnlShowPhoto);
        this.add(pnlButton);
    }

    /**
     * Création des variables
     */
    private void buildvariables() {
        galleryActionListener = new GalleryActionListener(galleryGUI);
        galleryMouseListener = new GalleryMouseListener(galleryGUI);
    }

    /**
     * Listeners du bouton ajouter
     * On construit dans une autre méthode pour avoir accès après la création de TOUS les composants
     */
    public void buildListeners() {
        btnSave.addActionListener(galleryActionListener);
        btnDelete.addActionListener(galleryActionListener);
        btnCancel.addActionListener(galleryActionListener);
        tfNamePhoto.addMouseListener(galleryMouseListener);
    }

    /**
     * Permet de afficher les boutons que l'on a pas besoin depuis l'application gallery
     */
    public void showNormalBtn() {
        btnSave.setVisible(true);
        btnDelete.setVisible(true);
        galleryGUI.getBtnAddPhoto().setVisible(true);
        btnSelect.setVisible(false);
    }

    /**
     * Permet d'afficher les bouttons que l'on a besoin depuis l'application contact
     */
    public void showSelectImageBtn() {
        btnSave.setVisible(false);
        btnDelete.setVisible(false);
        galleryGUI.getBtnAddPhoto().setVisible(false);
        btnSelect.setVisible(true);
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public JTextField getTfNamePhoto() {
        return tfNamePhoto;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public JButton getBtnSelect() {
        return btnSelect;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    @Override
    public void setName(String name) {
        this.name = name;
    }
}