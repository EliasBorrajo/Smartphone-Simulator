package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.listeners.ContactListener;
import ch.hevs.smartphone.applications.contacts.serialization.JSONStorageContact;
import ch.hevs.smartphone.parameters.button.myButton;
import ch.hevs.smartphone.structure.layout.ContentLayout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Bourquin Jonathan
 * Panel principale de l'application du carnet des contacts de type cardLayout
 * Contrôle du fichier JSON et création si manquant
 * Création de l'affichage du carnet des contacts
 * Accès à la class des actionListeners
 */

public class ContactsGUI extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Panel
    // private JPanel pnlContentCardsContact;       // Un pannel de type CardLayout qui va contenir TOUS nos pannels exstant dans l'APP
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
    private ArrayList<Contact> contacts;              // Contient tous les contactes

    // String
    private String[] contactNameShowContact;          // Permet de donner le nom + Prénom à un cotacte
    private String[] contactNameEditContact;
    private String[] contactNoPhone;

    // JSON
    private JSONStorageContact jsonAddressBook;        // Contient le carnet d'adresse dé-sérialisé

    // ActionListener
    private ContactListener myListener;                // Permet d'avoir notre fichier de listeners

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    /**
     * Constructeur
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
    // M E T H O D E S
    //*****************************************************************************
    /**
     * Création initiale des composants GUI pour fonctionner
     */
    public void buildPnlContentContact() {
        // Panel nord
        lblContactTitle = new JLabel("Contacts");
        btnAddContact = new myButton("+");
        pnlNorth = new JPanel();
        pnlNorth.add(lblContactTitle);
        pnlNorth.add(btnAddContact);

        // Panel centre
        scrollPaneContact = new JScrollPane();
        scrollPaneContact = buildScrollPaneContact();

        // Panel qui contient le tout
        pnlHomeContact = new JPanel(new BorderLayout());
        pnlHomeContact.add(pnlNorth, BorderLayout.NORTH);
        pnlHomeContact.add(scrollPaneContact, BorderLayout.CENTER);
    }

    /**
     * Création du fichier JSON
     */
    private void buildJSON() {
        // Dé-sérialer (READ) le fichier JSON
        jsonAddressBook = new JSONStorageContact();
    }

    /**
     * Creation des variables
     */
    private void buildVariables() {
        myListener = new ContactListener(contentLayout);

        contacts = jsonAddressBook.getContactArray();       // On récupère le carnet d'adresse dé-serialisé
        btnShowContact = new JButton[contacts.size()];      // Crée le tableau ayant la taille du nombre de bouttons que l'on possède
        jsonAddressBook.sortDescending(contacts);           // Re-organise notre ArrayListe de contacts


        contactNameShowContact = new String[contacts.size()];
        contactNameEditContact = new String[contacts.size()];

        // construction d'un array de string pour identifier les nouvelles cards edit
        for (int i = 0; i < contacts.size(); i++) {
            contactNameEditContact[i] = contacts.get(i).getFirstName() + " " +
                    contacts.get(i).getLastName() + i;
        }

        contactNoPhone = new String[contacts.size()];


        pnlAddContact = new AddContact(this);

        pnlShowContactInfo = new ShowContactInfo[contacts.size()];
        pnlEditContactInfo = new EditContactInfo[contacts.size()];

        // Création des pannels, pour chaque contacte
        // CREATION des contenus des ARRAYS nécessaires pour les CARDS de contacts
        for (int j = 0; j < contacts.size(); j++) {
            contactNoPhone[j] = contacts.get(j).getNoPhone();

            pnlShowContactInfo[j] = new ShowContactInfo(contentLayout,
                                                        contacts.get(j).getFirstName(),
                                                        contacts.get(j).getLastName(),
                                                        contactNoPhone[j],
                                                        contacts.get(j).getContactPhoto());

            pnlEditContactInfo[j] = new EditContactInfo(contentLayout,
                                                        contacts.get(j).getFirstName(),
                                                        contacts.get(j).getLastName(),
                                                        contacts.get(j).getNoPhone(),
                                                        contacts.get(j).getContactPhoto());
        }
    }

    /**
     * Création du Pannel qui aura une scrollBar et la liste des contactes
     *
     * @return un JscrollPane qui contient un panel de boutons de tous nos contacts
     */
    public JScrollPane buildScrollPaneContact() {
        buildVariables();

        pnlCenterJscrollContact = new JPanel(new GridLayout(0, 1, 5, 5));

        // Si la liste de contacts est vide on affiche un message par défaut
        if (contacts.size() == 0) {
            JLabel emptyContactMessage = new JLabel("No contact to show");
            pnlCenterJscrollContact.add(emptyContactMessage);
        } else {
            // Création des boutons pour chaque contact
            for (int i = 0; i < contacts.size(); i++) {
                contactNameShowContact[i] = contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName();
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
     * Méthode qui contruit le cardLayout
     */
    public void buildCardLayout() {
        cardLayoutContact = new CardLayout();
        this.setLayout(cardLayoutContact);

        this.add("HomeContact", pnlHomeContact);
        this.add("AddContact", pnlAddContact);
        // Création & ajout des cards de contact
        for (int i = 0; i < contacts.size(); i++) {
            this.add(contactNameShowContact[i], pnlShowContactInfo[i]);
            this.add(contactNameEditContact[i], pnlEditContactInfo[i]);
        }
    }

    //*****************************************************************************
    // L I S T E N E R S
    //*****************************************************************************
    public void setListeners() {
        btnAddContact.addActionListener(myListener);

        // Création des ActionListeners en fonction du nombre de contact
        for (int i = 0; i < contacts.size(); i++) {
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

    public JPanel getPnlCenterJscrollContact() { return pnlCenterJscrollContact; }

    public JSONStorageContact getJsonAddressBook() { return jsonAddressBook; }

    public CardLayout getCardLayoutContact() {
        return cardLayoutContact;
    }

    public ContentLayout getContentLayout() {
        return contentLayout;
    }

    public ContactListener getMyListener() {
        return myListener;
    }

    public JPanel getPnlHomeContact() {
        return pnlHomeContact;
    }

    public JPanel getPnlNorth() {
        return pnlNorth;
    }

    public JScrollPane getScrollPaneContact() {
        return scrollPaneContact;
    }

    public AddContact getPnlAddContact() {
        return pnlAddContact;
    }

    public ShowContactInfo[] getPnlShowContactInfo() {
        return pnlShowContactInfo;
    }

    public JLabel getLblContactTitle() {
        return lblContactTitle;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public String[] getContactNameShowContact() {
        return contactNameShowContact;
    }

    public String[] getContactNoPhone() {
        return contactNoPhone;
    }

    public EditContactInfo[] getPnlEditContactInfo() {
        return pnlEditContactInfo;
    }

    public String[] getContactNameEditContact() {
        return contactNameEditContact;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    public void setPnlCenterJscrollContact(JPanel pnlCenterJscrollContact) {
        this.pnlCenterJscrollContact = pnlCenterJscrollContact;
    }
}
