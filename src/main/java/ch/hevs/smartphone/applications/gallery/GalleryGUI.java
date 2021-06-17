package ch.hevs.smartphone.applications.gallery;

import ch.hevs.smartphone.applications.gallery.listeners.GalleryActionListener;
import ch.hevs.smartphone.applications.gallery.serialisation.JSONStoragePhoto;
import ch.hevs.smartphone.parameters.button.ButtonIcon;
import ch.hevs.smartphone.parameters.button.myButton;
import ch.hevs.smartphone.parameters.utils.Util;
import ch.hevs.smartphone.structure.layout.ContentLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Main panel of the cardlayout gallery application
 * Json file check and creation if missing
 * Creat the gallery display
 * Access to the actionListeners class
 *
 * @author Lonfat Milena
 */

public class GalleryGUI extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Panel
    private JPanel pnlCTGH;
    private JPanel pnlGallHome;
    private JPanel pnlImages;
    private ShowPhotoInfo[] pnlShowPhotoInfo;

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
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
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
    // M E T H O D S
    //*****************************************************************************
    /**
     * buildPnlHomeGall : create all the panels and their contents
     */
    public void buildPnlHomeGall() {
        // North panel
        pnlGallHome = new JPanel();
        lblGallery = new JLabel("Gallery");
        btnAddPhoto = new myButton("+");

        pnlGallHome.add(lblGallery);
        pnlGallHome.add(btnAddPhoto);

        // Center panel
        jsGallHome = new JScrollPane();
        jsGallHome = buildPnlImageJs();

        // Panel that contains everything
        pnlCTGH = new JPanel(new BorderLayout());
        pnlCTGH.add(pnlGallHome, BorderLayout.NORTH);
        pnlCTGH.add(jsGallHome, BorderLayout.CENTER);
    }

    /**
     * Creating the JSON file
     */
    private void buildJSON() {
        // Dé-sérialer (READ) le fichier JSON
        jsonPhotoBook = new JSONStoragePhoto();
    }

    /**
     * building variables
     */
    private void buildVariables() {
        galleryListener = new GalleryActionListener(this);

        photosArray = jsonPhotoBook.getPhotosArray();       // Recover the deserialize photos
        btnPhoto = new ButtonIcon[photosArray.size()];      // Create the array of buttons having the size of the array of photo

        photoName = new String[photosArray.size()];
        photoPath = new String[photosArray.size()];

        pnlShowPhotoInfo = new ShowPhotoInfo[photosArray.size()];

        // Creation of panels for each photo
        // Creation of the contents of the ARRAYS necessary for the gallery cards
        for (int i = 0; i < photosArray.size(); i++) {
            photoName[i] = photosArray.get(i).getName();
            photoPath[i] = photosArray.get(i).getPath();
            pnlShowPhotoInfo[i] = new ShowPhotoInfo(this, photoName[i], photoPath[i]);
        }
    }

    /**
     * Creation of the ScrollBar which has the list of photo buttons
     *
     * @return ScrollPane which has the list of photo button
     */
    protected JScrollPane buildPnlImageJs() {
        buildVariables();

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
     * Method that builds the cardLayout
     */
    public void buildCardLayout() {
        cardGallHome = new CardLayout();

        this.setLayout(cardGallHome);

        this.add("HomeGallery", pnlCTGH);
        for (int i = 0; i < photosArray.size(); i++) {
            this.add(photoName[i], pnlShowPhotoInfo[i]);
        }
    }

    //*****************************************************************************
    // L I S T E N E R S
    //*****************************************************************************
    /**
     * method to assign the listener with its button
     */
    public void buildListeners() {

        btnAddPhoto.addActionListener(galleryListener);

        // Creation of ActionListeners according to the number of photos
        for (int i = 0; i < photosArray.size(); i++) {
            btnPhoto[i].addActionListener(galleryListener);
        }
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public ShowPhotoInfo[] getPnlShowPhoto() {
        return pnlShowPhotoInfo;
    }

    public CardLayout getCardGallHome() {
        return cardGallHome;
    }

    public myButton getBtnAddPhoto() {
        return btnAddPhoto;
    }

    public ButtonIcon[] getBtnPhoto() {
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

    public GalleryActionListener getGalleryListener() {
        return galleryListener;
    }
}