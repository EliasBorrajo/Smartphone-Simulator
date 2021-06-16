package ch.hevs.smartphone.applications.contacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Bourquin Jonathan
 * This panel is the GUI to add a contact
 */

public class AddContact extends JPanel {

    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Panel
    private JPanel pnlNorth;
    private JPanel pnlCenter;
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
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     *
     * @param contactsGUI
     */
    public AddContact(ContactsGUI contactsGUI) {
        this.contactsGUI = contactsGUI;
        buildPnlContent();
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    /**
     * buildPnlContent : create all the panels and their contents
     */
    private void buildPnlContent() {
        buildPanelAndButton();

        // Panel Settings
        this.setSize(50, 50);
        this.setLayout(new BorderLayout());

        // North panel
        pnlNorth.add(lblContactsTitle);
        this.add(pnlNorth, BorderLayout.NORTH);

        // Center panel
        pnlCenter.setLayout(new GridLayout(3, 2));
        pnlCenter.add(lblFirstName);
        pnlCenter.add(tfFirstName);

        pnlCenter.add(lblLastName);
        pnlCenter.add(tfLastName);

        pnlCenter.add(lblNoPhone);
        pnlCenter.add(tfNoPhone);
        this.add(pnlCenter, BorderLayout.CENTER);

        // South panel
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
        // Panels
        pnlNorth = new JPanel();
        pnlCenter = new JPanel();
        pnlSouth = new JPanel();

        // Buttons
        buildIcon();
        btnSave = new JButton("Save");
        btnBack = new JButton(iconDefaultBack);

        // Labels
        lblContactsTitle = new JLabel("Add new contact");
        lblFirstName = new JLabel("First name : ");
        lblLastName = new JLabel("Last Name : ");
        lblNoPhone = new JLabel("Phone number : ");

        // TextFields
        tfFirstName = new JTextField("First Name", 50);
        tfLastName = new JTextField("Last Name", 50);
        tfNoPhone = new JTextField("Phone Number", 50);
    }

    /**
     * Construction de l'ICONE à la taille desirée
     */
    private void buildIcon() {
        // Allows to retrieve files from resources
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
    private class ListenerSaveAddContact implements ActionListener {
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



