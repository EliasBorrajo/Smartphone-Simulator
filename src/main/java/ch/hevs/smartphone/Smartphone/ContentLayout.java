package ch.hevs.smartphone.Smartphone;

import ch.hevs.smartphone.Bases.MyButton;
import ch.hevs.smartphone.Bases.ScreenSizeEnum;
import ch.hevs.smartphone.Contacts.AddContact;
import ch.hevs.smartphone.Contacts.ContactsContentLayout;
import ch.hevs.smartphone.Gallery.GalleryGUI;
import ch.hevs.smartphone.Weather.WeatherGUI;

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
    private ContactsContentLayout pnlContact;
    private GalleryGUI pnlGallery;
    private WeatherGUI pnlWeather;
    private AddContact pnlAddContact;

    // BUTTON
    private MyButton btnContact;
    private MyButton btnGallery;
    private MyButton btnWeather;

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
        pnlContact = new ContactsContentLayout();
        pnlGallery = new GalleryGUI();
        pnlWeather = new WeatherGUI();
        pnlAddContact = new AddContact();


        // CONSTUCTION DES IMAGES
        URL imageContact = ContentLayout.class.getClassLoader().getResource("ContentIcon/contactIcon2.png");
        URL imageGallery = ContentLayout.class.getClassLoader().getResource("ContentIcon/galleryIcon2.png");
        URL imageWeather = ContentLayout.class.getClassLoader().getResource("ContentIcon/weatherIcon2.png");

        iconContact = new ImageIcon(imageContact);
        iconGallery = new ImageIcon(imageGallery);
        iconWeather = new ImageIcon(imageWeather);

        btnContact = new MyButton(iconContact);
        btnGallery = new MyButton(iconGallery);
        btnWeather = new MyButton(iconWeather);

        // LA SUITE CONCERNE LE CARD
        this.setLayout(cardlayout);

        pnlHome.add(btnContact);
        pnlHome.add(btnGallery);
        pnlHome.add(btnWeather);
        pnlHome.setBackground(Color.GREEN);

        //Ajouteur les cards au panel conteneur
        this.add("Home",pnlHome);
        this.add("Contact",pnlContact);
        this.add("Gallery",pnlGallery);
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
                refreshPanel("Home");
            }
        });


        return this;
    }

    private void refreshPanel(String currentPanel){
        this.currentPanel = currentPanel;
        cardlayout.show(this,this.currentPanel);
        fLayout.buildMenu(this.currentPanel);
        // HISTRORIQUE des panels affichés pour le bouton retour
        this.actionsCount++;
        this.panelsOpen.add(this.currentPanel);
    }
}