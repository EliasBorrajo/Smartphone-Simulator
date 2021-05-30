package ch.hevs.smartphone.structure.layout;

import ch.hevs.smartphone.applications.contacts.*;
import ch.hevs.smartphone.applications.gallery.PhotoView;
import ch.hevs.smartphone.parameters.button.ButtonIcon;
import ch.hevs.smartphone.parameters.ScreenSizeEnum;
import ch.hevs.smartphone.applications.gallery.GalleryGUI;
import ch.hevs.smartphone.applications.weather.WeatherGUI;

import javax.swing.*;
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
    private PhotoView pnlPhotoView;
    private ShowContactInfo[] pnlShowContactInfo;


    // BUTTON
    private ButtonIcon btnContact;
    private ButtonIcon btnGallery;
    private ButtonIcon btnWeather;

    // ICON
    private ImageIcon iconWeather;
    private ImageIcon iconContact;
    private ImageIcon iconGallery;

    private FooterLayout footerLayout;

    private int actionsCount = -1;
    private ArrayList<String> panelsOpen = new ArrayList<String>();
    private String currentPanel = "Home";

    // CONTACT APP INFOS
    private AddressBook addressBook ;//= new AddressBook();
    private int nbContact;
    private String[] contactName; // Nom des contacts pour les nouvelles cards
    private String[] contactNoPhone;

    public int getNbContact()
    {
        return nbContact;
    }

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public ContentLayout(FooterLayout fLayout){
        this.footerLayout = fLayout;
        setPreferredSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));

        buildpnlContent();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private JPanel buildpnlContent()
    {
        pnlContact = new ContactsGUI();
        //*********** @TODO DECALRER AILLEURS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        addressBook = pnlContact.getAddressBook();
        ArrayList<Contact> contacts = this.addressBook.getTabContact();
        addressBook.sortDescending(contacts);
        nbContact = contacts.size();

        contactName = new String[nbContact];
        contactNoPhone = new String[nbContact];
        //***********

        cardlayout = new CardLayout();

        pnlContent = this;

        pnlHome = new JPanel(); //new GridLayout(2,3)

        pnlAddContact = new AddContact();
        pnlGallery = new GalleryGUI();
        pnlWeather = new WeatherGUI();
        pnlPhotoView = new PhotoView();
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

        btnContact = new ButtonIcon(iconContact);
        btnGallery = new ButtonIcon(iconGallery);
        btnWeather = new ButtonIcon(iconWeather);

        // LA SUITE CONCERNE LE CARD
        this.setLayout(cardlayout);

        pnlHome.add(btnContact);
        pnlHome.add(btnGallery);
        pnlHome.add(btnWeather);
        pnlHome.setBackground(Color.darkGray);

        //Ajouteur les cards au panel conteneur
        this.add("Home",pnlHome);
        this.add("Contact",pnlContact);
        this.add("AddContact", pnlAddContact);
        this.add("Gallery", pnlGallery);
        this.add("PhotoView", pnlPhotoView);
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
/*
        pnlGallery.getBtnPhoto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton buttonClicked = (JButton)e.getSource();
                System.out.println(buttonClicked);
                refreshPanel("PhotoView");
            }
        });

*/

        pnlContact.getBtnAddContact().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                refreshPanel("AddContact");
            }
        });

        for (int i=0; i<nbContact; i++)
        {
            int finalI = i;
            pnlContact.getBtnShowContact()[i].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    refreshPanel(contactName[finalI]);
                }
            });
        } // création des listener en fonction du nombre de contacts*/

        pnlAddContact.getBtnSave().addActionListener(new ListenerSaveAddContact
                ( this.getPnlAddContact().getTfFirstName(),
                  this.getPnlAddContact().getTfName(),
                  this.getPnlAddContact().getTfNoPhone()
                ));
        /*pnlAddContact.getBtnSave().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("ContentLayout");
                pnlContact.removeAll();
                pnlContact.add(pnlContact.buildpnlContentContact());
                cardlayout.show(pnlContent,"Contact");
            }
        });*/


        this.footerLayout.getBtnBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actionsCount > 0) {
                    panelsOpen.remove(actionsCount);
                    actionsCount--;
                    cardlayout.show(pnlContent, panelsOpen.get(actionsCount));
                    footerLayout.buildMenu();
                }
            }
        });

        this.footerLayout.getBtnHome().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionsCount = -1;
                panelsOpen.removeAll(panelsOpen);
                refreshPanel("Home");
            }
        });

        return this;
    }

    public void refreshPanel(String currentPanel){
        this.currentPanel = currentPanel;
        cardlayout.show(this,this.currentPanel);
        footerLayout.buildMenu();
        // HISTRORIQUE des panels affichés pour le bouton retour
        this.actionsCount++;
        this.panelsOpen.add(this.currentPanel);
    }

    //*****************************************************************************
    // I N N E R  -  C L A S S
    //*****************************************************************************
    public class ListenerSaveAddContact implements ActionListener
    {

        JTextField inputFN;
        JTextField inputN;
        JTextField inputNP;

        public ListenerSaveAddContact(JTextField firstName, JTextField name, JTextField noPhone) {
            inputFN = firstName;
            inputN = name;
            inputNP = noPhone;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            // SAVE
            Contact c = new Contact("","","");

            String text1 = "";
            text1 = inputFN.getText();
            c.setFirstName(text1);

            String text2 = "";
            text2 = inputN.getText();
            c.setLastName(text2);

            String text3 = "";
            text3 = inputNP.getText();
            c.setNoPhone(text3);

            System.out.println(c);

            addressBook.addContact(c);
            addressBook.sortDescending(addressBook.getTabContact()); // trie l'Arraylist contacts par ordre alphabétique

            System.out.println("AddContact1");
            addressBook.save(); // SERIALISATION
            System.out.println("AddContact2");

            inputFN.setText("");
            inputN.setText("");
            inputNP.setText("");

            // REFRESH @TODO REFRESH BUG ENCORE, il revient en arrière, mais ne met pas à jour la liste de contactes
            System.out.println("ContentLayout");
            pnlContact.removeAll();
           // pnlContact.validate();

            pnlContact.add(pnlContact.buildpnlContentContact());

            //pnlContact.revalidate();
            //pnlContact.repaint();

            cardlayout.show(pnlContent,"Contact");
            actionsCount--;

        }
    }
    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public CardLayout getCardlayout()
    {
        return cardlayout;
    }

    public JPanel getPnlContent()
    {
        return pnlContent;
    }

    public JPanel getPnlHome()
    {
        return pnlHome;
    }

    public ContactsGUI getPnlContact()
    {
        return pnlContact;
    }

    public AddContact getPnlAddContact()
    {
        return pnlAddContact;
    }

    public GalleryGUI getPnlGallery()
    {
        return pnlGallery;
    }

    public WeatherGUI getPnlWeather()
    {
        return pnlWeather;
    }

    public PhotoView getPnlPhotoView()
    {
        return pnlPhotoView;
    }

    public ButtonIcon getBtnContact()
    {
        return btnContact;
    }

    public ButtonIcon getBtnGallery()
    {
        return btnGallery;
    }

    public ButtonIcon getBtnWeather()
    {
        return btnWeather;
    }

    public ImageIcon getIconWeather()
    {
        return iconWeather;
    }

    public ImageIcon getIconContact()
    {
        return iconContact;
    }

    public ImageIcon getIconGallery()
    {
        return iconGallery;
    }

    public FooterLayout getfLayout()
    {
        return footerLayout;
    }

    public int getActionsCount()
    {
        return actionsCount;
    }

    public ArrayList<String> getPanelsOpen()
    {
        return panelsOpen;
    }

    public String getCurrentPanel()
    {
        return currentPanel;
    }
}