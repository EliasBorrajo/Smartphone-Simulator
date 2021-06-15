package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.listeners.ContactListener;
import ch.hevs.smartphone.parameters.utils.Util;
import ch.hevs.smartphone.structure.layout.ContentLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Bourquin Jonathan, Borrajo Elias
 * This class is the GUI that shows the information of a contact
 */

public class ShowContactInfo extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Panel
    private JPanel pnlNorth;
    private JPanel pnlCenter;
    private JPanel pnlSouth;
    private JPanel pnlSouthNorth;
    private JPanel pnlSouthCenter;
    private JPanel pnlSouthSouth;

    // Layout
    private ContentLayout contentLayout;

    // Label
    private JLabel lblIconContact;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblNoPhone;

    // String
    private String firstName = "";
    private String lastName = "";
    private String noPhone = "";
    private String pathContactPhoto;

    // Button
    private JButton btnDeleteContact;
    private JButton btnBack;
    private JButton btnEdit;

    // ImageIcon
    private ImageIcon iconDefaultBack;
    private ImageIcon iconContact;

    // ArrayList
    private ArrayList<Contact> contacts;

    // ActionListener
    private ContactListener myListener;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     * @param contentLayout
     * @param firstName
     * @param lastName
     * @param noPhone
     * @param pathContactPhoto
     * */
    public ShowContactInfo(ContentLayout contentLayout, String firstName, String lastName, String noPhone, String pathContactPhoto) {
        this.contentLayout = contentLayout;
        this.firstName = firstName;
        this.lastName = lastName;
        this.noPhone = noPhone;
        this.pathContactPhoto = pathContactPhoto;

        buildIcon();
        buildPnlShowContactInfo();
        setListeners();
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    /**
     * buildPnlShowContactInfo : create all the panels and their contents
     * */
    private void buildPnlShowContactInfo() {

        this.setLayout(new BorderLayout());

        // Creation variables
        pnlNorth = new JPanel(new BorderLayout());
        pnlCenter = new JPanel(new BorderLayout());
        pnlSouth = new JPanel(new GridLayout(4, 1));
        pnlSouthNorth = new JPanel();
        pnlSouthCenter = new JPanel();
        pnlSouthSouth = new JPanel();

        btnDeleteContact = new JButton("Delete Contact");
        btnEdit = new JButton("Edit");
        btnBack = new JButton(iconDefaultBack);

        lblIconContact = new JLabel(iconContact);
        lblFirstName = new JLabel(firstName);
        lblLastName = new JLabel(lastName);
        lblNoPhone = new JLabel(noPhone);

        pnlSouthNorth.add(lblFirstName);
        pnlSouthCenter.add(lblLastName);
        pnlSouthSouth.add(lblNoPhone);

        // North panel - Contains back and edit buttons
        pnlNorth.add(btnBack, BorderLayout.WEST);
        pnlNorth.add(btnEdit, BorderLayout.EAST);
        pnlNorth.setPreferredSize(new Dimension(20, 20));

        // Center panel - Contains contact image
        pnlCenter.add(lblIconContact, BorderLayout.CENTER);
        pnlCenter.setPreferredSize(new Dimension(20, 20));

        // South panel - Contains contact information
        pnlSouth.add(pnlSouthNorth);
        pnlSouth.add(pnlSouthCenter);
        pnlSouth.add(pnlSouthSouth);
        pnlSouth.add(btnDeleteContact);
        pnlSouth.setPreferredSize(new Dimension(100, 100));

        // Contains the three panels
        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlCenter, BorderLayout.CENTER);
        this.add(pnlSouth, BorderLayout.SOUTH);
    }

    /**
     * Creates the image of the default icon and the image of button back
     * We go through an intermediate step to be able to enlarge the image
     */
    private void buildIcon() {
        ClassLoader classLoader = getClass().getClassLoader();

        // Back button icon
        iconDefaultBack = new ImageIcon(classLoader.getResource("FooterIcon/backIcon.png"));
        Image imageBackIcon = iconDefaultBack.getImage();
        Image newImgBackIcon = imageBackIcon.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
        iconDefaultBack = new ImageIcon(newImgBackIcon);

        // Contact photo icon
        if (pathContactPhoto == null) {
            iconContact = new ImageIcon(classLoader.getResource("ContentIcon/Apps/Contact_Icon.png")); //Récupère l'image
            Image imageContactIcon = iconContact.getImage();        // transform it
            Image newImgContactIcon = imageContactIcon.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            iconContact = new ImageIcon(newImgContactIcon);         // transform it back
        } else {
            System.out.println("PATH CONTACT PHOTO DE SHOW CONTACT INFO " + pathContactPhoto);
            iconContact = new ImageIcon(pathContactPhoto);          //Récupère l'image
            Image imageContactIcon = iconContact.getImage();        // transform it
            Image newImgContactIcon = imageContactIcon;
            iconContact = new ImageIcon(newImgContactIcon);         // transform it back
            iconContact = Util.getScaledImageIcon(iconContact, 250);

        }
    }

    //*****************************************************************************
    // L I S T E N E R S
    //*****************************************************************************
    protected void setListeners() {
        myListener = new ContactListener(contentLayout);
        btnBack.addActionListener(myListener);
        btnEdit.addActionListener(myListener);
        btnDeleteContact.addActionListener(myListener);
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public JPanel getPnlNorth() {
        return pnlNorth;
    }

    public JPanel getPnlCenter() {
        return pnlCenter;
    }

    public JPanel getPnlSouth() {
        return pnlSouth;
    }

    public JPanel getPnlSouthNorth() {
        return pnlSouthNorth;
    }

    public JPanel getPnlSouthCenter() {
        return pnlSouthCenter;
    }

    public JPanel getPnlSouthSouth() {
        return pnlSouthSouth;
    }

    public JLabel getLblIconContact() {
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

    public JButton getBtnDeleteContact() {
        return btnDeleteContact;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public ImageIcon getIconDefaultBack() {
        return iconDefaultBack;
    }

    public ContentLayout getContentLayout() {
        return contentLayout;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public ContactListener getMyListener() {
        return myListener;
    }

    public String getPathContactPhoto() {
        return pathContactPhoto;
    }

    public ImageIcon getIconContact() {
        return iconContact;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    public void setLblIconContact(JLabel lblIconContact) {
        this.lblIconContact = lblIconContact;
    }

    public void setPathContactPhoto(String pathContactPhoto) {
        this.pathContactPhoto = pathContactPhoto;
    }
}