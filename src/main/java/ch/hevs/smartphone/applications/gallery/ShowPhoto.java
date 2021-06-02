package ch.hevs.smartphone.applications.gallery;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.gallery.listeners.GalleryActionListener;
import ch.hevs.smartphone.applications.gallery.listeners.GalleryMouseListener;
import ch.hevs.smartphone.applications.gallery.serialisation.JSONStoragePhoto;
import ch.hevs.smartphone.parameters.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ShowPhoto extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    //Panel
    private JPanel pnlCTSP;
    private JPanel pnlNamePhoto;
    private JPanel pnlShowPhoto;
    private JPanel pnlButton;

    // JTextField
    private JTextField tfNamePhoto;

    //Label
    private JLabel lblNamePhoto;
    private JLabel lblContentPhoto;

    //Button
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnCancel;

    //ImageIcon
    private ImageIcon icPhoto;

    //ArrayList
    private ArrayList<Photo> photosArray;

    //String
    private String name = "";
    private String path = "";

    //Other
    private GalleryGUI galleryGUI;
    private JSONStoragePhoto galleryPhoto;
    private GalleryActionListener galleryActionListener;
    private GalleryMouseListener galleryMouseListener;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public ShowPhoto(GalleryGUI galleryGUI, String name, String path) {
        this.galleryGUI = galleryGUI;
        this.name = name;
        this.path = path;
        this.add(buildpnlShowPhoto());
        buildListeners();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************

    /**
     * Création initiale du panel
     */
    private JPanel buildpnlShowPhoto() {
        buildvariables();

        /**Panel nord*/
        pnlNamePhoto = new JPanel();
        lblNamePhoto = new JLabel("Name :");
        tfNamePhoto = new JTextField(this.name);
        tfNamePhoto.setOpaque(false);
        tfNamePhoto.setPreferredSize(new Dimension(180, 30));

        pnlNamePhoto.add(lblNamePhoto);
        pnlNamePhoto.add(tfNamePhoto);

        /**Panel centre*/
        pnlShowPhoto = new JPanel();
        lblContentPhoto = new JLabel();

        //@TODO Faire en sorte de récupérer l'image et de l'afficher dans le JLabel (ImageIcon puis associer au JLabel)
        icPhoto = new ImageIcon(this.path);
        icPhoto = Util.getScaledImageIcon(icPhoto, 250);
        lblContentPhoto.setIcon(icPhoto);
        pnlShowPhoto.add(lblContentPhoto);

        /**Panel sud*/
        pnlButton = new JPanel();
        btnSave = new JButton("Save change");
        btnDelete = new JButton("Delete");
        btnCancel = new JButton("Cancel");

        pnlButton.add(btnSave);
        pnlButton.add(btnDelete);
        pnlButton.add(btnCancel);


        /**Panel qui contient le tout*/
        pnlCTSP = new JPanel(new BorderLayout());
        pnlCTSP.add(pnlNamePhoto, BorderLayout.NORTH);
        pnlCTSP.add(pnlShowPhoto, BorderLayout.CENTER);
        pnlCTSP.add(pnlButton, BorderLayout.SOUTH);
        return pnlCTSP;
    }

    /**
     * Création des variables
     */
    private void buildvariables() {
        try {
            galleryPhoto = new JSONStoragePhoto();
        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        galleryActionListener = new GalleryActionListener(galleryGUI);
        galleryMouseListener = new GalleryMouseListener(galleryGUI);

        photosArray = galleryPhoto.getPhotosArray();
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

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    @Override
    public void setName(String name) {
        this.name = name;
    }
}