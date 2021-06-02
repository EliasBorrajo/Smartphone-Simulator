package ch.hevs.smartphone.applications.gallery;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.gallery.listeners.GalleryActionListener;
import ch.hevs.smartphone.applications.gallery.serialisation.JSONStoragePhoto;
import ch.hevs.smartphone.parameters.button.ButtonIcon;
import ch.hevs.smartphone.parameters.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GalleryGUI extends JPanel {
    //Panel
    private JPanel pnlCTGH;
    private JPanel pnlGallHome;
    private JPanel pnlImages;
    private ShowPhoto[] pnlShowPhoto;

    //Layout
    private CardLayout cardGallHome;

    //Jscroll
    private JScrollPane jsGallHome;

    //Label
    private JLabel lblGallery;
    private JLabel lblmsg;

    //Button
    private Button btnAddPhoto;
    private ButtonIcon[] btnPhoto;

    //ImageIcon
    private ImageIcon ic;

    //ArrayList
    private ArrayList<Photo> photosArray;

    //String
    private String[] photoName;
    private String[] photoPath;

    //Other
    private JSONStoragePhoto jsonPhotoBook;
    private GalleryActionListener galleryListener;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public GalleryGUI() {
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
        /**Panel nord*/
        pnlGallHome = new JPanel();
        lblGallery = new JLabel("Gallery");
        btnAddPhoto = new Button("+");

        pnlGallHome.add(lblGallery);
        pnlGallHome.add(btnAddPhoto);

        /**Panel centre*/
        jsGallHome = new JScrollPane();
        jsGallHome = buildPnlImageJs();

        /**Panel qui contient le tout*/
        pnlCTGH = new JPanel(new BorderLayout());
        pnlCTGH.add(pnlGallHome, BorderLayout.NORTH);
        pnlCTGH.add(jsGallHome, BorderLayout.CENTER);
    }

    /**
     * Création du fichier JSON
     */
    private void buildJSON(){
        try {
            jsonPhotoBook = new JSONStoragePhoto();
        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to create galleryBook in GALLERY GUI");
        }
    }

    /**
     * Création des variables
     */
    private void buildvariables() {
        galleryListener = new GalleryActionListener(this);

        photosArray = jsonPhotoBook.getPhotosArray();    //On récupère les photos et on dé-sérialise
        btnPhoto = new ButtonIcon[photosArray.size()];      //Création du tableau de bouton ayant la taille de photos

        photoName = new String[photosArray.size()];
        photoPath = new String[photosArray.size()];

        pnlShowPhoto = new ShowPhoto[photosArray.size()];

        // Création des pannels, pour chaques photos
        // CREATION des contenus des ARRAYS nécessaires pour les CARDS de photos
        for (int i = 0; i < photosArray.size(); i++) {
            photoName[i] = photosArray.get(i).getName();
            photoPath[i] = photosArray.get(i).getPath();
            pnlShowPhoto[i] = new ShowPhoto(this, photoName[i], photoPath[i]);
        }
    }

    /**
     * Création du JscrollPane qui contient le panel qui affiche les images
     */
    private JScrollPane buildPnlImageJs() {
        buildvariables();

        pnlImages = new JPanel(new GridLayout(0, 2, 5, 5));

        if (photosArray.size() == 0) {
            lblmsg = new JLabel("Gallery is empty");
            pnlImages.add(lblmsg);
        } else {
            //Création des boutons pour chaques images
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
     * Panel principale contient les card
     */
    public void buildCardLayout() {
        cardGallHome = new CardLayout();

        this.setLayout(cardGallHome);

        this.add("GallHOME", pnlCTGH);
        for (int i = 0; i < photosArray.size(); i++) {
            this.add(photoName[i], pnlShowPhoto[i]);
        }
    }

    /**
     * Listeners du bouton ajouter
     * On construit dans une autre méthode pour avoir accès après la création de TOUS les composants
     */
    public void buildListeners() {
        for (int i = 0; i < photosArray.size(); i++) {
            btnPhoto[i].addActionListener(galleryListener);
        }

        btnAddPhoto.addActionListener(galleryListener);
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public ShowPhoto[] getPnlShowPhoto() {
        return pnlShowPhoto;
    }

    public CardLayout getCardGallHome() {
        return cardGallHome;
    }

    public Button getBtnAddPhoto() {
        return btnAddPhoto;
    }

    public JButton[] getBtnPhoto() {
        return btnPhoto;
    }

    public ArrayList<Photo> getPhotosArray() {
        return photosArray;
    }

    public String[] getPhotoName() {
        return photoName;
    }

    public JSONStoragePhoto getJsonPhotoBook() {
        return jsonPhotoBook;
    }

}