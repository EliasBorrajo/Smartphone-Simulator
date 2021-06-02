package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.listeners.ContactListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ShowContactInfo extends JPanel {
    //*****************************************************************************
    // V A R I A B L E S
    //*****************************************************************************
    // PANEL
    private JPanel pnlNorth;
    private JPanel pnlCentre;
    private JPanel pnlSouth;

    // LABEL
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
    private JButton btnBack;
    private JButton btnEdit;
    private JButton btnIcon;

    // IMAGES
    private ImageIcon iconDefaultContact;
    private String iconPath;

    // OTHER
    private ContactsGUI contactsGUI;
    private ArrayList<Contact> contacts;
    private ContactListener myListener;


    //*****************************************************************************
    // C O N S T R U  C T E U R
    //*****************************************************************************
    public ShowContactInfo(ContactsGUI contactsGUI, String firstName, String lastName, String noPhone) throws IOException, BusinessException {
        this.contactsGUI = contactsGUI;
        this.firstName = firstName;
        this.lastName = lastName;
        this.noPhone = noPhone;
        buildpnlShowContactInfo();
        setListeners();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private void buildpnlShowContactInfo() {
        contacts = contactsGUI.getJsonAddressBook().getContactArray();

        this.setLayout(new BorderLayout());
        this.setBackground(Color.red);

        // Creation Variables
        pnlNorth = new JPanel();
        pnlCentre = new JPanel(new GridLayout(3, 2));
        pnlSouth = new JPanel();

        buildIcon();

        btnDeleteContact = new JButton("Delete Contact");
        btnBack = new JButton("Back");
        btnEdit = new JButton("Edit");
        btnIcon = new JButton(iconDefaultContact);

        lblFirstName = new JLabel(firstName);
        lblLastName = new JLabel(lastName);
        lblNoPhone = new JLabel(noPhone);

        tfFirstName = new JTextField("");
        tfLastName = new JTextField("");
        tfPhone = new JTextField("");

        // NORTH - Contient l'image du contacte
        pnlNorth.add(btnIcon);

        // CENTER - Contien les informations du contacte + Les textBox pour l'édition
        pnlCentre.add(lblFirstName);
        pnlCentre.add(tfFirstName);
        pnlCentre.add(lblLastName);
        pnlCentre.add(tfLastName);
        pnlCentre.add(lblNoPhone);
        pnlCentre.add(tfPhone);


        // SOUTH
        pnlSouth.add(btnBack);
        pnlSouth.add(btnEdit);
        pnlSouth.add(btnDeleteContact);

        // CONTENEUR THIS
        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlCentre, BorderLayout.CENTER);
        this.add(pnlSouth, BorderLayout.SOUTH);
    }

    /**
     * Crée l'image de l'icone par défaut.
     * On passe par une étape intermédiaire pour pouvoir agrandire l'image.
     */
    private void buildIcon() {
        iconPath = "src/main/resources/ContentIcon/Apps/Contact_Icon.png";
        iconDefaultContact = new ImageIcon(iconPath); //Récupère l'image
        Image image = iconDefaultContact.getImage();  // transform it
        Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        iconDefaultContact = new ImageIcon(newimg);  // transform it back
    }

    //*****************************************************************************
    // L I S T E N E R S
    //*****************************************************************************
    protected void setListeners() {
        myListener = new ContactListener(contactsGUI);
        btnIcon.addActionListener(myListener);
        btnBack.addActionListener(myListener);
        btnEdit.addActionListener(myListener);
        btnDeleteContact.addActionListener(myListener);
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public JButton getBtnDeleteContact() {
        return btnDeleteContact;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnIcon() {
        return btnIcon;
    }

    public JTextField getTfFirstName() {
        return tfFirstName;
    }

    public JTextField getTfPhone() {
        return tfPhone;
    }

    public JTextField getTfLastName() {
        return tfLastName;
    }
}