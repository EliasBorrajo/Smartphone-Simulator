package ch.hevs.smartphone.Contacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactsContentLayout extends JPanel {
    // PANEL
    JPanel pnlContentContact = new JPanel();
    JPanel north = new JPanel();

    ContactGUI pnlContactMain = new ContactGUI();
    AddContact pnlAddContact = new AddContact();
    //ShowContact pnlShowContact = new ShowContact();

    // BUTTON
    private JButton btnAdd = new JButton("+");
    JButton btnCancel = new JButton("cancel");

    // CARDLAYOUT
    CardLayout cl = new CardLayout();


    public ContactsContentLayout( ) {
        pnlContentContact.setLayout(cl);
        /*pnlContactMain.setLayout(new BorderLayout());*/

        pnlContactMain.add(btnAdd, BorderLayout.SOUTH);
        pnlContactMain.setBackground(Color.red);
        pnlAddContact.add(btnCancel, BorderLayout.NORTH);
        pnlAddContact.setBackground(Color.GREEN);

        pnlContentContact.add(pnlContactMain,"contactMain");
        pnlContentContact.add(pnlAddContact, "addContact");

        cl.show(pnlContentContact, "contactMain");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(pnlContentContact, "addContact");

            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(pnlContentContact, "contactMain");
            }
        });
        // Comment implémenter ce bouton directement dans la classe "AddContact" en utilisant le CardLayout
        this.setLayout(new BorderLayout());
        this.add(pnlContentContact);

    }

    public JButton getbtnAdd() {
        return btnAdd;
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContactsContentLayout();
            }
        });
    }*/
}