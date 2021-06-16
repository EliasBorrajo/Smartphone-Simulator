package ch.hevs.smartphone.structure;

import ch.hevs.smartphone.parameters.ScreenSizeEnum;
import ch.hevs.smartphone.structure.layout.ContentLayout;
import ch.hevs.smartphone.structure.layout.FooterLayout;
import ch.hevs.smartphone.structure.layout.HeaderLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class which manages our JFrame and the main panel which will contain
 * the HeaderLayout, FooterLayout and ContentLayout
 *
 * @author Lonfat Milena, Borrajo Elias
 */

public class SmartphoneGUI extends JFrame {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Panel
    private JPanel borderPanel;
    private JPanel pnlScreen;

    // Layout
    protected FooterLayout footerLayout;
    protected HeaderLayout headerLayout;
    protected ContentLayout contentLayout;

    // Boolean
    private boolean isOn;

    // Timer
    private java.util.Timer timer;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     */
    public SmartphoneGUI() {
        buildFrame();
        setTimerUpdate();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    /**
     * Frame construction
     */
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

    /**
     * PnlScreen construction
     * @return a JPanel which contains the HeaderLayout, FooterLayout and ContentLayout
     */
    private JPanel builPnlScreen() {
        // Panel which will contain everything
        pnlScreen = new JPanel(new BorderLayout());

        // Create the main structure
        headerLayout = new HeaderLayout(this);
        contentLayout = new ContentLayout();
        footerLayout = new FooterLayout(contentLayout);

        // Add
        pnlScreen.add(headerLayout, BorderLayout.NORTH);
        pnlScreen.add(contentLayout, BorderLayout.CENTER);
        pnlScreen.add(footerLayout, BorderLayout.SOUTH);

        // Add and reation of margins on the side
        pnlScreen.add(this.buildBorderPanel(), BorderLayout.WEST);
        pnlScreen.add(this.buildBorderPanel(), BorderLayout.EAST);

        return pnlScreen;
    }

    /**
     * Edge construction to give the appearance of a smartphone
     * @return a JPanel which will be used to make the edges of our smartphone
     */
    private JPanel buildBorderPanel() {
        borderPanel = new JPanel();
        borderPanel.setBackground(Color.WHITE);
        return borderPanel;
    }


    /**
     * Timer update
     */
    private void setTimerUpdate() {
        timer = new Timer();
        int delay = 800;                                // 800 mili-second delay before get executed
        int delayRepeat = 500;                          // means will be repeated every 5 Mili-seconds
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();                               // We do an update every X time of the timer
            }
        }, delay, delayRepeat);

        // To stop the timer, call : timer.cancel();
    }

    /**
     * Update time and date
     */
    private void update() {
        headerLayout.updateTime();
        headerLayout.updateDate();
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public ContentLayout getContentLayout() {
        return contentLayout;
    }
}