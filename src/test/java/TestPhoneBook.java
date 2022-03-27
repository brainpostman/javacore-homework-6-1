import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestPhoneBook {

    PhoneBook phoneBook = new PhoneBook();

    @BeforeEach
    void addTestContact() {
        Contact contact = new Contact("Настя", "89991234569", phoneBook);
        phoneBook.createGroup("Работа");
        phoneBook.addContactToGroup(contact, "Работа");
    }

    @Test
    public void testCreateGroup() {
        phoneBook.createGroup("Семья");
        Assertions.assertTrue(phoneBook.getGroups().containsKey("Семья"));
    }

    @Test
    public void testAddContactToGroup() {
        String group = "Работа";
        Contact contact = phoneBook.getGroups().get(group).get(0);
        Assertions.assertTrue(phoneBook.getGroups().get(group).contains(contact));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Друзья", "Работа"})
    public void testPrintGroupContacts(String key) throws Exception {
        String result = tapSystemOut(() -> phoneBook.printGroupContacts(key));
        String expected = "Данной группы не существует";
        if (key.equals("Работа")) {
            Contact contact = phoneBook.getGroups().get(key).get(0);
            expected = "Контакты группы " + key + "\r\n" + contact;
        }
        Assertions.assertEquals(expected.trim(), result.trim());
    }

    @ParameterizedTest
    @ValueSource(strings = {"89991234567", "89991234569"})
    public void testPrintContact(String key) throws Exception {
        String result = tapSystemOut(() -> phoneBook.printContact(key));
        String expected = "Данного контакта не существует в телефонном справочнике.";
        if (key.equals("89991234569")) {
            expected = phoneBook.getContacts().get(key).toString();
        }
        Assertions.assertEquals(expected.trim(), result.trim());
    }

    @ParameterizedTest
    @ValueSource(strings = {"89991234567", "89991234569"})
    public void testDeleteContact(String key) throws Exception {
        String result = tapSystemOut(() -> phoneBook.deleteContact(key));
        String expected = "Данного контакта не существует в телефонном справочнике.";
        if (key.equals("89991234567")) {
            Assertions.assertEquals(expected, result.trim());
        } else {
            Assertions.assertFalse(phoneBook.getContacts().containsKey(key));
        }
    }

    @ParameterizedTest
    @MethodSource("addToContacts")
    public void deleteContactFromGroup(String contact, String group) throws Exception {
        String result = tapSystemOut(() -> phoneBook.deleteContactFromGroup(contact, group));
        String expected = "Данный контакт не значится в этой группе.";
        if (group.equals("Семья")) {
            expected = "Данной группы не существует";
        } else if (contact.equals("89991234569")) {
            expected = "Контакт успешно удалён.";
        }
        Assertions.assertEquals(expected.trim(), result.trim());
    }

    static Stream<Arguments> addToContacts() {
        return Stream.of(
                arguments("89991234566", "Семья"),
                arguments("89991234567", "Работа"),
                arguments("89991234569", "Работа"));
    }
}
