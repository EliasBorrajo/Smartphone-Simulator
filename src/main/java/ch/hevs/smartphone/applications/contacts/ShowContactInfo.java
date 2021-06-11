package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.listeners.ContactListener;
import ch.hevs.smartphone.parameters.utils.Util;
import ch.hevs.smartphone.structure.layout.ContentLayout;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Jonathan, Elias
 */
public class ShowContactInfo extends JPanel
{
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
    private JLabel lblIconContact;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblNoPhone;

    private String firstName = "";
    private String lastName = "";
    private String noPhone = "";
    private String pathContactPhoto;

    // BUTTON
    private JButton btnDeleteContact;
    private JButton btnBack;
    private JButton btnEdit;

    // IMAGES
    private ImageIcon iconDefaultBack;
    private ImageIcon iconContact;

    // OTHER
    private ContentLayout contentLayout;
    private ArrayList<Contact> contacts;
    private ContactListener myListener;


    //*****************************************************************************
    // C O N S T R U  C T E U R
    //*****************************************************************************
    public ShowContactInfo(ContentLayout contentLayout, String firstName, String lastName, String noPhone, String pathContactPhoto) throws IOException, BusinessException
    {
        this.contentLayout = contentLayout;
        this.firstName = firstName;
        this.lastName = lastName;
        this.noPhone = noPhone;
        this.pathContactPhoto = pathContactPhoto;

        buildIcon();
        buildpnlShowContactInfo();
        setListeners();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private void buildpnlShowContactInfo()
    {

        this.setLayout(new BorderLayout());
        this.setBackground(Color.red);

        // Creation Variables
        pnlNorth = new JPanel(new BorderLayout());
        pnlCentre = new JPanel(new BorderLayout());
        pnlSouth = new JPanel(new GridLayout(4, 1));
        pnlSouthNorth = new JPanel();
        pnlSouthCentre = new JPanel();
        pnlSouthSouth = new JPanel();

        btnDeleteContact = new JButton("Delete Contact");
        btnEdit = new JButton("Edit");
        btnBack = new JButton(iconDefaultBack);

        //iconContact = new ImageIcon(pathContactPhoto);

        lblIconContact = new JLabel(iconContact);
        lblFirstName = new JLabel(firstName);
        lblLastName = new JLabel(lastName);
        lblNoPhone = new JLabel(noPhone);

        pnlSouthNorth.add(lblFirstName);
        pnlSouthCentre.add(lblLastName);
        pnlSouthSouth.add(lblNoPhone);

        // NORTH - Contient l'image du contacte
        pnlNorth.add(btnBack, BorderLayout.WEST);
        pnlNorth.add(btnEdit, BorderLayout.EAST);
        pnlNorth.setPreferredSize(new Dimension(20, 20));


        // CENTER - Contien les informations du contacte + Les textBox pour l'édition
        pnlCentre.add(lblIconContact, BorderLayout.CENTER);
        pnlCentre.setPreferredSize(new Dimension(20, 20));

        // SOUTH
        pnlSouth.add(pnlSouthNorth);
        pnlSouth.add(pnlSouthCentre);
        pnlSouth.add(pnlSouthSouth);
        pnlSouth.add(btnDeleteContact);
        pnlSouth.setPreferredSize(new Dimension(100, 100));

        // HOME PAGE - page d'accueil pour le cardlayout ShowContactInfo
        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlCentre, BorderLayout.CENTER);
        this.add(pnlSouth, BorderLayout.SOUTH);
    }

    /**
     * Crée l'image de l'icone par défaut.
     * On passe par une étape intermédiaire pour pouvoir agrandire l'image.
     */
    private void buildIcon()
    {
        ClassLoader classLoader = getClass().getClassLoader();

        // btn back icon
        iconDefaultBack = new ImageIcon(classLoader.getResource("FooterIcon/backIcon.png"));
        Image imageBackIcon = iconDefaultBack.getImage();
        Image newImgBackIcon = imageBackIcon.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
        iconDefaultBack = new ImageIcon(newImgBackIcon);

        if(pathContactPhoto == null)
        {
            iconContact = new ImageIcon(classLoader.getResource("ContentIcon/Apps/Contact_Icon.png")); //Récupère l'image
            Image imageContactIcon = iconContact.getImage();  // transform it
            Image newImgContactIcon = imageContactIcon.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            iconContact = new ImageIcon(newImgContactIcon);  // transform it back
        }
        else
        {
            System.out.println("PATH CONTACT PHOTO "+pathContactPhoto);
            iconContact = new ImageIcon(pathContactPhoto); //Récupère l'image
            Image imageContactIcon = iconContact.getImage();  // transform it
            //Image newImgContactIcon = imageContactIcon.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            Image newImgContactIcon = imageContactIcon;
            iconContact = new ImageIcon(newImgContactIcon);  // transform it back
            iconContact = Util.getScaledImageIcon(iconContact, 250);

        }
    }

    //*****************************************************************************
    // L I S T E N E R S
    //*****************************************************************************
    protected void setListeners()
    {
        myListener = new ContactListener(contentLayout);
        btnBack.addActionListener(myListener);
        btnEdit.addActionListener(myListener);
        btnDeleteContact.addActionListener(myListener);
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************

    public JPanel getPnlNorth()
    {
        return pnlNorth;
    }

    public JPanel getPnlCentre()
    {
        return pnlCentre;
    }

    public JPanel getPnlSouth()
    {
        return pnlSouth;
    }

    public JPanel getPnlSouthNorth()
    {
        return pnlSouthNorth;
    }

    public JPanel getPnlSouthCentre()
    {
        return pnlSouthCentre;
    }

    public JPanel getPnlSouthSouth()
    {
        return pnlSouthSouth;
    }

    public JLabel getLblIconContact()
    {
        return lblIconContact;
    }

    public JLabel getLblFirstName()
    {
        return lblFirstName;
    }

    public JLabel getLblLastName()
    {
        return lblLastName;
    }

    public JLabel getLblNoPhone()
    {
        return lblNoPhone;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getNoPhone()
    {
        return noPhone;
    }

    public JButton getBtnDeleteContact()
    {
        return btnDeleteContact;
    }

    public JButton getBtnBack()
    {
        return btnBack;
    }

    public JButton getBtnEdit()
    {
        return btnEdit;
    }

    public ImageIcon getIconDefaultBack()
    {
        return iconDefaultBack;
    }

    public ContentLayout getContentLayout()
    {
        return contentLayout;
    }

    public ArrayList<Contact> getContacts()
    {
        return contacts;
    }

    public ContactListener getMyListener()
    {
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
    public void setLblIconContact(JLabel lblIconContact)
    {
        this.lblIconContact = lblIconContact;
    }

    public void setPathContactPhoto(String pathContactPhoto) {
        this.pathContactPhoto = pathContactPhoto;
    }
}