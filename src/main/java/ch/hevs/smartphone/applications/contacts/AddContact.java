package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author Bourquin Jonathan
 * Panel qui permet d'ajouter un contact
 */

public class AddContact extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Panel
    private JPanel pnlNorth;
    private JPanel pnlCentre;
    private JPanel pnlSouth;

    // Layout
    protected ContactsGUI contactsGUI;
    protected Contact contact;

    // Label
    private JLabel lblContactsTitle;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblNoPhone;

    // Button
    private JButton btnPhotoContact;
    private JButton btnSave;
    private JButton btnBack;

    // ImageIcon
    private ImageIcon iconDefaultBack;

    // String
    private String pathPhoto;

    // TextField
    protected JTextField tfFirstName;
    protected JTextField tfLastName;
    protected JTextField tfNoPhone;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    /**
     * Constructeur
     *
     * @param contactsGUI
     * @throws IOException
     * @throws BusinessException
     */
    public AddContact(ContactsGUI contactsGUI) throws IOException, BusinessException {
        this.contactsGUI = contactsGUI;
        buildPnlContent();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    /**
     * build pnlContent va créer tous les Pannels et leurs contenus
     */
    private void buildPnlContent() {
        buildPanelAndButton();

        // Parametre du panel
        this.setSize(50, 50);
        this.setLayout(new BorderLayout());

        // Ajout panels à la Frame
        // Panel nord
        pnlNorth.add(btnSave);
        this.add(pnlNorth, BorderLayout.NORTH);

        // Panel centre
        // Parametre des JPanel
        pnlCentre.setLayout(new GridLayout(3, 2));
        pnlCentre.add(lblFirstName);
        pnlCentre.add(tfFirstName);

        pnlCentre.add(lblLastName);
        pnlCentre.add(tfLastName);

        pnlCentre.add(lblNoPhone);
        pnlCentre.add(tfNoPhone);
        this.add(pnlCentre, BorderLayout.CENTER);

        // Panel sud
        pnlSouth.setLayout(new GridLayout());
        pnlSouth.add(btnBack);
        pnlSouth.add(btnSave);
        this.add(pnlSouth, BorderLayout.SOUTH);

        setListenerAddContact();
    }

    /**
     * build panels and buttons needed in buildPnlContent method
     */
    public void buildPanelAndButton() {
        // Panel
        pnlNorth = new JPanel();
        pnlCentre = new JPanel();
        pnlSouth = new JPanel();

        // Button
        buildIcon();
        btnPhotoContact = new JButton("Add picture");
        btnSave = new JButton("Save");
        btnBack = new JButton(iconDefaultBack);

        // Label
        lblContactsTitle = new JLabel("Add new contact");
        lblFirstName = new JLabel("First name : ");
        lblLastName = new JLabel("Last Name : ");
        lblNoPhone = new JLabel("Phone number : ");

        // TextField
        tfFirstName = new JTextField("First Name", 50);
        tfLastName = new JTextField("Last Name", 50);
        tfNoPhone = new JTextField("Phone Number", 50);
    }

    /**
     * Construction de l'ICONE à la taille desirée
     */
    private void buildIcon() {
        // Permet de récuperer les fichiers des ressources
        ClassLoader classLoader = getClass().getClassLoader();

        iconDefaultBack = new ImageIcon(classLoader.getResource("FooterIcon/backIcon.png"));
        Image imageBackIcon = iconDefaultBack.getImage();
        Image newImgBackIcon = imageBackIcon.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
        iconDefaultBack = new ImageIcon(newImgBackIcon);
    }

    //*****************************************************************************
    // L I S T E N E R S
    //*****************************************************************************
    public void setListenerAddContact() {
        btnSave.addActionListener(new ListenerSaveAddContact(tfFirstName, tfLastName, tfNoPhone));

        btnBack.addActionListener(contactsGUI.getMyListener());
    }

    //*****************************************************************************
    // I N N E R  -  C L A S S
    //*****************************************************************************
    class ListenerSaveAddContact implements ActionListener {
        JTextField inputFN;
        JTextField inputN;
        JTextField inputNP;

        public ListenerSaveAddContact(JTextField firstName, JTextField name, JTextField noPhone) {
            inputFN = firstName;
            inputN = name;
            inputNP = noPhone;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            contact = new Contact("", "", "", pathPhoto);

            String text1 = "";
            text1 = inputFN.getText();
            contact.setFirstName(text1);

            String text2 = "";
            text2 = inputN.getText();
            contact.setLastName(text2);

            String text3 = "";
            text3 = inputNP.getText();
            contact.setNoPhone(text3);

            System.out.println(contact);

            contactsGUI.getJsonAddressBook().addContact(contact);
            contactsGUI.getJsonAddressBook().sortDescending(contactsGUI.getJsonAddressBook().getContactArray()); // trie l'Arraylist contacts par ordre alphabétique

            System.out.println("AddContact2");

            inputFN.setText("");
            inputN.setText("");
            inputNP.setText("");

            // Refresh des panels
            contactsGUI.removeAll();
            contactsGUI.validate();

            contactsGUI.buildPnlContentContact();
            contactsGUI.buildCardLayout();
            contactsGUI.setListeners();

            contactsGUI.revalidate();
            contactsGUI.repaint();

        }

    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public JTextField getTfName() {
        return tfLastName;
    }

    public JTextField getTfFirstName() {
        return tfFirstName;
    }

    public JTextField getTfNoPhone() {
        return tfNoPhone;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public ContactsGUI getContactsGUI() {
        return contactsGUI;
    }

    public Contact getContact() {
        return contact;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    public void setContactsGUI(ContactsGUI contactsGUI) {
        this.contactsGUI = contactsGUI;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}



