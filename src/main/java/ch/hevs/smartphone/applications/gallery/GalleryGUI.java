package ch.hevs.smartphone.applications.gallery;

import ch.hevs.smartphone.bases.Button;
import ch.hevs.smartphone.bases.ButtonIcon;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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

                    //int index = path.indexOf();
                    //path = path.substring(index);
                    //ImageIcon icon = new ImageIcon(path);
                    //icon = Util.getScaledImageIcon(icon, 250);


                    //this.getButtonPictureChooser().setIcon(icon);
                }

                //this.getContentManager().getGalleryModel().setPathPictureSelected(path);
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

                Icon ic = new ImageIcon(photo.getPath());

                btnPhoto = new JButton(ic);
                btnPhoto.setSize(new Dimension(20,20));
                btnPhoto.setPreferredSize(new Dimension(20,20));
                btnPhoto.setMinimumSize(new Dimension(20,20));
                panelImage.add(btnPhoto);

            }
        }
        scrollPaneGallery = new JScrollPane(panelImage);
        scrollPaneGallery.setPreferredSize(new Dimension(260, 460));
        scrollPaneGallery.setMinimumSize(new Dimension(260, 460));
        scrollPaneGallery.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneGallery.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPaneGallery;
    }

    public JButton getBtnPhoto() {
        return btnPhoto;
    }
}