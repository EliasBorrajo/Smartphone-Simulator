package ch.hevs.smartphone.structure.layout;

import ch.hevs.smartphone.applications.contacts.*;
import ch.hevs.smartphone.applications.gallery.GalleryBook;
import ch.hevs.smartphone.applications.gallery.Photo;
import ch.hevs.smartphone.bases.MyIcon;
import ch.hevs.smartphone.applications.enums.ScreenSizeEnum;
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
    private WeatherGUI pnlWeather;
    private ShowContactInfo[] pnlShowContactInfo;

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
    private AddressBook addressBook = new AddressBook();
    private String currentPanel = "Home";

    private int nbContact;
    private String[] contactName; // Nom des contacts pour les nouvelles cards
    private String[] contactNoPhone;

    // GETTERS
    public int getNbContact() {
        return nbContact;
    }

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
        ArrayList<Contact> contacts = this.addressBook.getTabContact();
        addressBook.sortDescending(contacts);
        nbContact = contacts.size();

        contactName = new String[nbContact];
        contactNoPhone = new String[nbContact];

        cardlayout = new CardLayout();

        pnlContent = this;

        pnlHome = new JPanel(); //new GridLayout(2,3)
        pnlContact = new ContactsGUI();
        pnlAddContact = new AddContact();
        pnlGallery = new GalleryGUI();
        pnlWeather = new WeatherGUI();
        pnlShowContactInfo = new ShowContactInfo[nbContact];

        // Création des arrays nécessaires pour les cards de contacts

        for (int i=0; i<nbContact; i++) {
            contactName[i] = contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName();
            contactNoPhone[i] = contacts.get(i).getNoPhone();
            pnlShowContactInfo[i] = new ShowContactInfo(contactName[i], contactNoPhone[i]);
        }

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
        this.add("Weather",pnlWeather);
        for (int l=0; l<nbContact; l++) {
            this.add(contactName[l], pnlShowContactInfo[l]);
        } // Création des cards de contact

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

        for (int i=0; i<nbContact; i++) {
            int finalI = i;
            pnlContact.getBtnShowContact()[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    refreshPanel(contactName[finalI]);
                }
            });
        } // création des listener en fonction du nombre de contacts*/

        pnlAddContact.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                buildpnlContent();
                cardlayout.show(pnlContent,"Contact");
            }
        });

        this.fLayout.getBtnBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actionsCount > 0) {
                    String openPanel = "";
                    panelsOpen.remove(actionsCount);
                    actionsCount--;
                    cardlayout.show(pnlContent, panelsOpen.get(actionsCount));
//                    refreshPanel(panelsOpen.get(actionsCount));
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
        // HISTORIQUE des panels affichés pour le bouton retour
        this.actionsCount++;
        this.panelsOpen.add(this.currentPanel);
    }
}