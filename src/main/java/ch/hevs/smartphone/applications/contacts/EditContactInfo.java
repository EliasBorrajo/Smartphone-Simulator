package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.listeners.ContactListener;
import ch.hevs.smartphone.parameters.utils.Util;
import ch.hevs.smartphone.structure.layout.ContentLayout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * This class is the GUI to edit a contact
 *
 * @author Bourquin Jonathan
 */

public class EditContactInfo extends JPanel {
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
    private JFormattedTextField tfPhone;

    // ActionListener
    private ContactListener myListener;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
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

        try {
            buildPnlEditContactInfo();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setListeners();
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    /**
     * buildpnlEditContactInf : create all the panels and their contents
     */
    private void buildPnlEditContactInfo() throws ParseException {
        this.setLayout(new BorderLayout());

        // Creation Variables
        pnlNorth = new JPanel(new BorderLayout());
        pnlCenter = new JPanel(new BorderLayout());
        pnlSouth = new JPanel(new GridLayout(4, 1));
        pnlSouthNorth = new JPanel();
        pnlSouthCenter = new JPanel();
        pnlSouthSouth = new JPanel();

        buildIcon();

        btnBackEdit = new JButton(iconDefaultBack);
        btnSaveEdit = new JButton("Save");
        btnIconContact = new JButton(iconContact);

        lblFirstName = new JLabel(firstName);
        lblLastName = new JLabel(lastName);
        lblNoPhone = new JLabel(noPhone);

        MaskFormatter formatter = new MaskFormatter("+ ## ### ## ##");
        formatter.setValidCharacters("0123456789");

        tfFirstName = new JTextField();
        tfLastName = new JTextField();
        tfPhone = new JFormattedTextField(formatter);

        pnlSouthNorth.add(lblFirstName);
        pnlSouthNorth.add(tfFirstName);
        pnlSouthCenter.add(lblLastName);
        pnlSouthCenter.add(tfLastName);
        pnlSouthSouth.add(lblNoPhone);
        pnlSouthSouth.add(tfPhone);

        // Panel north - Contains back and save buttons
        pnlNorth.add(btnBackEdit, BorderLayout.WEST);
        pnlNorth.add(btnSaveEdit, BorderLayout.EAST);
        pnlNorth.setPreferredSize(new Dimension(20, 20));

        // Panel Center - Contains contact image button
        pnlCenter.add(btnIconContact, BorderLayout.CENTER);
        pnlCenter.setPreferredSize(new Dimension(20, 20));

        // Panel South - Contains contact information and JTextField for editing
        pnlSouth.add(pnlSouthNorth);
        pnlSouth.add(pnlSouthCenter);
        pnlSouth.add(pnlSouthSouth);
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

        // Contact photo button icon
        if (pathContactPhoto == null) {
            iconContact = new ImageIcon(classLoader.getResource("ContentIcon/Apps/Contact_Icon.png"));                 // Retrieve the image
            Image imageContactIcon = iconContact.getImage();                                                                 // Transform it
            Image newImgContactIcon = imageContactIcon.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH); // Resize
            iconContact = new ImageIcon(newImgContactIcon);                                                                  // Transform it back
        } else {
            iconContact = new ImageIcon(pathContactPhoto);                     // Retrieve the image
            Image imageContactIcon = iconContact.getImage();                   // Transform it
            Image newImgContactIcon = imageContactIcon;
            iconContact = new ImageIcon(newImgContactIcon);                    // Transform it back
            iconContact = Util.getScaledImageIcon(iconContact, 250);    // Resize
        }
    }

    //*****************************************************************************
    // L I S T E N E R S
    //*****************************************************************************
    /**
     * setting listeners for the buttons
     */
    protected void setListeners() {
        myListener = new ContactListener(contentLayout);
        btnIconContact.addActionListener(myListener);
        btnBackEdit.addActionListener(myListener);
        btnSaveEdit.addActionListener(myListener);
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public JButton getBtnIconContact() {
        return btnIconContact;
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
}
