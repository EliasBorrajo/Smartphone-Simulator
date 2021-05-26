package ch.hevs.smartphone.applications.gallery;

import ch.hevs.smartphone.bases.MyButton;
import ch.hevs.smartphone.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GalleryGUI extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // PANEL
    private JPanel pnlHomeGallery;
    private JPanel pnlNorth;
    private JPanel pnlCentre;

    // SCROLLPANE
    private JScrollPane scrollPaneGallery;

    // LABEL
    private JLabel lblTitle;

    // BUTTONS
    private MyButton btnAddGallery;
    private GalleryBook gb;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public GalleryGUI(){
        this.gb = new GalleryBook();
        add(buildpnlContentGallery());
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private JPanel buildpnlContentGallery(){

        pnlHomeGallery = new JPanel(new BorderLayout());

        pnlNorth = new JPanel();
        pnlCentre = new JPanel();
        lblTitle = new JLabel("Gallery");
        btnAddGallery = new MyButton("+");

        pnlCentre.setLayout(new BoxLayout(pnlCentre, BoxLayout.Y_AXIS));
        pnlCentre.setPreferredSize(new Dimension(100, 300));
        pnlCentre.add(this.buildContentPanel());
        pnlNorth.add(lblTitle);
        pnlNorth.add(btnAddGallery);
        pnlHomeGallery.add(pnlNorth,BorderLayout.NORTH);
        pnlHomeGallery.add(pnlCentre,BorderLayout.CENTER);
        //pnlHomeGallery.add(scrollPaneGallery, BorderLayout.CENTER);
        return pnlHomeGallery;
    }
    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************

    public MyButton getBtnAddGallery() {
        return btnAddGallery;
    }

    /**
     * Construit la vue qui affiche les photos
     * @return JScrollPane avec les photos
     */
    private JScrollPane buildContentPanel(){

        // TODO FAIRE GETTER SETTER POUR TOUETS LES CLASSES

        ArrayList<Photo> photos = this.gb.tabPhoto;

        JScrollPane panelPane = new JScrollPane();

        // Si il n'y a pas de photos
        if(photos.size() == 0){

        }else{

            for(Photo entity : photos){

                Photo photo = (Photo) entity;

                System.out.println(photo.toString());

                ImageIcon ic = new ImageIcon(photo.toString());

                //ImageIcon btn = Util.getScaledImageIcon(new ImageIcon(photo.path), 130);

                JLabel panelImage = new JLabel(ic);
//                panelImage.setLayout(new BoxLayout(panelImage, BoxLayout.LINE_AXIS));
//                panelImage.setOpaque(false);
                //panelImage.add(ic);

                //rowPanel.add(panelImage, gbc2);

                //gbc2.gridheight = GridBagConstraints.REMAINDER;

                panelPane.add(panelImage);
            }
        }
        //parent.add(contentPanel);
//        panelPane.setPreferredSize(new Dimension(460, 730));
//        panelPane.setMinimumSize(new Dimension(460, 730));
//        panelPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        panelPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return panelPane;

    }
}