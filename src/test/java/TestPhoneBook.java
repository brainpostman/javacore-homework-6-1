import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class TestPhoneBook {

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
