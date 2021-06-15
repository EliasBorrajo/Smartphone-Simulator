package ch.hevs.smartphone.applications.gallery;

import ch.hevs.smartphone.applications.gallery.listeners.GalleryActionListener;
import ch.hevs.smartphone.parameters.utils.Util;

import javax.swing.*;
import java.awt.*;

/**
 * @author Lonfat Milena
 * This class is the GUI that show the information of a photo
 */

public class ShowPhotoInfo extends JPanel {
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
    private JButton btnSelect; // Allow to choose the photo as the contact's profile image

    // ImageIcon
    private ImageIcon icPhoto;

    // String
    private String name = "";
    private String path = "";

    // ActionListener
    private GalleryActionListener galleryActionListener;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     *
     * @param galleryGUI
     * @param name
     * @param path
     */
    public ShowPhotoInfo(GalleryGUI galleryGUI, String name, String path) {
        this.galleryGUI = galleryGUI;
        this.name = name;
        this.path = path;
        buildPnlShowPhoto();
        buildListeners();
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    /**
     * buildPnlShowPhoto : create all the panels and their contents
     */
    private void buildPnlShowPhoto() {
        buildVariables();

        // North panel
        pnlNamePhoto = new JPanel();
        lblNamePhoto = new JLabel("Name :");
        tfNamePhoto = new JTextField(this.name);
        tfNamePhoto.setOpaque(false);
        tfNamePhoto.setPreferredSize(new Dimension(180, 30));

        pnlNamePhoto.add(lblNamePhoto);
        pnlNamePhoto.add(tfNamePhoto);

        // Center panel
        pnlShowPhoto = new JPanel();
        lblContentPhoto = new JLabel();

        icPhoto = new ImageIcon(this.path);
        icPhoto = Util.getScaledImageIcon(icPhoto, 250);
        lblContentPhoto.setIcon(icPhoto);
        pnlShowPhoto.add(lblContentPhoto);

        // South panel
        pnlButton = new JPanel();
        btnSave = new JButton("Save change");
        btnDelete = new JButton("Delete");
        btnCancel = new JButton("Cancel");
        btnSelect = new JButton("Select image");
        btnSelect.setVisible(false); // On n'affichera ce boutton que depuis l'app CONTACTES

        pnlButton.add(btnSave);
        pnlButton.add(btnDelete);
        pnlButton.add(btnCancel);
        pnlButton.add(btnSelect);

        // Contains the three panels
        this.add(pnlNamePhoto);
        this.add(pnlShowPhoto);
        this.add(pnlButton);
    }

    /**
     * Access to the class of listener
     */
    private void buildVariables() {
        galleryActionListener = new GalleryActionListener(galleryGUI);
    }

    /**
     * Add the actionListener to buttons
     */
    public void buildListeners() {
        btnSave.addActionListener(galleryActionListener);
        btnDelete.addActionListener(galleryActionListener);
        btnCancel.addActionListener(galleryActionListener);
    }

    /**
     * Displays buttons that you don't need from the gallery application
     */
    public void showNormalBtn() {
        btnSave.setVisible(true);
        btnDelete.setVisible(true);
        btnSelect.setVisible(false);
    }

    /**
     * Displays the buttons you need from the contact application
     */
    public void showSelectImageBtn() {
        btnSave.setVisible(false);
        btnDelete.setVisible(false);
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