package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.listeners.ContactListener;
import ch.hevs.smartphone.applications.contacts.serialization.JSONStorageContact;
import ch.hevs.smartphone.parameters.button.myButton;
import ch.hevs.smartphone.structure.layout.ContentLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Main panel of the cardlayout contact application
 * Json file check and creation if missing
 * Creat the contact book display
 * Access to the actionListeners class
 *
 * @author Bourquin Jonathan
 */

public class ContactsGUI extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Panel
    private JPanel pnlHomeContact;                  // Pannel d'accueil BRODERLAYOUT
    private JPanel pnlNorth;                        // Pannel Nord comprenant le boutton + et tu texte
    private JPanel pnlCenterJscrollContact;         // Pannel comprenant le SCROLL
    private AddContact pnlAddContact;               // Card de l'app contactes
    private ShowContactInfo[] pnlShowContactInfo;   // Cards des contactes que l'utilisateur va ajouter
    private EditContactInfo[] pnlEditContactInfo;

    // Layout
    private CardLayout cardLayoutContact;            // Contiendra les différents cards de l'application
    private ContentLayout contentLayout;             // Permet de récuperer & d'utiliser le content layout dans l'app

    // JScrollPane
    private JScrollPane scrollPaneContact;           // Pannel comprenant les Bouttons des contactes

    // Label
    private JLabel lblContactTitle;

    // Button
    private myButton btnAddContact;                   // boutton dans le pnlNorth
    private JButton[] btnShowContact;                 // Les bouttons du ScrollPaneContact

    // ArrayList
    private ArrayList<Contact> contactArray;              // Contient tous les contactes

    // String
    private String[] contactNameShowContact;          // Permet de donner le nom + Prénom à un cotacte
    private String[] contactNameEditContact;
    private String[] contactNoPhone;

    // JSON
    private JSONStorageContact jsonAddressBook;        // Contient le carnet d'adresse dé-sérialisé

    // ActionListener
    private ContactListener myListener;                // Permet d'avoir notre fichier de listeners

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     *
     * @param contentLayout
     */
    public ContactsGUI(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
        buildJSON();
        buildPnlContentContact();
        buildCardLayout();
        setListeners();
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    /**
     * buildPnlContentContact : create all the panels and their contents
     */
    public void buildPnlContentContact() {
        // North panel
        lblContactTitle = new JLabel("Contacts");
        btnAddContact = new myButton("+");
        pnlNorth = new JPanel();
        pnlNorth.add(lblContactTitle);
        pnlNorth.add(btnAddContact);

        // Center panel
        scrollPaneContact = new JScrollPane();
        scrollPaneContact = buildScrollPaneContact();

        // Panel that contains everything
        pnlHomeContact = new JPanel(new BorderLayout());
        pnlHomeContact.add(pnlNorth, BorderLayout.NORTH);
        pnlHomeContact.add(scrollPaneContact, BorderLayout.CENTER);
    }

    /**
     * Creating the JSON file
     */
    private void buildJSON() {
        // Deserialize (READ) the Json file
        jsonAddressBook = new JSONStorageContact();
    }

    /**
     * Creation variables
     */
    private void buildVariables() {
        myListener = new ContactListener(contentLayout);

        contactArray = jsonAddressBook.getContactArray();       // Recover the deserialized address book
        btnShowContact = new JButton[contactArray.size()];      // Create the array of buttons having the size of our address book
        jsonAddressBook.sortDescending(contactArray);           // Reorganize the ArrayList of contacts


        contactNameShowContact = new String[contactArray.size()];
        contactNameEditContact = new String[contactArray.size()];

        // Construction of a string array to identify new cards edit
       for (int i = 0; i < contactArray.size(); i++) {
            contactNameEditContact[i] = contactArray.get(i).getFirstName() + " " +
                    contactArray.get(i).getLastName() + i;
        }

        contactNoPhone = new String[contactArray.size()];

        pnlAddContact = new AddContact(this);

        pnlShowContactInfo = new ShowContactInfo[contactArray.size()];
        pnlEditContactInfo = new EditContactInfo[contactArray.size()];

        // Creation of panels for each contact
        // Creation of the contents of the ARRAYS necessary for the contact cards
        for (int j = 0; j < contactArray.size(); j++) {
            contactNoPhone[j] = contactArray.get(j).getNoPhone();

            pnlShowContactInfo[j] = new ShowContactInfo(contentLayout,
                                                        contactArray.get(j).getFirstName(),
                                                        contactArray.get(j).getLastName(),
                                                        contactNoPhone[j],
                                                        contactArray.get(j).getContactPhoto());

            pnlEditContactInfo[j] = new EditContactInfo(contentLayout,
                                                        contactArray.get(j).getFirstName(),
                                                        contactArray.get(j).getLastName(),
                                                        contactArray.get(j).getNoPhone(),
                                                        contactArray.get(j).getContactPhoto());
        }
    }

    /**
     * Creation of the ScrollBar which has the list of contact buttons
     *
     * @return ScrollPane which has the list of contact button
     */
    public JScrollPane buildScrollPaneContact() {
        buildVariables();

        pnlCenterJscrollContact = new JPanel(new GridLayout(0, 1, 5, 5));

        // If the contact list is empty, a default message is displayed
        if (contactArray.size() == 0) {
            JLabel emptyContactMessage = new JLabel("No contact to show");
            pnlCenterJscrollContact.add(emptyContactMessage);
        } else {
            // Creation of buttons for each contact
            for (int i = 0; i < contactArray.size(); i++) {
                contactNameShowContact[i] = contactArray.get(i).getFirstName() + " " + contactArray.get(i).getLastName();
                btnShowContact[i] = new JButton(contactNameShowContact[i]);
                pnlCenterJscrollContact.add(btnShowContact[i]);
            }
        }
        scrollPaneContact = new JScrollPane(pnlCenterJscrollContact);
        scrollPaneContact.setPreferredSize(new Dimension(280, 500));
        scrollPaneContact.setMinimumSize(new Dimension(280, 500));
        scrollPaneContact.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneContact.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        return scrollPaneContact;
    }

    /**
     * Method that builds the cardLayout
     */
    public void buildCardLayout() {
        cardLayoutContact = new CardLayout();
        this.setLayout(cardLayoutContact);

        this.add("HomeContact", pnlHomeContact);
        this.add("AddContact", pnlAddContact);
        // Création & ajout des cards de contact
        for (int i = 0; i < contactArray.size(); i++) {
            this.add(contactNameShowContact[i], pnlShowContactInfo[i]);
            this.add(contactNameEditContact[i], pnlEditContactInfo[i]);
        }
    }

    //*****************************************************************************
    // L I S T E N E R S
    //*****************************************************************************
    /**
     * setting listeners for the buttons
     */
    public void setListeners() {
        btnAddContact.addActionListener(myListener);

        // Creation of ActionListeners according to the number of contacts
        for (int i = 0; i < contactArray.size(); i++) {
            btnShowContact[i].addActionListener(myListener);
        }
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public myButton getBtnAddContact() {
        return btnAddContact;
    }

    public JButton[] getBtnShowContact() {
        return btnShowContact;
    }

    public JSONStorageContact getJsonAddressBook() { return jsonAddressBook; }

    public CardLayout getCardLayoutContact() {
        return cardLayoutContact;
    }

    public ContactListener getMyListener() {
        return myListener;
    }

    public AddContact getPnlAddContact() {
        return pnlAddContact;
    }

    public ShowContactInfo[] getPnlShowContactInfo() {
        return pnlShowContactInfo;
    }

    public ArrayList<Contact> getContactArray() {
        return contactArray;
    }

    public String[] getContactNameShowContact() {
        return contactNameShowContact;
    }

    public EditContactInfo[] getPnlEditContactInfo() {
        return pnlEditContactInfo;
    }

    public String[] getContactNameEditContact() {
        return contactNameEditContact;
    }
}
