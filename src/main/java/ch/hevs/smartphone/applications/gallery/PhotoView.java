package ch.hevs.smartphone.applications.gallery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhotoView extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    //Gallery
    GalleryBook gb;

    // Photo
    private Photo photo;

    // STRING
    private String name = "Nom de la photo";

    //Panel
    private JPanel pnlNamePhoto = new JPanel();
    private JPanel pnlShowPhoto = new JPanel();
    private JPanel pnlButton = new JPanel();


    //ICON
    private Icon iconPhoto;

    // JTEXTFIELD
    private JTextField tfNamePhoto = new JTextField(name);

    //LABEL
    private JLabel lblNamePhoto = new JLabel("Name :");
    private JLabel lblContentPhoto = new JLabel("Affichage de la photo en question");

    //BUTTONS
    private JButton btnSave = new JButton("Save change");
    private JButton btnDelete = new JButton("Delete photo");

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public PhotoView(){
        setLayout(new BorderLayout());
        add(buildpnlName(),BorderLayout.NORTH);
        add(buildpnlShow(),BorderLayout.CENTER);
        add(buildpnlBtn(),BorderLayout.SOUTH);
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private JPanel buildpnlBtn(){
        pnlButton.add(btnDelete);

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        pnlButton.add(btnSave);
        return pnlButton;
    }

    private JPanel buildpnlShow(){
        pnlShowPhoto.add(lblContentPhoto);
        pnlShowPhoto.setBackground(Color.GREEN);
        return pnlShowPhoto;
    }

    private JPanel buildpnlName(){
        pnlNamePhoto.add(lblNamePhoto);
        pnlNamePhoto.add(tfNamePhoto);
        return pnlNamePhoto;
    }
}
