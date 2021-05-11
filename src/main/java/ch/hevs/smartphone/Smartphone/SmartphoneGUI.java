package ch.hevs.smartphone.Smartphone;

import ch.hevs.smartphone.Bases.ScreenSizeEnum;

import javax.swing.*;
import java.awt.*;

public class SmartphoneGUI extends JFrame {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // private static final long serilVersionUID = 1L; ????

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public SmartphoneGUI(){
        this.setSize(ScreenSizeEnum.WIDTH.getSize(),ScreenSizeEnum.HEIGHT.getSize());

        //this.setUndecorated(true);      // Une fenêtre peut être débarrassée de sa décoration : pas de bordure, pas de titre, pas de boutons fermer et iconifier
                                        // Ceci n'est possible que sur une fenêtre qui n'est pas displayable,
                                        // c'est à dire une fenêtre sur laquelle on n'a pas encore exécuté un pack ou un setVisible(true)

        //this.setShape(new RoundRectangle2D.Double(0,0,this.getWidth(), this.getHeight(),30,30)); // Bord arrondi

        // Panel de base, fond noir avec bordures
        JPanel pnlScreen = new JPanel(new BorderLayout());
        pnlScreen.setBackground(Color.BLACK);

        pnlScreen.add(new HeaderLayout(), BorderLayout.NORTH);
        pnlScreen.add(new ContentLayout(), BorderLayout.CENTER);
        pnlScreen.add(new FooterLayout(), BorderLayout.SOUTH);

        // @TODO: A FAIRE PLUS TARD POUR AVOIR LES BORDURES
        //pnlScreen.add(this.buildBorderPanel(),BorderLayout.WEST);
        //pnlScreen.add(this.buildBorderPanel(),BorderLayout.EAST);

        this.setContentPane(pnlScreen);

        this.setLocationRelativeTo(null);       // La fenêtre souvre au centre de l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************

}
