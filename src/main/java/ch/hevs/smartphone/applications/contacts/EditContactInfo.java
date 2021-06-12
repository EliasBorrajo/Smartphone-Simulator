package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.listeners.ContactListener;
import ch.hevs.smartphone.parameters.utils.Util;
import ch.hevs.smartphone.structure.layout.ContentLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EditContactInfo extends JPanel
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
    private JButton btnIconContact;

    // IMAGES
    private ImageIcon iconContact;
    private ImageIcon iconDefaultBack;
    private String pathContactPhoto;
    private String iconPathBackPicture;


    // OTHER
    private ContentLayout contentLayout;
    private ArrayList<Contact> contacts;
    private ContactListener myListener;


    //*****************************************************************************
    // C O N S T R U  C T E U R
    //*****************************************************************************
    public EditContactInfo(ContentLayout contentLayout, String firstName, String lastName, String noPhone, String pathContactPhoto)
    {
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
    private void buildpnlEditContactInfo()
    {
        this.setLayout(new BorderLayout());

        // Creation Variables
        pnlNorth = new JPanel(new BorderLayout());
        pnlCentre = new JPanel(new BorderLayout());
        pnlSouth = new JPanel(new GridLayout(4, 1));
        pnlSouthNorth = new JPanel();
        pnlSouthCentre = new JPanel();
        pnlSouthSouth = new JPanel();

        buildIcon();

        btnBackEdit = new JButton( iconDefaultBack);
        btnSaveEdit = new JButton("Save");
        //iconContact = new ImageIcon(pathContactPhoto);
        //iconContact = contentLayout.getPnlContact().
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

        // NORTH - Contient l'image du contacte
        pnlNorth.add(btnBackEdit, BorderLayout.WEST);
        pnlNorth.add(btnSaveEdit, BorderLayout.EAST);
        pnlNorth.setPreferredSize(new Dimension(20, 20));

        // CENTER - Contien les informations du contacte + Les textBox pour l'édition
        pnlCentre.add(btnIconContact, BorderLayout.CENTER);
        pnlCentre.setPreferredSize(new Dimension(20, 20));

        // SOUTH
        pnlSouth.add(pnlSouthNorth);
        pnlSouth.add(pnlSouthCentre);
        pnlSouth.add(pnlSouthSouth);
        pnlSouth.setPreferredSize(new Dimension(100, 100));

        // HOME PAGE - page d'accueil pour le cardlayout ShowContactInfo
        this.add(pnlNorth,  BorderLayout.NORTH);
        this.add(pnlCentre, BorderLayout.CENTER);
        this.add(pnlSouth,  BorderLayout.SOUTH);

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
        btnIconContact.addActionListener(myListener);
        btnBackEdit.addActionListener(myListener);
        btnSaveEdit.addActionListener(myListener);
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

    public JButton getBtnIconContact()
    {
        return btnIconContact;
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

    public JTextField getTfFirstName()
    {
        return tfFirstName;
    }

    public JTextField getTfLastName()
    {
        return tfLastName;
    }

    public JTextField getTfPhone()
    {
        return tfPhone;
    }

    public JButton getBtnBackEdit()
    {
        return btnBackEdit;
    }

    public JButton getBtnSaveEdit()
    {
        return btnSaveEdit;
    }

    public ImageIcon getIconContact()
    {
        return iconContact;
    }

    public ImageIcon getIconDefaultBack()
    {
        return iconDefaultBack;
    }

    public String getIconPathBackPicture()
    {
        return iconPathBackPicture;
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
}
