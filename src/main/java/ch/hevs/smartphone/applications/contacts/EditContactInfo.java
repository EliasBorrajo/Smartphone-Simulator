package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.listeners.ContactListener;
import ch.hevs.smartphone.parameters.utils.Util;
import ch.hevs.smartphone.structure.layout.ContentLayout;

import javax.swing.*;
import java.awt.*;

/**
 * @author Bourquin Jonathan
 * Panel qui permet l'édition des informations d'un contact
 */

public class EditContactInfo extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Panel
    private JPanel pnlNorth;
    private JPanel pnlCentre;
    private JPanel pnlSouth;
    private JPanel pnlSouthNorth;
    private JPanel pnlSouthCentre;
    private JPanel pnlSouthSouth;

    // Layout
    private ContentLayout contentLayout;

    // Label
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblNoPhone;

    // Button
    private JButton btnBackEdit;
    private JButton btnSaveEdit;
    private JButton btnIconContact;

    // ImageIcon
    private ImageIcon iconContact;
    private ImageIcon iconDefaultBack;

    // String
    private String firstName = "";
    private String lastName = "";
    private String noPhone = "";
    private String pathContactPhoto;

    // TextField
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfPhone;

    // ActionListener
    private ContactListener myListener;

    //*****************************************************************************
    // C O N S T R U  C T E U R
    //*****************************************************************************
    /**
     * Constructeur
     *
     * @param contentLayout
     * @param firstName
     * @param lastName
     * @param noPhone
     * @param pathContactPhoto
     */
    public EditContactInfo(ContentLayout contentLayout, String firstName, String lastName, String noPhone, String pathContactPhoto) {
        this.contentLayout = contentLayout;
        this.firstName = firstName;
        this.lastName = lastName;
        this.noPhone = noPhone;
        this.pathContactPhoto = pathContactPhoto;

        buildpnlEditContactInfo();
        setListeners();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    /**
     * Création initiale du panel
     */
    private void buildpnlEditContactInfo() {
        this.setLayout(new BorderLayout());

        // Creation Variables
        pnlNorth = new JPanel(new BorderLayout());
        pnlCentre = new JPanel(new BorderLayout());
        pnlSouth = new JPanel(new GridLayout(4, 1));
        pnlSouthNorth = new JPanel();
        pnlSouthCentre = new JPanel();
        pnlSouthSouth = new JPanel();

        buildIcon();

        btnBackEdit = new JButton(iconDefaultBack);
        btnSaveEdit = new JButton("Save");
        btnIconContact = new JButton(iconContact);

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

        // Panel nord - Contient l'image du contacte
        pnlNorth.add(btnBackEdit, BorderLayout.WEST);
        pnlNorth.add(btnSaveEdit, BorderLayout.EAST);
        pnlNorth.setPreferredSize(new Dimension(20, 20));

        // Panel Centre - Contien les informations du contacte + Les textBox pour l'édition
        pnlCentre.add(btnIconContact, BorderLayout.CENTER);
        pnlCentre.setPreferredSize(new Dimension(20, 20));

        // Panel sud
        pnlSouth.add(pnlSouthNorth);
        pnlSouth.add(pnlSouthCentre);
        pnlSouth.add(pnlSouthSouth);
        pnlSouth.setPreferredSize(new Dimension(100, 100));

        // Homepage - page d'accueil pour le cardlayout ShowContactInfo
        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlCentre, BorderLayout.CENTER);
        this.add(pnlSouth, BorderLayout.SOUTH);
    }

    /**
     * Crée l'image de l'icone par défaut.
     * On passe par une étape intermédiaire pour pouvoir agrandire l'image.
     */
    private void buildIcon() {
        ClassLoader classLoader = getClass().getClassLoader();

        // btn back icon
        iconDefaultBack = new ImageIcon(classLoader.getResource("FooterIcon/backIcon.png"));
        Image imageBackIcon = iconDefaultBack.getImage();
        Image newImgBackIcon = imageBackIcon.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
        iconDefaultBack = new ImageIcon(newImgBackIcon);

        if (pathContactPhoto == null) {
            iconContact = new ImageIcon(classLoader.getResource("ContentIcon/Apps/Contact_Icon.png")); //Récupère l'image
            Image imageContactIcon = iconContact.getImage();  // transform it
            Image newImgContactIcon = imageContactIcon.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            iconContact = new ImageIcon(newImgContactIcon);  // transform it back
        } else {
            System.out.println("PATH CONTACT PHOTO " + pathContactPhoto);
            iconContact = new ImageIcon(pathContactPhoto); //Récupère l'image
            Image imageContactIcon = iconContact.getImage();  // transform it
            Image newImgContactIcon = imageContactIcon;
            iconContact = new ImageIcon(newImgContactIcon);  // transform it back
            iconContact = Util.getScaledImageIcon(iconContact, 250);
        }
    }

    //*****************************************************************************
    // L I S T E N E R S
    //*****************************************************************************
    protected void setListeners() {
        myListener = new ContactListener(contentLayout);
        btnIconContact.addActionListener(myListener);
        btnBackEdit.addActionListener(myListener);
        btnSaveEdit.addActionListener(myListener);
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

    public JButton getBtnIconContact() {
        return btnIconContact;
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

    public ImageIcon getIconContact() {
        return iconContact;
    }

    public ImageIcon getIconDefaultBack() {
        return iconDefaultBack;
    }

    public ContentLayout getContentLayout() {
        return contentLayout;
    }

    public ContactListener getMyListener() {
        return myListener;
    }
}
