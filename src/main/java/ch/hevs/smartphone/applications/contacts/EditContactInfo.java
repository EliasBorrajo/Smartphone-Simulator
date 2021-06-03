package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.listeners.ContactListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        private JPanel pnlSouthNorth;
        private JPanel pnlSouthCentre;
        private JPanel pnlSouthSouth;

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
    private JButton btnBackEdit;
    private JButton btnSaveEdit;

    // IMAGES
    private ImageIcon iconDefaultContact;
    private ImageIcon iconDefaultBack;
    private String iconPathContactPicture;
    private String iconPathBackPicture;


    // OTHER
    private ContactsGUI contactsGUI;
    private ArrayList<Contact> contacts;
    private ContactListener myListener;


    //*****************************************************************************
    // C O N S T R U  C T E U R
    //*****************************************************************************
    public EditContactInfo(ContactsGUI contactsGUI, String firstName, String lastName, String noPhone) {
        this.contactsGUI = contactsGUI;
        this.firstName = firstName;
        this.lastName = lastName;
        this.noPhone = noPhone;

        buildpnlEditContactInfo();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private void buildpnlEditContactInfo() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.red);

        // Creation Variables
        pnlNorth = new JPanel(new BorderLayout());
        pnlCentre = new JPanel(new BorderLayout());
        pnlSouth = new JPanel(new GridLayout(4,1));
        /*pnlSouthNorth = new JPanel(new BorderLayout());
        pnlSouthCentre = new JPanel(new BorderLayout());
        pnlSouthSouth = new JPanel(new BorderLayout());*/
        pnlSouthNorth = new JPanel();
        pnlSouthCentre = new JPanel();
        pnlSouthSouth = new JPanel();

        buildIcon();

        btnBackEdit = new JButton(iconDefaultBack);
        btnSaveEdit = new JButton("Save");

        lblIconContact = new JButton(iconDefaultContact);
        lblFirstName = new JLabel(firstName);
        lblLastName = new JLabel(lastName);
        lblNoPhone = new JLabel(noPhone);

        tfFirstName = new JTextField(this.firstName);
        tfLastName = new JTextField(this.lastName);
        tfPhone = new JTextField(this.noPhone);

        pnlSouthNorth.add(lblFirstName);
        pnlSouthNorth.add(tfFirstName);
        pnlSouthCentre.add(lblLastName);
        pnlSouthCentre.add(tfLastName);
        pnlSouthSouth.add(lblNoPhone);
        pnlSouthSouth.add(tfPhone);

        // NORTH - Contient l'image du contacte
        pnlNorth.add(btnBackEdit, BorderLayout.WEST);
        pnlNorth.add(btnSaveEdit, BorderLayout.EAST);
        pnlNorth.setPreferredSize(new Dimension(20,20));


        // CENTER - Contien les informations du contacte + Les textBox pour l'édition
        pnlCentre.add(lblIconContact, BorderLayout.CENTER);
        pnlCentre.setPreferredSize(new Dimension(20,20));

        // SOUTH
        pnlSouth.add(pnlSouthNorth);
        pnlSouth.add(pnlSouthCentre);
        pnlSouth.add(pnlSouthSouth);
        pnlSouth.setPreferredSize(new Dimension(100,100));


        // HOME PAGE - page d'accueil pour le cardlayout ShowContactInfo
        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlCentre, BorderLayout.CENTER);
        this.add(pnlSouth, BorderLayout.SOUTH);

    }

    /**
     * Crée l'image de l'icone par défaut.
     * On passe par une étape intermédiaire pour pouvoir agrandire l'image.
     */
    private void buildIcon() {
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
        /*myListener = new ContactListener(contactsGUI);
        btnBackEdit.addActionListener(myListener);
        btnSaveEdit.addActionListener(myListener);*/
        btnSaveEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("coucou");
            }
        });
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************


    public JPanel getPnlNorth() {
        return pnlNorth;
    }

    public JPanel getPnlCentre() {
        return pnlCentre;
    }

    public JPanel getPnlSouth() {
        return pnlSouth;
    }

    public JPanel getPnlSouthNorth() {
        return pnlSouthNorth;
    }

    public JPanel getPnlSouthCentre() {
        return pnlSouthCentre;
    }

    public JPanel getPnlSouthSouth() {
        return pnlSouthSouth;
    }

    public JButton getLblIconContact() {
        return lblIconContact;
    }

    public JLabel getLblFirstName() {
        return lblFirstName;
    }

    public JLabel getLblLastName() {
        return lblLastName;
    }

    public JLabel getLblNoPhone() {
        return lblNoPhone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNoPhone() {
        return noPhone;
    }

    public JTextField getTfFirstName() {
        return tfFirstName;
    }

    public JTextField getTfLastName() {
        return tfLastName;
    }

    public JTextField getTfPhone() {
        return tfPhone;
    }

    public JButton getBtnBackEdit() {
        return btnBackEdit;
    }

    public JButton getBtnSaveEdit() {
        return btnSaveEdit;
    }

    public ImageIcon getIconDefaultContact() {
        return iconDefaultContact;
    }

    public ImageIcon getIconDefaultBack() {
        return iconDefaultBack;
    }

    public String getIconPathContactPicture() {
        return iconPathContactPicture;
    }

    public String getIconPathBackPicture() {
        return iconPathBackPicture;
    }

    public ContactsGUI getContactsGUI() {
        return contactsGUI;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public ContactListener getMyListener() {
        return myListener;
    }
}
