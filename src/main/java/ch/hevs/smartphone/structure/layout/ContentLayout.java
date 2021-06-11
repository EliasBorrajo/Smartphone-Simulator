package ch.hevs.smartphone.structure.layout;

import ch.hevs.smartphone.applications.contacts.*;
import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
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
    private JPanel pnlContent;          // Panel principal qui va contenir tous les cards
    private JPanel pnlHome;             // Panneau d'accueil
    // CONTACTS
    private ContactsGUI pnlContact;     // Application Contactes
    // GALLERY
    private GalleryGUI pnlGallery;      // Application Gallerie
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

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public ContentLayout() throws IOException, BusinessException
    {
        setPreferredSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        buildpnlContent();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    public void buildpnlContent() throws IOException, BusinessException
    {
        cardlayout = new CardLayout();

        pnlContent = this;                          // permet de faciliter la lecture

        pnlHome = new JPanel();                     //new GridLayout(2,3)
        pnlContact = new ContactsGUI(this);
        pnlGallery = new GalleryGUI(this);
        pnlWeather = new WeatherGUI();

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
        this.add("Gallery",     pnlGallery);
        this.add("Weather",     pnlWeather);

        cardlayout.show(this, "Home");

        //*****************************************************************************
        // L I S T E N E R S
        //*****************************************************************************
        // BOUTTONS DE HOME-PAGE
        btnContact.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               cardlayout.show(pnlContent, "Contact");
            }
        });

        btnGallery.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cardlayout.show(pnlContent, "Gallery");
            }
        });

        btnWeather.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cardlayout.show(pnlContent, "Weather");
            }
        });
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

    public GalleryGUI getPnlGallery()
    {
        return pnlGallery;
    }

    public WeatherGUI getPnlWeather()
    {
        return pnlWeather;
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

}