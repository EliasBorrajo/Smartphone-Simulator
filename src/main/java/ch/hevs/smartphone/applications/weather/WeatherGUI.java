package ch.hevs.smartphone.applications.weather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class is the GUI that show the weather's information
 * City change possible
 * Gives the name of the city, the maximum, minimum and current temperature and the humidity level
 *
 * @author Borrajo Elias, Milena Lonfat
 */
public class WeatherGUI extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    WeatherAPI weatherInfos;

    // Panel
    private JPanel pnlNorth;
    private JPanel pnlCenter;
    private JPanel pnlCity;
    private JPanel pnlTemp;
    private JPanel pnlMaxTemp;
    private JPanel pnlMinTemp;
    private JPanel pnlHumidity;
    private JPanel pnlNoConnexion;
    private JPanel pnlCountry;

    private JPanel pnlIconWeather;

    // Label
    private JLabel lblCity;
    private JLabel lblTemp;
    private JLabel lblMaxTemp;
    private JLabel lblMinTemp;
    private JLabel lblHumidity;
    private JLabel lblNoConnexion;
    private JLabel lblCountry;
    private JLabel lblIconWeather;

    // Button
    private JButton btnSearch;

    // ImageIcon
    private ImageIcon iconSearch;

    // TextField
    private JTextField tfLocation;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Contructor
     */
    public WeatherGUI() {
        weatherInfos = new WeatherAPI();
        buildMainPanel();
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    /**
     * Set the layout and calls two other methods to build icon and panel
     */
    private void buildMainPanel() {
        setLayout(new BorderLayout());
        buildIcon();
        buildPanel();
    }

    /**
     * Creation of the icon
     */
    private void buildIcon() {
        //Creation icon
        ClassLoader classLoader = getClass().getClassLoader();

        // button search
        iconSearch = new ImageIcon(classLoader.getResource("ContentIcon/Apps/Weather_IconSearch.png"));
        Image imageSearchIcon = iconSearch.getImage();
        Image newImgSearchIcon = imageSearchIcon.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        iconSearch = new ImageIcon(newImgSearchIcon);
    }

    /**
     * Creation of the panel
     */
    private void buildPanel() {
        // Creation JTextField
        tfLocation = new JTextField();
        tfLocation.setPreferredSize(new Dimension(200, 30));

        //Creation button search
        btnSearch = new JButton(iconSearch);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Change URL location
                weatherInfos.setUrlLocation(tfLocation.getText());
                //remettre les valeurs getAPIDetails
                weatherInfos.getAPIDetails();

                // Change information
                weatherInfos.setCityName(weatherInfos.getCityName());
                weatherInfos.setTemperature(weatherInfos.getTemperature());
                weatherInfos.setMaxTemp(weatherInfos.getMaxTemp());
                weatherInfos.setMinTemp(weatherInfos.getMinTemp());
                weatherInfos.setHumidity(weatherInfos.getHumidity());
                weatherInfos.setUrlPicture(weatherInfos.getUrlPicture());
                weatherInfos.setCountry(weatherInfos.getCountry());

                removeAll();
                validate();
                buildPanel();
                revalidate();
                repaint();
            }
        });

        // Creation labels
        lblCity = new JLabel("Location : " + weatherInfos.getCityName());
        lblTemp = new JLabel("Temperature : " + weatherInfos.getTemperature() + "°C");
        lblMaxTemp = new JLabel("Maximum temperature : " + weatherInfos.getMaxTemp() + "°C");
        lblMinTemp = new JLabel("Minimum temperature : " + weatherInfos.getMinTemp() + "°C");
        lblHumidity = new JLabel("Humidity level : " + weatherInfos.getHumidity() + "%");
        lblCountry = new JLabel("Country : " + weatherInfos.getCountry());

        // Icon weather
        try {
            lblIconWeather = new JLabel(new ImageIcon(new URL(weatherInfos.getUrlPicture()) ) );
        } catch (MalformedURLException e1){
            System.out.println("Invalid URL");
        }

        // North panel
        pnlNorth = new JPanel();
        pnlNorth.add(tfLocation);
        pnlNorth.add(btnSearch);

        // Center panel
        pnlCenter = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        pnlCity = new JPanel();
        pnlCity.add(lblCity);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlCenter.add(pnlCity, gridBagConstraints);

        pnlCountry = new JPanel();
        pnlCountry.add(lblCountry);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlCenter.add(pnlCountry, gridBagConstraints);

        pnlTemp = new JPanel();
        pnlTemp.add(lblTemp);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pnlCenter.add(pnlTemp, gridBagConstraints);

        pnlMaxTemp = new JPanel();
        pnlMaxTemp.add(lblMaxTemp);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        pnlCenter.add(pnlMaxTemp, gridBagConstraints);

        pnlMinTemp = new JPanel();
        pnlMinTemp.add(lblMinTemp);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        pnlCenter.add(pnlMinTemp, gridBagConstraints);

        pnlHumidity = new JPanel();
        pnlHumidity.add(lblHumidity);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        pnlCenter.add(pnlHumidity, gridBagConstraints);

        pnlIconWeather = new JPanel();
        pnlIconWeather.add(lblIconWeather);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        pnlCenter.add(pnlIconWeather, gridBagConstraints);

        // Panel if no connexion
        pnlNoConnexion = new JPanel();
        lblNoConnexion = new JLabel("Could not connect to server");
        pnlNoConnexion.add(lblNoConnexion);

        // add panels
        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlCenter, BorderLayout.CENTER);
        this.add(pnlNoConnexion, BorderLayout.SOUTH);

        // Show or hide panel if it's connected or not
        if (!weatherInfos.isConnected()) {
            pnlCenter.setVisible(false);
            pnlNorth.setVisible(true);
            pnlNoConnexion.setVisible(true);
        } else {
            pnlCenter.setVisible(true);
            pnlNorth.setVisible(true);
            pnlNoConnexion.setVisible(false);
        }
    }
}
