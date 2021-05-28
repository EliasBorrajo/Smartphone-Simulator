package ch.hevs.smartphone.structure.frame;

import ch.hevs.smartphone.applications.enums.ScreenSizeEnum;
import ch.hevs.smartphone.structure.layout.ContentLayout;
import ch.hevs.smartphone.structure.layout.FooterLayout;
import ch.hevs.smartphone.structure.layout.HeaderLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Timer;
import java.util.TimerTask;

public class SmartphoneGUI extends JFrame {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // PANEL
    private JPanel borderPanel;
    private JPanel pnlScreen;

    // LAYOUT
    FooterLayout fLayout;
    HeaderLayout hLayout;

    // VARIABLES
    private boolean isOn;
    private java.util.Timer timer;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public SmartphoneGUI() {
        buildFrame();
        setTimerUpdate();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private void buildFrame() {
        setSize(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEIGHT.getSize());
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 30, 30));

        add(builPnlScreen());
        isOn = true;

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel builPnlScreen(){
        // Création du pannel qui contiendra TOUT
        pnlScreen = new JPanel(new BorderLayout());
        pnlScreen.setBackground(Color.BLACK);

        // Création de la structure principale
        hLayout = new HeaderLayout();
        fLayout = new FooterLayout();

        pnlScreen.add(hLayout, BorderLayout.NORTH);
        pnlScreen.add(new ContentLayout(fLayout), BorderLayout.CENTER);
        pnlScreen.add(fLayout, BorderLayout.SOUTH);

        // Creation des marges sur le coté
        pnlScreen.add(this.buildBorderPanel(),BorderLayout.WEST);
        pnlScreen.add(this.buildBorderPanel(),BorderLayout.EAST);

        return pnlScreen;
    }

    private JPanel buildBorderPanel() {
        borderPanel = new JPanel();
        borderPanel.setBackground(Color.PINK);
        return  borderPanel;
    }

    private void setTimerUpdate()
    {
        timer = new Timer();
        int delay = 800;        // 800 mili-second delay before get executed
        int delayRepeat = 500;  // means will be repeated every 5 Mili-seconds
        timer.scheduleAtFixedRate(new TimerTask()
        {   @Override
        public void run()
        {
            update(); // On fait un update tous les Xtemps du timer
        }
        },delay, delayRepeat);

        // To stop the timer, call : timer.cancel();
    }

    private void update()
    {
        hLayout.updateTime();
        hLayout.updateDate();
    }
}