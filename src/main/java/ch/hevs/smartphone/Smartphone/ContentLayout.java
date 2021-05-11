package ch.hevs.smartphone.Smartphone;

import ch.hevs.smartphone.Bases.MyButton;
import ch.hevs.smartphone.Bases.ScreenSizeEnum;
import ch.hevs.smartphone.Contacts.ContactGUI;
import ch.hevs.smartphone.Gallery.GalleryGUI;
import ch.hevs.smartphone.Weather.WeatherGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentLayout extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // LAYOUT
    private CardLayout cardLayout = new CardLayout();

    // JPANEL
    private JPanel pnlContent = new JPanel();   // Panel principal qui va contenir tous les cards
    private ContactGUI pnlContact = new ContactGUI();
    private GalleryGUI pnlGallery = new GalleryGUI();
    private WeatherGUI pnlWeather = new WeatherGUI();
    //@TODO : faire de meme avec le home
    private JPanel pnlHome = new JPanel();

    // JBUTTON
    private MyButton btnContact = new MyButton("Contact");
    private MyButton btnGallery = new MyButton("Gallery");
    private MyButton btnWeather = new MyButton("Weather");


    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public ContentLayout(){
        this.setPreferredSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        this.setMinimumSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));

        //this.buildCardLayout();
        this.buildCardLayout();
        //btnActions();

        //this.setLayout(cardLayout);

        //this.setContactGUI(new ContactGUI());
        //this.setGalleryGUI(new GalleryGUI());
        //this.setWeatherGUI(new WeatherGUI());

        //buildRoots();
    }
    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private void buildCardLayout(){
        pnlContent = this;
        pnlContent.setLayout(cardLayout);   // pnlContent sera de type cardLayout

        pnlHome.setLayout(new GridLayout(2,3)); //@TODO: vérifier le layout
        pnlHome.setBackground(Color.GREEN);
        pnlHome.add(btnContact);
        pnlHome.add(btnGallery);
        pnlHome.add(btnWeather);

        //Ajouteur les cards au panel conteneur
        pnlContent.add(pnlHome,"Home");
        pnlContent.add(pnlContact,"Contact");
        pnlContent.add(pnlGallery,"Gallery");
        pnlContent.add(pnlWeather,"Weather");

        cardLayout.show(pnlContent,"Home");

        btnContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnlContent,"Contact");
            }
        });

        btnGallery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnlContent,"Gallery");
            }
        });

        btnWeather.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnlContent,"Weather");
            }
        });
    }

    public JPanel getCardlayout(){
        return pnlContent;
    }
}
