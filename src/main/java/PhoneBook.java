import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PhoneBook {

    private String groupName;

    private final Map<String, List<Contact>> groups = new HashMap<>();
    private final Map<String, Contact> contacts = new HashMap<>();

    public Map<String, Contact> getContacts() {
        return contacts;
    }

    public Map<String, List<Contact>> getGroups() {
        return groups;
    }

    public void createGroup(String groupName) {
        List<Contact> contactGroup = new ArrayList<>();
        groups.put(groupName, contactGroup);
    }

    public void printGroupContacts(String groupName) {
        if (!groups.containsKey(groupName)) {
            System.out.println("Данной группы не существует\n");
            return;
        }
        System.out.println("Контакты группы " + groupName);
        for (Contact contact : groups.get(groupName)) {
            System.out.println(contact);
        }
    }

    public void addContactToGroup(Contact contact, String groupName) {
        if (!groups.containsKey(groupName)) {
            System.out.println("Данной группы не существует\n");
            return;
        }
        groups.get(groupName).add(contact);
        contact.getContactGroups().add(groupName);
    }

    public void printContact(String number) {
        if (!contacts.containsKey(number)) {
            System.out.println("Данного контакта не существует в телефонном справочнике.\n");
        } else {
            System.out.println(contacts.get(number));
        }
    }

    public void deleteContact(String number) {
        if (!contacts.containsKey(number)) {
            System.out.println("Данного контакта не существует в телефонном справочнике.\n");
            return;
        }
        Contact contact = contacts.get(number);
        for (String groupName : contact.getContactGroups()) {
            groups.get(groupName).remove(contact);
        }
        contacts.remove(number);
    }

    public void deleteContactFromGroup(String number, String groupName) {
        if (!groups.containsKey(groupName)) {
            System.out.println("Данной группы не существует\n");
            return;
        }
        if (!groups.get(groupName).remove(contacts.get(number))) {
            System.out.println("Данный контакт не значится в этой группе.\n");
        } else {
            System.out.println("Контакт успешно удалён.");
        }
    }
}
