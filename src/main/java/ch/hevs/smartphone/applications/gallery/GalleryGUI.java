package ch.hevs.smartphone.applications.gallery;

import ch.hevs.smartphone.parameters.button.Button;
import ch.hevs.smartphone.parameters.utils.Util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GalleryGUI extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    private GalleryBook gb;
    // PANEL
    private JPanel pnlHomeGallery;
    private JPanel pnlNorth;
    private JPanel panelImage;

    // SCROLLPANE
    private JScrollPane scrollPaneGallery;

    // LABEL
    private JLabel lblTitle;

    // BUTTONS
    private Button btnAddGallery;
    private JButton btnPhoto;
    private int cptBtn = 0;

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
        lblTitle = new JLabel("Gallery");
        btnAddGallery = new Button("+");

        btnAddGallery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = null;
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, GIF & PNG Images", "jpg", "gif", "png");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(chooser);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    path = chooser.getSelectedFile().getPath();
                    Photo photo = new Photo(path);

                    gb.addPhoto(photo);

                    gb.save();
                }
            }
        });

        pnlNorth.add(lblTitle);
        pnlNorth.add(btnAddGallery);
        pnlHomeGallery.add(pnlNorth,BorderLayout.NORTH);
        pnlHomeGallery.add(buildContentJSPane(),BorderLayout.CENTER);
        return pnlHomeGallery;
    }
    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    /**
     * Construit la vue qui affiche les photos
     * @return JScrollPane avec les photos
     */
    private JScrollPane buildContentJSPane(){

        // TODO FAIRE GETTER SETTER POUR TOUTES LES CLASSES

        ArrayList<Photo> photos = this.gb.tabPhoto;

        panelImage = new JPanel(new GridLayout(0,2,5,5));

        // Si il n'y a pas de photos
        if(photos.size() == 0){
            JLabel msg = new JLabel("Gallery is empty");
            panelImage.add(msg);
        }else{
            for(Photo entity : photos){

                Photo photo = (Photo) entity;

                System.out.println(photo.getPath());

                ImageIcon ic = new ImageIcon(photo.getPath());
                ic = Util.getScaledImageIcon(ic, 100);
                btnPhoto = new JButton(ic);
                panelImage.add(btnPhoto);
            }

/*            for(int i = 0; i < photos.size(); i++){

                System.out.println(photos.get(i).getPath());

                btnPhoto[i] = new JButton();

                ImageIcon ic = new ImageIcon(photos.get(i).getPath());
                ic = Util.getScaledImageIcon(ic, 150);

                btnPhoto[i].setIcon(ic);
                panelImage.add(btnPhoto[i]);
            }*/
        }
        scrollPaneGallery = new JScrollPane(panelImage);
        scrollPaneGallery.setPreferredSize(new Dimension(280, 500));
        scrollPaneGallery.setMinimumSize(new Dimension(280, 500));
        scrollPaneGallery.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneGallery.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPaneGallery;
    }

    public JButton getBtnPhoto(){
        return btnPhoto;
    }
}