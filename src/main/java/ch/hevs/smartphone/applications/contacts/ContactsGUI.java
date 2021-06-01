package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.serialization.JSONStorageContact;
import ch.hevs.smartphone.parameters.button.Button;
import ch.hevs.smartphone.structure.layout.ContentLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ContactsGUI extends JPanel
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // LAYOUTS
    private CardLayout cardLayoutContact;        // Contiendra les différents cards de l'application
    private ContentLayout cl;               // Permet de récuperer & d'utiliser le content layout dans l'app

    // PANEL
    private JPanel pnlContentCardsContact;           // panel conteneur pour avoir accès au cardlayout depuis les listeners
    private JPanel pnlHomeContact;              // Pannel d'accueil BRODERLAYOUT
        private JPanel pnlNorth;                // Pannel Nord comprenant le boutton + et tu texte
        private JPanel pnlCenterJscrollContact; // Pannel comprenant le SCROLL
        private JScrollPane scrollPaneContact;  // Pannel comprenant les Bouttons des contactes

    private AddContact pnlAddContact;               // Card de l'app contactes
    private ShowContactInfo[] pnlShowContactInfo;   // Cards des contactes que l'utilisateur va ajouter

    // LABEL
    private JLabel lblContactTitle;

    // BUTTONS
    private Button btnAddContact;               // boutton dans le pnlNorth
    private JButton[] btnShowContacts;          // Les bouttons du ScrollPaneContact

    // OTHER OBJECTS
    private JSONStorageContact jsonAddressBook; // Contient le carnet d'adresse dé-sérialisé
    private ArrayList<Contact> contacts;        // Contient tous les contactes
    private String[] contactName;                 // Permet de donner le nom + Prénom à un cotacte
    private String[] contactNoPhone;

    String currentPanel = "";

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public ContactsGUI(ContentLayout cl)
    {
        this.cl = cl;

        buildPnlContentContact();
        buildCardsLayout();
        setListeners();
    }


    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************

    /**
     * Création initiale des composants GUI pour fonctionner
     */
    public void buildPnlContentContact()
    {
        pnlContentCardsContact = this;

        // NORTH
        lblContactTitle = new JLabel("Contacts");
        btnAddContact = new Button("+");
        pnlNorth = new JPanel();
        pnlNorth.add(lblContactTitle);
        pnlNorth.add(btnAddContact);

        //CENTER
        scrollPaneContact = new JScrollPane();
        scrollPaneContact = buildScrollPaneContact();
        scrollPaneContact.setBackground(Color.CYAN);
        /*pnlCentre.add(scrollPaneContact);
        pnlCentre.add*/

        // HOME PAGE
        pnlHomeContact = new JPanel(new BorderLayout());
        pnlHomeContact.add(pnlNorth,          BorderLayout.NORTH);
        pnlHomeContact.add(scrollPaneContact, BorderLayout.CENTER);

    }

    /**
     * Creation de toutes nos différentes variables
     */
    private void buildVariables()
    {
        try // Essaye de dé-sérialer (READ) le fichier JSON
        {
            jsonAddressBook = new JSONStorageContact();
        }
        catch (IOException | BusinessException e)
        {
            e.printStackTrace();
            System.out.println("Failed to crate adressBook in CONTACTS GUI");
        }

        contacts = jsonAddressBook.getContactArray();       // On récupère le carnet d'adresse dé-serialisé
        btnShowContacts = new JButton[contacts.size()];     // Crée le tableau ayant la taille du nombre de bouttons que l'on possède
        jsonAddressBook.sortDescending(contacts);           // Re-organise notre ArrayListe de contacts


        contactName     = new String[contacts.size()];
        contactNoPhone  = new String[contacts.size()];

        try // Essaye de créer un pannel pour l'ajout des contactes
        {
            pnlAddContact = new AddContact(this);
        } catch (IOException | BusinessException e)
        {
            e.printStackTrace();
        }

        pnlShowContactInfo = new ShowContactInfo[contacts.size()];

        // CREATION des contenus des ARRAYS nécessaires pour les CARDS de contacts
        for (int i = 0; i < contacts.size(); i++)
        {
            contactName[i] = contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName();
            contactNoPhone[i] = contacts.get(i).getNoPhone();
            try
            {
                pnlShowContactInfo[i] = new ShowContactInfo(contactName[i], contactNoPhone[i]);
            } catch (IOException | BusinessException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Création du Pannel qui aura une scrollBar et la liste des contactes
     * @return
     */
    public JScrollPane buildScrollPaneContact()
    {
        buildVariables();

        pnlCenterJscrollContact = new JPanel(new GridLayout(0, 1, 5, 5));
        pnlCenterJscrollContact.setBackground(Color.ORANGE);

        // Si la liste de contactes est vide on affiche un message par défaut
        if (contacts.size() == 0)
        {
            JLabel emptyContactMessage = new JLabel("No contact to show");
            pnlCenterJscrollContact.add(emptyContactMessage);
        }
        else
        {

            // CREATION des BOUTTONS pour chaque CONTACTE
            for (int i = 0; i < contacts.size(); i++)
            {
                btnShowContacts[i] = new JButton(contactName[i]);
                pnlCenterJscrollContact.add(btnShowContacts[i]);
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
     * Création du cardlayout avec ajout des cards
     */
    private void buildCardsLayout()
    {

        cardLayoutContact = new CardLayout();
        this.setLayout(cardLayoutContact);

        this.add("HomeContact", pnlHomeContact);
        this.add("AddContact", pnlAddContact);

        // Création des cards de contact
        for (int i = 0; i < contacts.size(); i++)
        {
            this.add(contactName[i], pnlShowContactInfo[i]); //@TODO : PAS SRU QUE CE SOIT CORRECTE !!!
        }
        //this.cl.refreshPanel("Home"); //@TODO : Ajouter une nouvelle methode REFRESH pour ce cardLayout ici ????

    }

    /**
     * Listeners
     */
    private void setListeners()
    {
        // action listener pour l'ajout d'un contact
        btnAddContact.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cardLayoutContact.show(pnlContentCardsContact, "AddContact");
                //refreshPanel("AddContact");
            }
        });

        // action listener pour les différents contact du scrollPane
        // création des ActionListener en fonction du nombre de contacts présents
        for (int i = 0; i < contacts.size() ; i++)
        {
            String finalName = contactName[i];
            btnShowContacts[i].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    cardLayoutContact.show(pnlContentCardsContact, finalName);
                }
            });
        }
    }

    /*public void refreshPanel(String currentPanel)
    {
        this.currentPanel = currentPanel;
        cardLayoutContact.show(this, this.currentPanel);
        cl.getfLayout().buildMenu();
    }*/



    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public Button getBtnAddContact()
    {
        return btnAddContact;
    }

    public JButton[] getBtnShowContact()
    {
        return btnShowContacts;
    }

    public JPanel getPnlCenterJscrollContact()
    {
        return pnlCenterJscrollContact;
    }

    public JSONStorageContact getJsonAddressBook()
    {
        return jsonAddressBook;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    public void setPnlCenterJscrollContact(JPanel pnlCenterJscrollContact)
    {
        this.pnlCenterJscrollContact = pnlCenterJscrollContact;
    }



}
