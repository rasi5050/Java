package org.example;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ContactManager contactManager = new ContactManager();
        Contact contact;
        while (true) {
            System.out.println("Welcome to ContactMan1ager\n" +

                    "1. Insert new contact\n" +
                    "2. View contacts\n" +
                    "3. Update contact\n" +
                    "4. Delete contact\n");
            int option = scanner.nextInt();

            switch (option) {
                case 1: {
                    contact = contactManager.inputToContact();
                    contactManager.insert(contact);
                    break;
                }
                case 2: {
                    List<Contact> allContacts = contactManager.getAllContacts();
                    if (!allContacts.isEmpty())
                        contactManager.showAllContacts(allContacts);
                    else
                        System.out.println("Contact Manager is empty");

                    break;
                }
                case 3: {
                    List<Contact> allContacts = contactManager.getAllContacts();
                    if (!allContacts.isEmpty()) {
                        contactManager.showAllContacts(allContacts);

                        System.out.println("Enter Contact index to update");
                        int idx = scanner.nextInt();
                        Contact oldContact = allContacts.get(idx);


                        Contact newContact = contactManager.inputToContact();
                        newContact.setId(oldContact.getId());
                        contactManager.update(newContact);
                    }
                    else {
                        System.out.println("Contact Manager is empty");
                    }
                    break;
                }

                case 4: {
                    List<Contact> allContacts = contactManager.getAllContacts();


                    if (!allContacts.isEmpty()) {
                        contactManager.showAllContacts(allContacts);
                        System.out.println("Enter Contact index to update");
                        int idx = scanner.nextInt();
                        Contact contactToDelete = allContacts.get(idx);
                        contactManager.delete(contactToDelete);
                    }
                    else {
                        System.out.println("Contact Manager is empty");
                    }
                    break;

                }


            }
        }

        }

    }
