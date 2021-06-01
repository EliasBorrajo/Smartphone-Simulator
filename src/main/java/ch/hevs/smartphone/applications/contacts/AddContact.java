package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddContact extends JPanel
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // PANEL
    private JPanel pnlNorth = new JPanel();
    private JPanel pnlCentre = new JPanel();
    private JPanel pnlSouth = new JPanel();

    // LABEL
    private JLabel lblContactsTitle = new JLabel("Add new contact");
    private JLabel lblName = new JLabel("Last Name : ");
    private JLabel lblFirstName = new JLabel("First name : ");
    private JLabel lblNoPhone = new JLabel("Phone number : ");

    // TEXTFIELD
    private JTextField tfName = new JTextField("N",50);
    private JTextField tfFirstName = new JTextField("FN", 50);
    private JTextField tfNoPhone = new JTextField("NP", 50);


    // TODO fonction pour ajouter une image de la galerie

    // BUTTON
    JButton btnSave = new JButton("Save");

    // GETTERS
    public JButton getBtnSave() {
        return btnSave;
    }

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public AddContact() throws IOException, BusinessException
    {
        buildPnlContent();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private JPanel buildPnlContent() {

        // Parametre du frame
        setSize(50, 50);
        setLayout(new BorderLayout());

        // Parametre des JPanel
        pnlCentre.setLayout(new GridLayout(3,2));

        // Ajout panels à la Frame
        pnlNorth.add(btnSave);
        this.add(pnlNorth, BorderLayout.NORTH);

        pnlCentre.add(lblFirstName);
        pnlCentre.add(tfFirstName);
        pnlCentre.add(lblName);
        pnlCentre.add(tfName);
        pnlCentre.add(lblNoPhone);
        pnlCentre.add(tfNoPhone);
        this.add(pnlCentre, BorderLayout.CENTER);

        pnlSouth.setLayout(new GridLayout());
        pnlSouth.add(btnSave);
        this.add(pnlSouth, BorderLayout.SOUTH);


        return this;
    }

    //*****************************************************************************
    // I N N E R  -  C L A S S
    //*****************************************************************************
    // INNER CLASS

   /* class ListenerSaveAddContact implements ActionListener {

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

            Contact c = new Contact("","","");

            String text1 = "";
            text1 = inputFN.getText();
            c.setFirstName(text1);

            String text2 = "";
            text2 = inputN.getText();
            c.setLastName(text2);

            String text3 = "";
            text3 = inputNP.getText();
            c.setNoPhone(text3);

            System.out.println(c);

            ad.addContact(c);
            ad.sortDescending(ad.getContacts()); // trie l'Arraylist contacts par ordre alphabétique
            System.out.println("AddContact1");

            try {
                ad.write(ad.getmyObj(),ad.getContacts());
                System.out.println(ad.getmyObj());
            } catch (BusinessException businessException) {
                businessException.printStackTrace();
            }

            System.out.println("AddContact2");

            inputFN.setText("");
            inputN.setText("");
            inputNP.setText("");

        }
*/


    //********************************




    /*public class ListenerSaveAddContact implements ActionListener
    {
        JTextField inputFN;
        JTextField inputN;
        JTextField inputNP;

        public ListenerSaveAddContact(JTextField firstName, JTextField name, JTextField noPhone)
        {
            inputFN = firstName;
            inputN = name;
            inputNP = noPhone;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            // SAVE
            // Récuperation des TEXTBOX
            Contact c = new Contact("", "", "");

            String text1 = "";
            text1 = inputFN.getText();
            c.setFirstName(text1);

            String text2 = "";
            text2 = inputN.getText();
            c.setLastName(text2);

            String text3 = "";
            text3 = inputNP.getText();
            c.setNoPhone(text3);

            System.out.println(c);

            addressBook.addContact(c);
            // addressBook.sortDescending(addressBook.getContactArray()); // trie l'Arraylist contacts par ordre alphabétique

            System.out.println("AddContact1");
            try
            {
                addressBook.write(addressBook.getmyObj(), addressBook.getContactArray()); // SERIALISATION
            } catch (BusinessException businessException)
            {
                businessException.printStackTrace();
                System.out.println("COULD NOT SAVE CONTACT -- INNER CLASS CONTENTLAYOUT LISTENERS");
            }
            System.out.println("AddContact2");

            inputFN.setText("");
            inputN.setText("");
            inputNP.setText("");

            // REFRESH @TODO REFRESH BUG ENCORE
            System.out.println("ContentLayout");
            pnlContact.removeAll();
            // pnlContact.validate();
            //refreshPanel("Contact");
            pnlContact.add(pnlContact.buildpnlContentContact());

            //pnlContact.revalidate();
            //pnlContact.repaint();

            cardlayout.show(pnlContent, "Contact");
            actionsCount--;

        }
    }
    */


    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public JTextField getTfName()
    {
        return tfName;
    }

    public JTextField getTfFirstName()
    {
        return tfFirstName;
    }

    public JTextField getTfNoPhone()
    {
        return tfNoPhone;
    }

}

