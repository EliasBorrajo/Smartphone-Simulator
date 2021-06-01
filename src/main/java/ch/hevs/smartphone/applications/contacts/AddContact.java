package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddContact extends JPanel
{

    // PANEL
    private JPanel pnlNorth;
    private JPanel pnlCentre;
    private JPanel pnlSouth;

    // LABEL
    private JLabel lblContactsTitle;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblNoPhone;

    // BUTTON
    JButton btnPhotoContact;
    JButton btnSave;

    // TEXTFIELD
    protected JTextField tfFirstName;
    protected JTextField tfLastName;
    protected JTextField tfNoPhone;

    // OTHER CLASS USED FOR COMPOSITION
    protected ContactsGUI contactsGUI;
    protected Contact contact;

    // TODO fonction pour ajouter une image de la galerie



    //*****************************************************************************
    // C O N S T R  U  C T E U R
    //*****************************************************************************
    public AddContact(ContactsGUI contactsGUI) throws IOException, BusinessException
    {
        this.contactsGUI = contactsGUI;
        buildPnlContent();
    }

    // METHODS

    /**
     * build pnlContent
     * @return JPanel
     */
    private JPanel buildPnlContent() {

        buildPanelAndButton();

        // Parametre du panel
        this.setSize(50, 50);
        this.setLayout(new BorderLayout());

        // Parametre des JPanel
        pnlCentre.setLayout(new GridLayout(3,2));

        // Ajout panels à la Frame
        pnlNorth.add(btnSave);
        this.add(pnlNorth, BorderLayout.NORTH);

        pnlCentre.add(lblFirstName);
        pnlCentre.add(tfFirstName);
        pnlCentre.add(lblLastName);
        pnlCentre.add(tfLastName);
        pnlCentre.add(lblNoPhone);
        pnlCentre.add(tfNoPhone);
        this.add(pnlCentre, BorderLayout.CENTER);

        pnlSouth.setLayout(new GridLayout());
        pnlSouth.add(btnSave);
        this.add(pnlSouth, BorderLayout.SOUTH);

        setListenerAddContact();

        return this;
    }

    /**
     * build panels and buttons needed in buildPnlContent method
     * @return
     */
    public JPanel buildPanelAndButton() {

        // PANEL
        pnlNorth = new JPanel();
        pnlCentre = new JPanel();
        pnlSouth = new JPanel();

        // BUTTON
        btnPhotoContact = new JButton("Add picture");
        btnSave = new JButton("Save");

        // LABEL
        lblContactsTitle = new JLabel("Add new contact");
        lblFirstName = new JLabel("First name : ");
        lblLastName = new JLabel("Last Name : ");
        lblNoPhone = new JLabel("Phone number : ");

        // TEXTFIELD
        tfFirstName = new JTextField("FN", 50);
        tfLastName = new JTextField("N",50);
        tfNoPhone = new JTextField("NP", 50);

        return this;
    }

    //*****************************************************************************
    // L I S T E N E R S
    //*****************************************************************************
    public void setListenerAddContact() {
        btnSave.addActionListener(new ListenerSaveAddContact(tfFirstName, tfLastName, tfNoPhone));
    }

    //*****************************************************************************
    // I N N E R  -  C L A S S
    //*****************************************************************************
    // INNER CLASS
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

            contact = new Contact("","","");


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
            System.out.println("AddContact1");

            try {
                contactsGUI.getJsonAddressBook().write(contactsGUI.getJsonAddressBook().getmyObj(),
                                                contactsGUI.getJsonAddressBook().getContactArray());

                System.out.println(contactsGUI.getJsonAddressBook().getmyObj());
            } catch (BusinessException businessException) {
                businessException.printStackTrace();
            }

            System.out.println("AddContact2");

            inputFN.setText("");
            inputN.setText("");
            inputNP.setText("");

            // Refresh des PANNELS
            contactsGUI.removeAll();
            contactsGUI.validate();

            contactsGUI.buildPnlContentContact();
            contactsGUI.buildCardsLayout();
            contactsGUI.setListeners();

            contactsGUI.revalidate();
            contactsGUI.repaint();


        }
    }
    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public JTextField getTfName()
    {
        return tfLastName;
    }

    public JTextField getTfFirstName()
    {
        return tfFirstName;
    }

    public JTextField getTfNoPhone()
    {
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



