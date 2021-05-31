package ch.hevs.smartphone.structure.layout;

import ch.hevs.smartphone.applications.contacts.*;
import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.serialization.JSONStorageContact;
import ch.hevs.smartphone.applications.gallery.PhotoView;
import ch.hevs.smartphone.parameters.button.ButtonIcon;
import ch.hevs.smartphone.parameters.ScreenSizeEnum;
import ch.hevs.smartphone.applications.gallery.GalleryGUI;
import ch.hevs.smartphone.applications.weather.WeatherGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Classe contenant tout notre écran principal
 */
public class ContentLayout extends JPanel
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // LAYOUT
    private CardLayout cardlayout;

    // PANEL
    // HOME
    private JPanel pnlContent;          // Panel principal qui va contenir tous les cards
    private JPanel pnlHome;             // Panneau d'accueil
    // CONTACTES
    private ContactsGUI pnlContact;     // Application Contactes
    private AddContact pnlAddContact;               // Card de l'app contactes
    private ShowContactInfo[] pnlShowContactInfo;   // Cards des contactes que l'utilisateur va ajouter
    // GALLERY
    private GalleryGUI pnlGallery;      // Application Gallerie
    private PhotoView pnlPhotoView;                 // Pannel de photo ouverte
    // WEATHER
    private WeatherGUI pnlWeather;      // Application Weather


    // BUTTON - lancent les applicatons
    private ButtonIcon btnContact;
    private ButtonIcon btnGallery;
    private ButtonIcon btnWeather;

    // ICON
    private ImageIcon iconWeather;
    private ImageIcon iconContact;
    private ImageIcon iconGallery;


    // FooterLayout
    private FooterLayout footerLayout;
    private int actionsCount = -1;          // Compteur permettant de savoir quel PANNEL afficher (BtnHome & BtnRetour)
    private ArrayList<String> panelsOpen = new ArrayList<String>();
    private String currentPanel = "Home";      // Sert au refreshPannel

    // CONTACT APP
    //private AddressBook addressBook;               // Conteint tous les contactes
    private JSONStorageContact addressBook = new JSONStorageContact();
    private int nbContact;                      // Nombre de contactes dans le carnet d'adresse
    private String[] contactName;               // Nom des contacts pour les nouvelles cards
    private String[] contactNoPhone;            //


    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public ContentLayout(FooterLayout footerLayout) throws IOException, BusinessException
    {
        this.footerLayout = footerLayout;
        setPreferredSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));

        buildpnlContent();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private void buildpnlContent() throws IOException, BusinessException
    {
        pnlContact = new ContactsGUI();
        //*********** @TODO DECALRER AILLEURS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        addressBook = pnlContact.getAddressBook();
        ArrayList<Contact> contacts = this.addressBook.getContactArray();
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

        for (int i = 0; i < nbContact; i++)
        {
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

        // CARDS LAYOUT
        this.setLayout(cardlayout);

        pnlHome.add(btnContact);
        pnlHome.add(btnGallery);
        pnlHome.add(btnWeather);
        pnlHome.setBackground(Color.darkGray);

        //Ajouteur les cards au panel conteneur
        this.add("Home", pnlHome);
        this.add("Contact", pnlContact);
        this.add("AddContact", pnlAddContact);
        this.add("Gallery", pnlGallery);
        this.add("PhotoView", pnlPhotoView);
        this.add("Weather", pnlWeather);

        // Création des cards de contact
        for (int l = 0; l < nbContact; l++)
        {
            this.add(contactName[l], pnlShowContactInfo[l]);
        }
        this.refreshPanel("Home");


        //*****************************************************************************
        // L I S T E N E R S
        //*****************************************************************************

        // BOUTTONS HOME
        btnContact.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                refreshPanel("Contact");
            }
        });

        btnGallery.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                refreshPanel("Gallery");
            }
        });

        btnWeather.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                refreshPanel("Weather");
            }
        });

        // CONTACT APP
        pnlContact.getBtnAddContact().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                refreshPanel("AddContact");
            }
        });

        // création des ActionListener en fonction du nombre de contacts présents*/
        for (int i = 0; i < nbContact; i++)
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
        }

        pnlAddContact.getBtnSave().addActionListener(new ListenerSaveAddContact
                (this.getPnlAddContact().getTfFirstName(),
                        this.getPnlAddContact().getTfName(),
                        this.getPnlAddContact().getTfNoPhone()
                ));

        // FOOTER
        this.footerLayout.getBtnBack().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (actionsCount > 0)
                {
                    panelsOpen.remove(actionsCount);
                    actionsCount--;
                    cardlayout.show(pnlContent, panelsOpen.get(actionsCount));
                    footerLayout.buildMenu();
                }
            }
        });

        this.footerLayout.getBtnHome().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                actionsCount = -1;
                panelsOpen.removeAll(panelsOpen);
                refreshPanel("Home");
            }
        });

        //return this;
    }

    public void refreshPanel(String currentPanel)
    {
        System.out.println("AVANT REFRESH" + panelsOpen);
        this.currentPanel = currentPanel;
        cardlayout.show(this, this.currentPanel);
        footerLayout.buildMenu();
        // HISTRORIQUE des panels affichés pour le bouton retour
        this.actionsCount++;
        this.panelsOpen.add(this.currentPanel);
        System.out.println("APRES REFRESH" + panelsOpen);

    }

    //*****************************************************************************
    // I N N E R  -  C L A S S
    //*****************************************************************************
    public class ListenerSaveAddContact implements ActionListener
    {
        JTextField inputFN;
        JTextField inputN;
        JTextField inputNP;

        public ListenerSaveAddContact(JTextField firstName, JTextField name, JTextField noPhone)
        {
            inputFN = firstName;
            inputN = name;
            inputNP = noPhone;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            // SAVE
            // Récuperation des TEXTBOX
            Contact c = new Contact("", "", "");

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
           // addressBook.sortDescending(addressBook.getContactArray()); // trie l'Arraylist contacts par ordre alphabétique

            System.out.println("AddContact1");
            try
            {
                addressBook.write(addressBook.getmyObj(), addressBook.getContactArray()); // SERIALISATION
            } catch (BusinessException businessException)
            {
                businessException.printStackTrace();
                System.out.println("COULD NOT SAVE CONTACT -- INNER CLASS CONTENTLAYOUT LISTENERS");
            }
            System.out.println("AddContact2");

            inputFN.setText("");
            inputN.setText("");
            inputNP.setText("");

            // REFRESH @TODO REFRESH BUG ENCORE
            System.out.println("ContentLayout");
            pnlContact.removeAll();
            // pnlContact.validate();
            //refreshPanel("Contact");
            pnlContact.add(pnlContact.buildpnlContentContact());

            //pnlContact.revalidate();
            //pnlContact.repaint();

            cardlayout.show(pnlContent, "Contact");
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

    public int getNbContact()
    {
        return nbContact;
    }
}