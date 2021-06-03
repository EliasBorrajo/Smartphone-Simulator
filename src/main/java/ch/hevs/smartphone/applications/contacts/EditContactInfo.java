package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.listeners.ContactListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class EditContactInfo extends JPanel {
    //*****************************************************************************
    // V A R I A B L E S
    //*****************************************************************************
    // PANEL
    private JPanel pnlNorth;
    private JPanel pnlCentre;
    private JPanel pnlSouth;


    // LABEL
    private JButton lblIconContact;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblNoPhone;

    private String firstName = "";
    private String lastName = "";
    private String noPhone = "";

    // TEXT FIELD
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfPhone;

    // BUTTON
    private JButton btnDeleteContact;
    private JButton btnBackEdit;
    private JButton btnSaveEdit;


    // IMAGES
    private ImageIcon iconDefaultContact;
    private ImageIcon iconDefaultBack;
    private String iconPathContactPicture;
    private String iconPathBackPicture;


    // OTHER
    private ShowContactInfo showContactInfo;
    private ArrayList<Contact> contacts;
    private ContactListener myListener;


    //*****************************************************************************
    // C O N S T R U  C T E U R
    //*****************************************************************************
    public EditContactInfo(ShowContactInfo showContactInfo, String firstName, String lastName, String noPhone) {
        this.showContactInfo = showContactInfo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.noPhone = noPhone;
        System.out.println("Edit panel for contact " + firstName);
        //buildpnlShowContactInfo();
        //setListeners();
    }

    /*//*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private void buildpnlShowContactInfo() {
        contacts = contactsGUI.getJsonAddressBook().getContactArray();

        this.setLayout(new BorderLayout());
        this.setBackground(Color.red);

        // Creation Variables
        pnlNorth = new JPanel(new BorderLayout());
        pnlCentre = new JPanel(new BorderLayout());
        pnlSouth = new JPanel(new GridLayout(4,1));

        buildIcon();

        btnDeleteContact = new JButton("Delete Contact");
        btnBackEdit = new JButton("Back");
        btnSaveEdit = new JButton("Edit");
        btnBackEdit = new JButton(iconDefaultBack);

        lblIconContact = new JLabel(iconDefaultContact);
        lblFirstName = new JLabel(firstName);
        lblLastName = new JLabel(lastName);
        lblNoPhone = new JLabel(noPhone);


        /*tfFirstName = new JTextField("");
        tfLastName = new JTextField("");
        tfPhone = new JTextField("");*//*

        // NORTH - Contient l'image du contacte
        pnlNorth.add(btnSaveEdit, BorderLayout.EAST);
        pnlNorth.add(btnBackEdit, BorderLayout.WEST);
        pnlNorth.setPreferredSize(new Dimension(20,20));


        // CENTER - Contien les informations du contacte + Les textBox pour l'édition
        pnlCentre.add(lblIconContact, BorderLayout.CENTER);
        pnlCentre.setPreferredSize(new Dimension(20,20));

        // SOUTH
        pnlSouth.add(pnlSouthNorth);
        pnlSouth.add(pnlSouthCentre);
        pnlSouth.add(pnlSouthSouth);
        pnlSouth.add(btnDeleteContact);
        pnlSouth.setPreferredSize(new Dimension(100,100));


        // CONTENEUR THIS
        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlCentre, BorderLayout.CENTER);
        this.add(pnlSouth, BorderLayout.SOUTH);
    }*/

    /**
     * Crée l'image de l'icone par défaut.
     * On passe par une étape intermédiaire pour pouvoir agrandire l'image.
     */
    /*private void buildIcon() {
        // contactIcon
        iconPathContactPicture = "src/main/resources/ContentIcon/Apps/Contact_Icon.png";
        iconDefaultContact = new ImageIcon(iconPathContactPicture); //Récupère l'image
        Image imageContactIcon = iconDefaultContact.getImage();  // transform it
        Image newImgContactIcon = imageContactIcon.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        iconDefaultContact = new ImageIcon(newImgContactIcon);  // transform it back

        // btn back icon
        iconPathBackPicture = "src/main/resources/HeaderIcon/backIcon.png";
        iconDefaultBack = new ImageIcon(iconPathBackPicture);
        Image imageBackIcon = iconDefaultBack.getImage();
        Image newImgBackIcon = imageBackIcon.getScaledInstance(10,10, java.awt.Image.SCALE_SMOOTH);
        iconDefaultBack = new ImageIcon(newImgBackIcon);
    }

    //*****************************************************************************
    // L I S T E N E R S
    //*****************************************************************************
    protected void setListeners() {
        myListener = new ContactListener(contactsGUI);
        btnBackEdit.addActionListener(myListener);
        btnSaveEdit.addActionListener(myListener);
        btnDeleteContact.addActionListener(myListener);
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public JButton getBtnDeleteContact() {
        return btnDeleteContact;
    }

    public JButton getBtnBackEdit() {
        return btnBackEdit;
    }

    public JButton getBtnSaveEdit() {
        return btnSaveEdit;
    }

    public JTextField getTfFirstName() {
        return tfFirstName;
    }

    public JTextField getTfPhone() {
        return tfPhone;
    }

    public JTextField getTfLastName() {
        return tfLastName;
    }*/

}
