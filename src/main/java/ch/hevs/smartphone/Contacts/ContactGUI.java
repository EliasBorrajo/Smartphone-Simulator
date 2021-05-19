package ch.hevs.smartphone.Contacts;

import javax.swing.*;
import java.awt.*;

public class ContactGUI extends JPanel {
    // PANEL
    JPanel pnl_north = new JPanel();
    JPanel pnl_centre = new JPanel();

    // LABEL
    JLabel contactTitle = new JLabel("CONTACTS");

    // BUTTON
    JButton btnAdd = new JButton("+");

    // SCROLLPAN
    JScrollPane contactTable;

    // OBJECT FOR METHODS
    AddContact addressBookList = new AddContact();
    Contacts contactTmp = new Contacts();

    public ContactGUI() {
        contactTable = new JScrollPane(addressBookList.getContactJList());
        // Il faudrait que les contacts ajoutés à l'aide de la classe "AddContact" apparaissent dans le JScrollPane

        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLUE);

        // SETTING PANEL NORTH
        pnl_north.setLayout(new GridLayout());
        pnl_north.add(contactTitle);
        pnl_north.add(btnAdd);
        this.add(pnl_north, BorderLayout.NORTH);

        // SETTING PANEL CENTRE
        pnl_centre.add(contactTable);
        pnl_centre.setBackground(Color.MAGENTA);
        this.add(pnl_centre, BorderLayout.CENTER);

        /*btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/

    }
}