package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManager {
    private Connection conn;
    private PreparedStatement pstmt = null;

    public ContactManager() throws SQLException {
        String url = "jdbc:sqlite:ContactManager.db";
        conn = DriverManager.getConnection(url);
            if (conn != null){

                Statement stmt = conn.createStatement();
//                #create tables, contact, address, email, phone
//                        contact is the main table
//                        contact 1:m address
//                        contact 1:m email
//                        contact 1:m phone
//                contact : (id, name)
//                address : (id, contact_id, address type, address line1, address line2, city, state, zip, country)
//                email : (id, contact_id, email type, email)
//                phone : (id, contact_id, phone type, phone)

                String createTblContact = "CREATE TABLE IF NOT EXISTS Contact (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100));";
                String createTblAddress = "CREATE TABLE IF NOT EXISTS Address (id INTEGER PRIMARY KEY AUTOINCREMENT, contact_id INTEGER, address_type VARCHAR(50), address_line1 VARCHAR(255), address_line2 VARCHAR(255), city VARCHAR(100), state VARCHAR(100), zip INTEGER, country VARCHAR(100), FOREIGN KEY (contact_id) REFERENCES Contact(id));";
                String createTblEmail = "CREATE TABLE IF NOT EXISTS Email (id INTEGER PRIMARY KEY AUTOINCREMENT, contact_id INTEGER, email_type VARCHAR(50), email VARCHAR(255), FOREIGN KEY(contact_id) REFERENCES Contact(id));";
                String createTblPhone = "CREATE TABLE IF NOT EXISTS Phone (id INTEGER PRIMARY KEY AUTOINCREMENT, contact_id INTEGER, phone_type VARCHAR(50), phone VARCHAR(15), FOREIGN KEY(contact_id) REFERENCES Contact(id));";

                stmt.executeUpdate(createTblContact);
                stmt.executeUpdate(createTblAddress);
                stmt.executeUpdate(createTblEmail);
                stmt.executeUpdate(createTblPhone);
                stmt.close();
            }
    }
    void insert(Contact contact) throws SQLException {
        String name = contact.getName();
        List<Email> emails = contact.getEmails();
        List<Phone> phones = contact.getPhones();
        List<Address> addresses = contact.getAddresses();
        Statement stmt = conn.createStatement();


//        insert name
        String insertName = "INSERT INTO Contact (name) VALUES ('"+ name +"');";
        stmt.executeUpdate(insertName);
        ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid();");
        int foreignKey = 0;
        if (rs.next()) {
            foreignKey = rs.getInt(1);
        }

        // Set the parameters for the PreparedStatement

        for (Email email : emails)
        {


            String emailSql = "INSERT INTO Email(contact_id, email_type, email) VALUES (?, ?, ?)";

//        prepared statement to parameterize SQL
            pstmt = conn.prepareStatement(emailSql);
            pstmt.setInt(1, foreignKey);
            pstmt.setString(2, email.getEmail_type());
            pstmt.setString(3, email.getEmail());

            // Execute the prepared statement
            pstmt.executeUpdate();
        }

        for (Phone phone: phones){
            String phoneSql = "INSERT INTO Phone (contact_id, phone_type, phone) VALUES (?, ?, ?);";
            pstmt = conn.prepareStatement(phoneSql);
            pstmt.setInt(1, foreignKey);
            pstmt.setString(2, phone.getPhone_type());
            pstmt.setString(3, phone.getPhone());

            // Execute the prepared statement
            pstmt.executeUpdate();

        }

        for (Address address: addresses) {
            String addressSql = "INSERT INTO Address (contact_id, address_type, address_line1, address_line2, city, state, zip, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            pstmt = conn.prepareStatement(addressSql);
            pstmt.setInt(1, foreignKey);
            pstmt.setString(2, address.getAddress_type());
            pstmt.setString(3, address.getAddress_line1());
            pstmt.setString(4, address.getAddress_line2());
            pstmt.setString(5, address.getCity());
            pstmt.setString(6, address.getState());
            pstmt.setInt(7, address.getZip());
            pstmt.setString(8, address.getCountry());


            // Execute the prepared statement
            pstmt.executeUpdate();

        }

    }

    Contact toContact(ResultSet rs) throws SQLException {
//        single resultset to Contact
        Statement stmt = conn.createStatement();
        Contact contact = new Contact();
        List<Email> emails = new ArrayList<>();

        List<Phone> phones = new ArrayList<>();

        List<Address> addresses = new ArrayList<>();


            Integer id = rs.getInt("id");
            contact.setId(id);
            contact.setName(rs.getString("name"));

            //            get emails of the contact
            String getEmailsSql = "SELECT * FROM Email WHERE contact_id=" +id+";";
            ResultSet emailsRs = stmt.executeQuery(getEmailsSql);
            while (emailsRs.next()) {
                Email email = new Email();
                email.setContact_id(emailsRs.getInt("id"));
                email.setEmail_type(emailsRs.getString("email_type"));
                email.setEmail(emailsRs.getString("email"));
                emails.add(email);
            }
//            get phones of the contact
            String getPhonesSql = "SELECT * FROM Phone WHERE contact_id=" +id+";";
            ResultSet phonesRs = stmt.executeQuery(getPhonesSql);
            while (phonesRs.next()) {
                Phone phone = new Phone();
                phone.setContact_id(phonesRs.getInt("id"));
                phone.setPhone_type(phonesRs.getString("phone_type"));
                phone.setPhone(phonesRs.getString("phone"));
                phones.add(phone);
            }

//            get addresses of the contact
            String getAddressesSql = "SELECT * FROM Address WHERE contact_id=" +id+";";
            ResultSet addressesRs = stmt.executeQuery(getAddressesSql);
            while (addressesRs.next()) {
                Address address = new Address();
                address.setContact_id(addressesRs.getInt("id"));
                address.setAddress_type(addressesRs.getString("address_type"));
                address.setAddress_line1(addressesRs.getString("address_line1"));
                address.setAddress_line2(addressesRs.getString("address_line2"));
                address.setCity(addressesRs.getString("city"));
                address.setState(addressesRs.getString("state"));
                address.setZip(addressesRs.getInt("zip"));
                address.setCountry(addressesRs.getString("country"));
                addresses.add(address);
//            contact_id, address_type, address_line1, address_line2, city, state, zip, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            }


        contact.setEmails(emails);
        contact.setPhones(phones);
        contact.setAddresses(addresses);
        return contact;
    }
    List<Contact> getAllContacts() throws SQLException {
        Statement stmt = conn.createStatement();
        String getAllContactsSql = "SELECT *" +
                "FROM Contact;";
//                +
//                "JOIN Address ON Contact.id = Address.contact_id" +
//                "JOIN Email ON Contact.id = Email.contact_id" +
//                "JOIN Phone ON Contact.id = Phone.contact_id;";
        List<Contact> contacts = new ArrayList<>();
        ResultSet rs = stmt.executeQuery(getAllContactsSql);
        while (rs.next()) {
            contacts.add(toContact(rs));
        }
            return contacts;
    }
void showAllContacts(List<Contact> contacts) {
        int i=0;
        for (Contact contact: contacts){
            System.out.println("Contact "+i);

            System.out.println("Name: " + contact.getName());

            System.out.println("Emails");
            for (Email email: contact.getEmails()) {
                System.out.print(email.getEmail_type() + ": ");
                System.out.print(email.getEmail() + "\t");
            }
            System.out.print("\n");

            System.out.println("Phones");
            for (Phone phone: contact.getPhones()) {
                System.out.print(phone.getPhone_type() + ": ");
                System.out.print(phone.getPhone() + "\t");
            }
            System.out.print("\n");
            System.out.println("Addresses");
            for (Address address: contact.getAddresses()) {
                System.out.print(address.getAddress_type() + ": ");
                System.out.print(address.getAddress_line1() + " ");
                System.out.print(address.getAddress_line2() + " ");
                System.out.print(address.getCity() + " ");
                System.out.print(address.getState() + " ");
                System.out.print(address.getCountry() + " ");
                System.out.print(address.getZip() + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
            i++;
        }
}


Contact inputToContact() {
        Contact contact = new Contact();
        List<Email> emails = new ArrayList<>();
        List<Phone> phones = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter contact Name");
        String input = scanner.nextLine();
        contact.setName(input);

    System.out.println("Enter contact's emails");

        while (true) {

            System.out.println("enter email type or type done");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) break;
            else {
                Email email = new Email();
                email.setEmail_type(input);
                System.out.println("enter email id");
                input = scanner.nextLine();
                email.setEmail(input);
                emails.add(email);
            }
        }
        contact.setEmails(emails);
    System.out.println("Enter contact's phones");
        while (true) {
            System.out.println("enter phone type or type done");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) break;
            else {
                Phone phone = new Phone();
                phone.setPhone_type(input);
                System.out.println("enter phone number");
                input = scanner.nextLine();
                phone.setPhone(input);
                phones.add(phone);
            }
        }
        contact.setPhones(phones);

    System.out.println("Enter contact's addresses");
    while (true) {
        System.out.println("enter address type or type done");
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("done")) break;
        else {
            Address address = new Address();
            address.setAddress_type(input);

            System.out.println("enter address line 1");
            input = scanner.nextLine();
            address.setAddress_line1(input);

            System.out.println("enter address line 2");
            input = scanner.nextLine();
            address.setAddress_line2(input);

            System.out.println("enter city");
            input = scanner.nextLine();
            address.setCity(input);

            System.out.println("enter state");
            input = scanner.nextLine();
            address.setState(input);

            System.out.println("enter country");
            input = scanner.nextLine();
            address.setCountry(input);

            System.out.println("enter zip");
            int zip = scanner.nextInt();
            address.setZip(zip);
//            to skip the \n inserted by the nextInt()
            scanner.nextLine();

            addresses.add(address);

        }
    }
    contact.setAddresses(addresses);
return contact;
}
//delete
//    update
    void delete(Contact contact) throws SQLException {
        Integer id = contact.getId();

        String deleteContactSql = "DELETE FROM Contact WHERE id="+id+";";
        String deleteEmailSql = "DELETE FROM Email WHERE contact_id="+id+";";
        String deletePhoneSql = "DELETE FROM Phone WHERE contact_id="+id+";";
        String deleteAddressSql = "DELETE FROM Address WHERE contact_id="+id+";";
        Statement stmt = conn.createStatement();
        stmt.execute(deleteContactSql);
        stmt.execute(deleteEmailSql);
        stmt.execute(deletePhoneSql);
        stmt.execute(deleteAddressSql);

    }

    void update(Contact contact) throws SQLException {
        Integer id = contact.getId();

//        update implemented as delete and new insert
        delete(contact);
        insert(contact);
    }

}

