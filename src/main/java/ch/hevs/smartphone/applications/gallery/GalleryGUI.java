package ch.hevs.smartphone.applications.gallery;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.gallery.listeners.GalleryActionListener;
import ch.hevs.smartphone.applications.gallery.serialisation.JSONStoragePhoto;
import ch.hevs.smartphone.parameters.button.ButtonIcon;
import ch.hevs.smartphone.parameters.button.myButton;
import ch.hevs.smartphone.parameters.utils.Util;
import ch.hevs.smartphone.structure.layout.ContentLayout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Lonfat Milena
 * Panel principale de l'application galerie de type cardLayout
 * Contrôle du fichier JSON et création si manquant
 * Création de l'affichage de la galerie
 * Accès à la class des actionListeners
 * Permet d'ajouter des photos à notre galerie
 */

public class GalleryGUI extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Panel
    private JPanel pnlCTGH;
    private JPanel pnlGallHome;
    private JPanel pnlImages;
    private ShowPhoto[] pnlShowPhoto;

    // Layout
    private ContentLayout contentLayout;
    private CardLayout cardGallHome;

    // JScrollPane
    private JScrollPane jsGallHome;

    // Label
    private JLabel lblGallery;
    private JLabel lblmsg;

    // Button
    private myButton btnAddPhoto;
    private ButtonIcon[] btnPhoto;

    // ImageIcon
    private ImageIcon ic;

    // ArrayList
    private ArrayList<Photo> photosArray;

    // String
    private String[] photoName;
    private String[] photoPath;

    // JSON
    private JSONStoragePhoto jsonPhotoBook;

    // ActionListener
    private GalleryActionListener galleryListener;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    /**
     * Constructeur
     *
     * @param contentLayout
     */
    public GalleryGUI(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
        buildJSON();
        buildPnlHomeGall();
        buildCardLayout();
        buildListeners();
    }
    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    /**
     * Création initiale du panel
     */
    public void buildPnlHomeGall() {
        // Panel nord
        pnlGallHome = new JPanel();
        lblGallery = new JLabel("Gallery");
        btnAddPhoto = new myButton("+");

        pnlGallHome.add(lblGallery);
        pnlGallHome.add(btnAddPhoto);

        // Panel centre
        jsGallHome = new JScrollPane();
        jsGallHome = buildPnlImageJs();

        // Panel qui contient le tout
        pnlCTGH = new JPanel(new BorderLayout());
        pnlCTGH.add(pnlGallHome, BorderLayout.NORTH);
        pnlCTGH.add(jsGallHome, BorderLayout.CENTER);
    }

    /**
     * Création du fichier JSON
     */
    private void buildJSON() {
        // Dé-sérialer (READ) le fichier JSON
        jsonPhotoBook = new JSONStoragePhoto();
    }

    /**
     * Création des variables
     */
    private void buildvariables() {
        galleryListener = new GalleryActionListener(this);

        photosArray = jsonPhotoBook.getPhotosArray();       //On récupère les photos et on dé-sérialise
        btnPhoto = new ButtonIcon[photosArray.size()];      //Création du tableau de bouton ayant la taille de photos

        photoName = new String[photosArray.size()];
        photoPath = new String[photosArray.size()];

        pnlShowPhoto = new ShowPhoto[photosArray.size()];

        // Création des pannels, pour chaques photos
        // Création des contenus des ARRAYS nécessaires pour les CARDS de photos
        for (int i = 0; i < photosArray.size(); i++) {
            photoName[i] = photosArray.get(i).getName();
            photoPath[i] = photosArray.get(i).getPath();
            pnlShowPhoto[i] = new ShowPhoto(this, photoName[i], photoPath[i]);
        }
    }

    /**
     * Création du JscrollPane qui contient le panel qui affiche les images
     *
     * @return un JscrollPane qui contient un panel de boutons de toutes nos photos
     */
    protected JScrollPane buildPnlImageJs() {
        buildvariables();

        pnlImages = new JPanel(new GridLayout(0, 2, 5, 5));

        if (photosArray.size() == 0) {
            lblmsg = new JLabel("Gallery is empty");
            pnlImages.add(lblmsg);
        } else {
            // Création des boutons pour chaques images
            for (int i = 0; i < photosArray.size(); i++) {
                ic = new ImageIcon(String.valueOf(photosArray.get(i).getPath()));
                ic = Util.getScaledImageIcon(ic, 100);
                btnPhoto[i] = new ButtonIcon(ic);
                pnlImages.add(btnPhoto[i]);
            }
        }
        jsGallHome = new JScrollPane(pnlImages);
        jsGallHome.setPreferredSize(new Dimension(280, 500));
        jsGallHome.setMinimumSize(new Dimension(280, 500));
        jsGallHome.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsGallHome.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        return jsGallHome;
    }

    /**
     * Méthode qui contruit le cardLayout
     */
    public void buildCardLayout() {
        cardGallHome = new CardLayout();

        this.setLayout(cardGallHome);

        this.add("HomeGallery", pnlCTGH);
        for (int i = 0; i < photosArray.size(); i++) {
            this.add(photoName[i], pnlShowPhoto[i]);
        }
    }

    //*****************************************************************************
    // L I S T E N E R S
    //*****************************************************************************
    public void buildListeners() {
        // Bouton qui permet d'ajouter des photos
        btnAddPhoto.addActionListener(galleryListener);

        // Création des ActionListeners en fonction du nombre d'image
        for (int i = 0; i < photosArray.size(); i++) {
            btnPhoto[i].addActionListener(galleryListener);
        }
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public JPanel getPnlCTGH() {
        return pnlCTGH;
    }

    public JPanel getPnlGallHome() {
        return pnlGallHome;
    }

    public JPanel getPnlImages() {
        return pnlImages;
    }

    public ShowPhoto[] getPnlShowPhoto() {
        return pnlShowPhoto;
    }

    public CardLayout getCardGallHome() {
        return cardGallHome;
    }

    public ContentLayout getContentLayout() {
        return contentLayout;
    }

    public JScrollPane getJsGallHome() {
        return jsGallHome;
    }

    public JLabel getLblGallery() {
        return lblGallery;
    }

    public JLabel getLblmsg() {
        return lblmsg;
    }

    public myButton getBtnAddPhoto() {
        return btnAddPhoto;
    }

    public ButtonIcon[] getBtnPhoto() {
        return btnPhoto;
    }

    public ImageIcon getIc() {
        return ic;
    }

    public ArrayList<Photo> getPhotosArray() {
        return photosArray;
    }

    public String[] getPhotoName() {
        return photoName;
    }

    public String[] getPhotoPath() {
        return photoPath;
    }

    public JSONStoragePhoto getJsonPhotoBook() {
        return jsonPhotoBook;
    }

    public GalleryActionListener getGalleryListener() {
        return galleryListener;
    }
}