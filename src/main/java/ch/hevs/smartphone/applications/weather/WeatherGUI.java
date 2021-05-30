package ch.hevs.smartphone.applications.weather;

import ch.hevs.smartphone.parameters.ScreenSizeEnum;

import javax.swing.*;
import java.awt.*;

public class WeatherGUI extends JPanel
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    WeatherAPI weatherInfos;

    // PANEL
    private JPanel pnlNord;
    private JPanel pnlCentre;
    private JPanel pnlEst;
    private JPanel pnlOuest;

    // LABEL
    private JLabel lblVille;
    private JLabel lblTempMax;
    private JLabel lblHumidite;

    // SCROLL PANE
    private JScrollPane scrollPaneWeather;



    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public WeatherGUI()
    {
        weatherInfos = new WeatherAPI();
        buildMainPanel();
    }



    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private void buildMainPanel()
    {
        setPreferredSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));

        setLayout(new BorderLayout());
        setBackground(Color.CYAN);

        buildPanel();
    }

    private void buildPanel()
    {
        // Cration des labels
        // @TODO METTRE DES VALEURS PAR DEFAUT
        // @TODO POUVOIR SAISIR DANS UN TEXTBOX NOM DE LA VILLE A CHERCHER
        lblVille    = new JLabel(weatherInfos.getNomVille());
        lblHumidite = new JLabel(weatherInfos.getHumidite());
        lblTempMax  = new JLabel(weatherInfos.getTempMax());

        // Creation des PANELS, par défaut en flowlayout
        pnlCentre   = new JPanel();
        pnlCentre.setBackground(Color.CYAN);

        pnlNord     = new JPanel();
        pnlEst      = new JPanel();

        // NORD
        pnlNord.add(lblHumidite);

        // CENTRE
        pnlCentre.add(lblVille);
        pnlCentre.add(lblTempMax);

        // Ajout des PANELS au Layout
        this.add(pnlNord,   BorderLayout.NORTH);
        this.add(pnlCentre, BorderLayout.CENTER);

    }
}
