package ch.hevs.smartphone.applications.gallery;

import javax.swing.*;
import java.awt.*;

public class AddPhotoGallery extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // PANEL
    private JPanel pnlAddPhoto;
    private JPanel pnlCentre;
    private JTextField txtF;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public AddPhotoGallery(){
        pnlAddPhoto = this;
        setLayout(new BorderLayout());
        pnlCentre = new JPanel();
        txtF = new JTextField("hfjdsklfhadkfjlshIUF");
        pnlCentre.add(txtF);
        add(pnlCentre,BorderLayout.CENTER);
    }
    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************

}