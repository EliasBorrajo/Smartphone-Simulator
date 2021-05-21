package ch.hevs.smartphone.Contacts;


import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;

public class Contacts {
    Scanner scan = new Scanner(System.in);

    private final static int MAX_CONTACTS_ADDRESSBOOK = 5;

    private static String[] firstName = new String[MAX_CONTACTS_ADDRESSBOOK];
    private static String[] lastName = new String[MAX_CONTACTS_ADDRESSBOOK];
    private static String[] noPhone = new String[MAX_CONTACTS_ADDRESSBOOK];


    private static int cptFirstName = 0;
    private static int cptLastName = 0;
    private static int cptNoPhone = 0;

    private int noContact;

    // GETTERS

    public String[] getFirstName() {
        return firstName;
    }

    public String[] getLastName() {
        return lastName;
    }

    public String[] getNoPhone() {
        return noPhone;
    }

    // SETTERS

    public void setFirstName(String[] firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String[] lastName) {
        this.lastName = lastName;
    }

    public void setNoPhone(String[] noPhone) {
        this.noPhone = noPhone;
    }

// METHODS

    public void addContactFirstName(String fn) {
        firstName[cptFirstName] = fn;
        cptFirstName++;
    }

    public void addContactLastName(String ln) {
        lastName[cptLastName] = ln;
        cptLastName++;
    }

    public void addNoPhone(String np) {
        noPhone[cptNoPhone] = np;
        cptNoPhone++;
    }

    public void remove(String[] t, int noC) {
        noContact = noC;
        t[noContact] = null;
    }

    public void display(String[] t) {
        for (int i = 0; i<MAX_CONTACTS_ADDRESSBOOK; i++) {
            System.out.println(t[i]);
        }
    }

    public JList addStringArrayToJlist(String[] t) {
        JList list = new JList(t);
        return list;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "firstName=" + Arrays.toString(firstName) +
                ", lastName=" + Arrays.toString(lastName) +
                ", noPhone=" + Arrays.toString(noPhone) +
                '}';
    }
}