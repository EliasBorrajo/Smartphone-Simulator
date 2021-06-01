package ch.hevs.smartphone.structure.layout;

import ch.hevs.smartphone.applications.contacts.*;
import ch.hevs.smartphone.errors.BusinessException;
import ch.hevs.smartphone.parameters.button.ButtonIcon;
import ch.hevs.smartphone.parameters.ScreenSizeEnum;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

/**
 * Classe contenant tout notre écran principal.
 * On peut lancer 3 applications différentes depuis le Home.
 * ContentLayout est de type CardsLayout.
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
   private JPanel pnlHome;             // Panneau d'accueil
    // CONTACTES
    private ContactsGUI pnlContact;     // Application Contactes

    /*// GALLERY
    private GalleryGUI pnlGallery;      // Application Gallerie
        // WEATHER
    private WeatherGUI pnlWeather;      // Application Weather*/


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
    /*private int actionsCount = -1;              // Compteur permettant de savoir quel PANNEL afficher (BtnHome & BtnRetour)
    private ArrayList<String> panelsOpen = new ArrayList<String>();*/
    private String currentPanel = "Home";       // Sert au refreshPannel

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
        cardlayout = new CardLayout();

        pnlHome = new JPanel();                     //new GridLayout(2,3)
        pnlContact = new ContactsGUI(this);

        /*pnlGallery = new GalleryGUI();
        pnlWeather = new WeatherGUI();*/

        // HOME - CONSTUCTION DES IMAGES
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
        this.add("Home",        pnlHome);
        this.add("Contact",     pnlContact);

        refreshPanel("Home");
        /*this.add("Gallery",     pnlGallery);
        this.add("Weather",     pnlWeather);*/

        //*****************************************************************************
        // L I S T E N E R S
        //*****************************************************************************

        // BOUTTONS DE HOME-PAGE
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

        this.footerLayout.getBtnHome().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pnlContact.getCardLayoutContact().show(pnlContact,"HomeContact");
                refreshPanel("Home");
            }
        });

    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    public void refreshPanel(String currentPanel)
    {
        //this.currentPanel = currentPanel;
        cardlayout.show(this, currentPanel);
        footerLayout.buildMenu();
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public CardLayout getCardlayout()
    {
        return cardlayout;
    }

    public JPanel getPnlHome()
    {
        return pnlHome;
    }

    public ContactsGUI getPnlContact()
    {
        return pnlContact;
    }

    /*public GalleryGUI getPnlGallery()
    {
        return pnlGallery;
    }

    public WeatherGUI getPnlWeather()
    {
        return pnlWeather;
    }*/

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

}