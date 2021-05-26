package ch.hevs.smartphone.applications.gallery;

import javax.swing.*;
import java.awt.*;

public class PhotoView extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Photo
    private Photo photo;

    // STRING
    private String name = "Nom de la photo";

    //Panel
    private JPanel pnlView;

    //ICON
    private Icon iconPhoto = new ImageIcon(photo.getPath());

    //LABEL
    private JLabel lblNamePhoto = new JLabel(name);
    private JLabel lblContentPhoto = new JLabel(iconPhoto);

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public PhotoView(){
        add(buildpnlContentPhotoView());
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private JPanel buildpnlContentPhotoView(){
        pnlView = new JPanel(new BorderLayout());

        pnlView.add(lblNamePhoto,BorderLayout.NORTH);
        pnlView.add(lblContentPhoto,BorderLayout.CENTER);
        return pnlView;
    }
}
