import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestPhoneBook {

//    public static void main(String[] args) {
//        PhoneBook phoneBook = new PhoneBook();
//        List<Contact> contacts = createContacts(phoneBook);
//        phoneBook.createGroup("Работа");
//        phoneBook.createGroup("Друзья");
//        phoneBook.createGroup("Семья");
//        phoneBook.addContactToGroup(contacts.get(0), "Семья");
//        phoneBook.addContactToGroup(contacts.get(1), "Семья");
//        phoneBook.addContactToGroup(contacts.get(2), "Работа");
//        phoneBook.addContactToGroup(contacts.get(3), "Работа");
//        phoneBook.addContactToGroup(contacts.get(4), "Друзья");
//        phoneBook.addContactToGroup(contacts.get(5), "Друзья");
//        phoneBook.addContactToGroup(contacts.get(0), "Работа");
//        phoneBook.addContactToGroup(contacts.get(2), "Друзья");
//        phoneBook.addContactToGroup(contacts.get(3), "Друзья");
//        phoneBook.addContactToGroup(contacts.get(5), "Работа");
//        phoneBook.printGroupContacts("Семья");
//        phoneBook.printGroupContacts("Друзья");
//        phoneBook.printGroupContacts("Работа");
//        phoneBook.printContact("89991234567");
//        phoneBook.deleteContact("89991234568");
//        phoneBook.printContact("89991234568");
//        phoneBook.deleteContactFromGroup("89991234563", "Работа");
//        phoneBook.printGroupContacts("Работа");
//    }
//
//    public static List<Contact> createContacts(PhoneBook phoneBook) {
//        return Arrays.asList(
//                new Contact("Ваня", "89991234567", phoneBook),
//                new Contact("Дима", "89991234568", phoneBook),
//                new Contact("Настя", "89991234569", phoneBook),
//                new Contact("Пётр", "89991234569", phoneBook),
//                new Contact("Катя", "89991234565", phoneBook),
//                new Contact("Дуня", "89991234563", phoneBook)
//        );
//    }

    static PhoneBook phoneBook = new PhoneBook();

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void testEquals(Contact argument) {
        Contact contact = new Contact("Ваня", "89991234567", phoneBook);
        if (argument.getName() == "Ваня") {
            Assertions.assertTrue(argument.equals(contact));
        } else {
            Assertions.assertFalse(argument.equals(contact));
        }
    }

    static Stream<Contact> contactProvider() {
        return Stream.of(
                new Contact("Ваня", "89991234567", phoneBook),
                new Contact("Дима", "89991234568", phoneBook));
    }

    @Test
    public void testAddContactToGroup() {
        Contact contact = new Contact("Настя", "89991234569", phoneBook);
        phoneBook.createGroup("Работа");
        phoneBook.addContactToGroup(contact, "Работа");
        Assertions.assertTrue(phoneBook.getGroups().get("Работа").contains(contact));
    }

}
