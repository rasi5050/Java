package com.rasi.contactmanagerv3;


import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

//run as
//java Main insert "{\"name\":\"John Doe\",\"emails\":[{\"email_type\":\"personal\",\"email\":\"john@example.com\"}],\"phones\":[{\"phone_type\":\"mobile\",\"phone\":\"1234567890\"}],\"addresses\":[{\"address_type\":\"home\",\"address_line1\":\"123 Main St\",\"address_line2\":\"\",\"city\":\"New York\",\"state\":\"NY\",\"zip\":10001,\"country\":\"USA\"}]}"

public class Main {
    public static void main(String[] args) throws SQLException {
        if (args.length == 0) {
            System.out.println("Please provide contact details in JSON format.");
            return;
        }
//        Scanner scanner = new Scanner(System.in);

        String operation = args[0];
        String jsonInput;
        ContactManager contactManager = new ContactManager();
//        Contact contact;


        switch (operation) {
            case "insert": {
                jsonInput = args[1];
                Contact contact = contactManager.parseJsonToContact(jsonInput);

            if (contact != null) {
                contactManager.insert(contact);
                System.out.println("Contact added successfully.");
            } else {
                System.out.println("Failed to parse JSON input.");
            }
            break;}
            case "view": {
                    List<Contact> allContacts = contactManager.getAllContacts();
                    if (!allContacts.isEmpty())
                        contactManager.showAllContacts(allContacts);
                    else
                        System.out.println("Contact Manager is empty");

                    break;
                }
            case "update": {
                List<Contact> allContacts = contactManager.getAllContacts();
                    int id = Integer.parseInt(args[1]);
                    jsonInput = args[2];
                Contact oldContact = allContacts.get(id);
                contactManager.delete(oldContact.getId());
                    Contact contact = contactManager.parseJsonToContact(jsonInput);

                    if (contact != null) {
                        contactManager.update(id, contact);
                        System.out.println("Contact added successfully.");
                    } else {
                        System.out.println("Failed to parse JSON input.");
                    }
                    break;

////                    List<Contact> allContacts = contactManager.getAllContacts();
//                    if (!allContacts.isEmpty()) {
//                        contactManager.showAllContacts(allContacts);
//
//                        System.out.println("Enter Contact index to update");
//                        int idx = scanner.nextInt();
//                        Contact oldContact = allContacts.get(idx);
//
//
//                        Contact newContact = contactManager.inputToContact();
//                        newContact.setId(oldContact.getId());
//                        contactManager.update(newContact);
//                    }
//                    else {
//                        System.out.println("Contact Manager is empty");
//                    }
//                    break;
                }
            case "delete": {
                List<Contact> allContacts = contactManager.getAllContacts();
                    Integer id = Integer.parseInt(args[1]);
                Contact oldContact = allContacts.get(id);

                    if (contactManager.delete(oldContact.getId())) {
                        System.out.println("Contact deleted");
                    }
                    else {
                        System.out.println("Delete failed!");
                    }
                    break;

                }

        }



//        while (true) {
//            System.out.println("Welcome to ContactMan1ager\n" +
//
//                    "1. Insert new contact\n" +
//                    "2. View contacts\n" +
//                    "3. Update contact\n" +
//                    "4. Delete contact\n");
//            int option = scanner.nextInt();
//
//            switch (option) {
//                case 1: {
//                    contact = contactManager.inputToContact();
//                    contactManager.insert(contact);
//                    break;
//                }
//                case 2: {
//                    List<Contact> allContacts = contactManager.getAllContacts();
//                    if (!allContacts.isEmpty())
//                        contactManager.showAllContacts(allContacts);
//                    else
//                        System.out.println("Contact Manager is empty");
//
//                    break;
//                }
//                case 3: {
//                    List<Contact> allContacts = contactManager.getAllContacts();
//                    if (!allContacts.isEmpty()) {
//                        contactManager.showAllContacts(allContacts);
//
//                        System.out.println("Enter Contact index to update");
//                        int idx = scanner.nextInt();
//                        Contact oldContact = allContacts.get(idx);
//
//
//                        Contact newContact = contactManager.inputToContact();
//                        newContact.setId(oldContact.getId());
//                        contactManager.update(newContact);
//                    }
//                    else {
//                        System.out.println("Contact Manager is empty");
//                    }
//                    break;
//                }
//
//                case 4: {
//                    List<Contact> allContacts = contactManager.getAllContacts();
//
//
//                    if (!allContacts.isEmpty()) {
//                        contactManager.showAllContacts(allContacts);
//                        System.out.println("Enter Contact index to update");
//                        int idx = scanner.nextInt();
//                        Contact contactToDelete = allContacts.get(idx);
//                        contactManager.delete(contactToDelete);
//                    }
//                    else {
//                        System.out.println("Contact Manager is empty");
//                    }
//                    break;
//
//                }
//
//
//            }
//        }

    }

}
