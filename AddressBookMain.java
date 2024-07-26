import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookMain {
    private static Map<String, AddressBook> addressBooks = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add New Address Book");
            System.out.println("2. Manage Existing Address Book");
            System.out.println("3. Display All Address Books");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the new address book: ");
                    String addressBookName = scanner.nextLine();
                    if (addressBooks.containsKey(addressBookName)) {
                        System.out.println("Address book with this name already exists.");
                    } else {
                        addressBooks.put(addressBookName, new AddressBook());
                        System.out.println("Address book added successfully!");
                    }
                    break;

                case 2:
                    System.out.print("Enter the name of the address book to manage: ");
                    String manageAddressBookName = scanner.nextLine();
                    AddressBook addressBook = addressBooks.get(manageAddressBookName);
                    if (addressBook != null) {
                        manageAddressBook(scanner, addressBook);
                    } else {
                        System.out.println("Address book not found.");
                    }
                    break;

                case 3:
                    System.out.println("Existing address books:");
                    for (String name : addressBooks.keySet()) {
                        System.out.println(name);
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void manageAddressBook(Scanner scanner, AddressBook addressBook) {
        while (true) {
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Display Contacts");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    boolean addMore = true;
                    while (addMore) {
                        System.out.println("Enter contact details:");

                        System.out.print("First Name: ");
                        String firstName = scanner.nextLine();

                        System.out.print("Last Name: ");
                        String lastName = scanner.nextLine();

                        System.out.print("Address: ");
                        String address = scanner.nextLine();

                        System.out.print("City: ");
                        String city = scanner.nextLine();

                        System.out.print("State: ");
                        String state = scanner.nextLine();

                        System.out.print("ZIP: ");
                        String zip = scanner.nextLine();

                        System.out.print("Phone Number: ");
                        String phoneNumber = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
                        addressBook.addContact(newContact);

                        System.out.println("Contact added successfully!");

                        System.out.print("Do you want to add another contact? (yes/no): ");
                        String response = scanner.nextLine();
                        addMore = response.equalsIgnoreCase("yes");
                    }
                    break;

                case 2:
                    System.out.println("Enter the name of the contact to edit:");

                    System.out.print("First Name: ");
                    String editFirstName = scanner.nextLine();

                    System.out.print("Last Name: ");
                    String editLastName = scanner.nextLine();

                    Contact contactToEdit = addressBook.findContactByName(editFirstName, editLastName);
                    if (contactToEdit != null) {
                        System.out.println("Enter new details:");

                        System.out.print("Address: ");
                        String newAddress = scanner.nextLine();

                        System.out.print("City: ");
                        String newCity = scanner.nextLine();

                        System.out.print("State: ");
                        String newState = scanner.nextLine();

                        System.out.print("ZIP: ");
                        String newZip = scanner.nextLine();

                        System.out.print("Phone Number: ");
                        String newPhoneNumber = scanner.nextLine();

                        System.out.print("Email: ");
                        String newEmail = scanner.nextLine();

                        addressBook.editContact(contactToEdit, newAddress, newCity, newState, newZip, newPhoneNumber, newEmail);
                        System.out.println("Contact updated successfully!");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 3:
                    System.out.println("Enter the name of the contact to delete:");

                    System.out.print("First Name: ");
                    String deleteFirstName = scanner.nextLine();

                    System.out.print("Last Name: ");
                    String deleteLastName = scanner.nextLine();

                    boolean isDeleted = addressBook.deleteContact(deleteFirstName, deleteLastName);
                    if (isDeleted) {
                        System.out.println("Contact deleted successfully!");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 4:
                    System.out.println("Current contacts in address book:");
                    addressBook.displayContacts();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}