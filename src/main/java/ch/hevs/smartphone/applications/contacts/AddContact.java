package ch.hevs.smartphone.applications.contacts;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

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
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JFormattedTextField tfNoPhone;

    // Panel
    private JPanel pnlNorth;
    private JPanel pnlCenter;
    private JPanel pnlSouth;

    // Label
    private JLabel lblContactsTitle;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblNoPhone;

    private JLabel lblDefaultFirstName;
    private JLabel lblDefaultLastName;
    private JLabel lblDefaultNoPhone;

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
        try {
            buildPanelAndButton();
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
    public void buildPanelAndButton() throws ParseException {
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
        lblNoPhone = new JLabel("<html> Phone number: <br/> (indicative required) <html>");

        // TextFields
        MaskFormatter formatter = new MaskFormatter("+ ## ## ### ## ##");
        formatter.setValidCharacters("0123456789");
        tfFirstName = new JTextField();
        tfLastName = new JTextField();
        tfNoPhone = new JFormattedTextField(formatter);
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
        JFormattedTextField inputNP;

        /**
         * Constructor
         *
         * @param firstName
         * @param name
         * @param noPhone
         */
        public ListenerSaveAddContact(JTextField firstName, JTextField name, JFormattedTextField noPhone) {
            inputFN = firstName;
            inputN = name;
            inputNP = noPhone;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (inputFN.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "first name is empty.");
            }
            else if (inputN.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "name is empty.");
            }
            else if (inputNP.getText().trim().length() == 0 || inputNP.getText().trim().length() < 9) {
                JOptionPane.showMessageDialog(null, "<html>Phone number is empty, not valid or in the wrong format<br/>Please enter an integer<html>");
            } else {
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



