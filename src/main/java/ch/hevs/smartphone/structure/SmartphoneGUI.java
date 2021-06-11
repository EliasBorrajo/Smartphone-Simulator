package ch.hevs.smartphone.structure;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.parameters.ScreenSizeEnum;
import ch.hevs.smartphone.structure.layout.ContentLayout;
import ch.hevs.smartphone.structure.layout.FooterLayout;
import ch.hevs.smartphone.structure.layout.HeaderLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SmartphoneGUI extends JFrame
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // PANEL
    private JPanel borderPanel;
    private JPanel pnlScreen;

    // LAYOUT
    protected FooterLayout footerLayout;
    protected HeaderLayout headerLayout;
    protected ContentLayout contentLayout;

    // VARIABLES
    private boolean isOn;
    private java.util.Timer timer;


    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public SmartphoneGUI() throws IOException, BusinessException
    {
        buildFrame();
        setTimerUpdate();
    }


    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private void buildFrame() throws IOException, BusinessException
    {
        setSize(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEIGHT.getSize());
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 30, 30));

        add(builPnlScreen());
        isOn = true;

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel builPnlScreen() throws IOException, BusinessException
    {
        // Création du pannel qui contiendra TOUT
        pnlScreen = new JPanel(new BorderLayout());
        pnlScreen.setBackground(Color.BLACK);

        // Création de la structure principale
        headerLayout = new HeaderLayout(this);
        footerLayout = new FooterLayout();
        contentLayout = new ContentLayout(footerLayout);

        pnlScreen.add(headerLayout, BorderLayout.NORTH);
        pnlScreen.add(contentLayout, BorderLayout.CENTER);
        pnlScreen.add(footerLayout, BorderLayout.SOUTH);

        // Creation des marges sur le coté
        pnlScreen.add(this.buildBorderPanel(), BorderLayout.WEST);
        pnlScreen.add(this.buildBorderPanel(), BorderLayout.EAST);

        return pnlScreen;
    }

    private JPanel buildBorderPanel()
    {
        borderPanel = new JPanel();
        borderPanel.setBackground(Color.WHITE);
        return borderPanel;
    }


    private void setTimerUpdate()
    {
        timer = new Timer();
        int delay = 800;        // 800 mili-second delay before get executed
        int delayRepeat = 500;  // means will be repeated every 5 Mili-seconds
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                update(); // On fait un update tous les Xtemps du timer
            }
        }, delay, delayRepeat);

        // To stop the timer, call : timer.cancel();
    }

    private void update()
    {
        headerLayout.updateTime();
        headerLayout.updateDate();
    }
    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public FooterLayout getFooterLayout()
    {
        return footerLayout;
    }

    public HeaderLayout getHeaderLayout()
    {
        return headerLayout;
    }

    public ContentLayout getContentLayout()
    {
        return contentLayout;
    }
}