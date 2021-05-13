package ch.hevs.smartphone.Smartphone;

import ch.hevs.smartphone.Bases.ScreenSizeEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class SmartphoneGUI extends JFrame {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // PANEL
    private JPanel borderPanel;
    private JPanel pnlScreen;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public SmartphoneGUI() {
        init();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private void init() {
        setSize(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEIGHT.getSize());
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 30, 30));

        add(builPnlScreen());

        setVisible(true);
        setLocationRelativeTo(null);       // La fenêtre souvre au centre de l'écran
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel builPnlScreen(){
        pnlScreen = new JPanel(new BorderLayout());
        pnlScreen.setBackground(Color.BLACK);

        pnlScreen.add(new HeaderLayout(), BorderLayout.NORTH);
        pnlScreen.add(new ContentLayout(), BorderLayout.CENTER);
        pnlScreen.add(new FooterLayout(), BorderLayout.SOUTH);
        pnlScreen.add(this.buildBorderPanel(),BorderLayout.WEST);
        pnlScreen.add(this.buildBorderPanel(),BorderLayout.EAST);

        return pnlScreen;
    }

    private JPanel buildBorderPanel() {
        borderPanel = new JPanel();
        borderPanel.setBackground(Color.PINK);
        return  borderPanel;
    }
}