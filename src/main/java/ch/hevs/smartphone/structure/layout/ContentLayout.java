package ch.hevs.smartphone.structure.layout;

import ch.hevs.smartphone.applications.contacts.AddContact;
import ch.hevs.smartphone.applications.contacts.AddressBook;
import ch.hevs.smartphone.applications.contacts.ContactsGUI;
import ch.hevs.smartphone.applications.gallery.GalleryBook;
import ch.hevs.smartphone.applications.gallery.Photo;
import ch.hevs.smartphone.bases.MyIcon;
import ch.hevs.smartphone.applications.enums.ScreenSizeEnum;
import ch.hevs.smartphone.applications.gallery.AddPhotoGallery;
import ch.hevs.smartphone.applications.gallery.GalleryGUI;
import ch.hevs.smartphone.applications.weather.WeatherGUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class ContentLayout extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // LAYOUT
    private CardLayout cardlayout;

    // PANEL
    private JPanel pnlContent;  // Panel principal qui va contenir tous les cards
    private JPanel pnlHome;
    private ContactsGUI pnlContact;
    private AddContact pnlAddContact;
    private GalleryGUI pnlGallery;
    private AddPhotoGallery pnlAddPhoto;
    private WeatherGUI pnlWeather;

    // BUTTON
    private MyIcon btnContact;
    private MyIcon btnGallery;
    private MyIcon btnWeather;

    // ICON
    private ImageIcon iconWeather;
    private ImageIcon iconContact;
    private ImageIcon iconGallery;

    private FooterLayout fLayout;

    private int actionsCount = -1;
    private ArrayList<String> panelsOpen = new ArrayList<String>();
    private String currentPanel = "Home";


    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public ContentLayout(FooterLayout fLayout){
        this.fLayout = fLayout;
        setPreferredSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        buildpnlContent();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private JPanel buildpnlContent(){
        cardlayout = new CardLayout();

        pnlContent = this;

        pnlHome = new JPanel(); //new GridLayout(2,3)
        pnlContact = new ContactsGUI();
        pnlAddContact = new AddContact();
        pnlGallery = new GalleryGUI();
        pnlAddPhoto = new AddPhotoGallery();
        pnlWeather = new WeatherGUI();


        // CONSTUCTION DES IMAGES
        URL imageContact = ContentLayout.class.getClassLoader().getResource("ContentIcon/contactIcon2.png");
        URL imageGallery = ContentLayout.class.getClassLoader().getResource("ContentIcon/galleryIcon2.png");
        URL imageWeather = ContentLayout.class.getClassLoader().getResource("ContentIcon/weatherIcon2.png");

        iconContact = new ImageIcon(imageContact);
        iconGallery = new ImageIcon(imageGallery);
        iconWeather = new ImageIcon(imageWeather);

        btnContact = new MyIcon(iconContact);
        btnGallery = new MyIcon(iconGallery);
        btnWeather = new MyIcon(iconWeather);

        // LA SUITE CONCERNE LE CARD
        this.setLayout(cardlayout);

        pnlHome.add(btnContact);
        pnlHome.add(btnGallery);
        pnlHome.add(btnWeather);
        pnlHome.setBackground(Color.GREEN);

        //Ajouteur les cards au panel conteneur
        this.add("Home",pnlHome);
        this.add("Contact",pnlContact);
        this.add("AddContact", pnlAddContact);
        this.add("Gallery", pnlGallery);
        this.add("AddPhoto", pnlAddPhoto);
        this.add("Weather",pnlWeather);

        this.refreshPanel("Home");

        btnContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshPanel("Contact");
            }
        });

        btnGallery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshPanel("Gallery");
            }
        });

        btnWeather.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshPanel("Weather");
            }
        });

        pnlGallery.getBtnAddGallery().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GalleryBook gb = new GalleryBook();

                String path = null;
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, GIF & PNG Images", "jpg", "gif", "png");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(chooser);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    path = chooser.getSelectedFile().getPath();
                    Photo photo = new Photo(path);

                    gb.addPhoto(photo);

                    gb.save();

                    //int index = path.indexOf();
                    //path = path.substring(index);
                    //ImageIcon icon = new ImageIcon(path);
                    //icon = Util.getScaledImageIcon(icon, 250);


                    //this.getButtonPictureChooser().setIcon(icon);
                }

                //this.getContentManager().getGalleryModel().setPathPictureSelected(path);


                //refreshPanel("AddPhoto");
            }
        });

        pnlContact.getBtnAddContact().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshPanel("AddContact");
            }
        });

        // CREER UN GETTER POUR LE BOUTON
        this.pnlAddContact.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddressBook ad = new AddressBook();

                String path = null;



            }
        });

        this.fLayout.getBtnBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actionsCount > 0) {
                    panelsOpen.remove(actionsCount);
                    actionsCount--;
                    cardlayout.show(pnlContent, panelsOpen.get(actionsCount));
                    fLayout.buildMenu("Home");
                }else{
                    refreshPanel("Home");
                }
            }
        });

        this.fLayout.getBtnHome().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionsCount = 0;
                //Reset historique
                panelsOpen = new ArrayList<>();
                refreshPanel("Home");
            }
        });

        return this;
    }

    public void refreshPanel(String currentPanel){
        this.currentPanel = currentPanel;
        cardlayout.show(this,this.currentPanel);
        fLayout.buildMenu(this.currentPanel);
        // HISTRORIQUE des panels affichés pour le bouton retour
        this.actionsCount++;
        this.panelsOpen.add(this.currentPanel);
    }
}