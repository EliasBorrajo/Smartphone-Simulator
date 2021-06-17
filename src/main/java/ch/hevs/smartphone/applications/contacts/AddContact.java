package ch.hevs.smartphone.applications.contacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class constructs the panel for the GUI to add a contact
 *
 * @author Bourquin Jonathan
 */

public class AddContact extends JPanel {

    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Layout
    protected ContactsGUI contactsGUI;
    protected Contact contact;

    // TextField
    protected JTextField tfFirstName;
    protected JTextField tfLastName;
    protected JTextField tfNoPhone;

    // Panel
    private JPanel pnlNorth;
    private JPanel pnlCenter;
    private JPanel pnlSouth;

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
        pnlCenter.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlCenter.add(lblFirstName, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlCenter.add(tfFirstName, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pnlCenter.add(lblLastName, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        pnlCenter.add(tfLastName, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        pnlCenter.add(lblNoPhone, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        pnlCenter.add(tfNoPhone, gridBagConstraints);

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
        lblFirstName = new JLabel("First name");
        lblLastName = new JLabel("Last Name");
        lblNoPhone = new JLabel("Phone number");

        // TextFields
        tfFirstName = new JTextField();
        tfFirstName.setPreferredSize(new Dimension(200,30));
        tfLastName = new JTextField();
        tfLastName.setPreferredSize(new Dimension(200,30));
        tfNoPhone = new JTextField();
        tfNoPhone.setPreferredSize(new Dimension(200,30));
    }

    /**
     * build icon at the desired size
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
    /**
     * set the action listeners to the buttons
     */
    public void setListenerAddContact() {
        btnSave.addActionListener(new ListenerSaveAddContact(tfFirstName, tfLastName, tfNoPhone));

        btnBack.addActionListener(contactsGUI.getMyListener());
    }

    //*****************************************************************************
    // I N N E R  -  C L A S S
    //*****************************************************************************
    /**
     * private class used to save all the textfields in a click
     */
    private class ListenerSaveAddContact implements ActionListener {
        JTextField inputFN;
        JTextField inputN;
        JTextField inputNP;

        /**
         * Constructor
         *
         * @param firstName
         * @param name
         * @param noPhone
         */
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
    public Contact getContact() {
        return contact;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    public void setContact(Contact contact) {
        this.contact = contact;
    }

}



