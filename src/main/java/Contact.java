import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contact {
    private String name;
    private final String number;
    private List<String> contactGroups = new ArrayList<>();

    public Contact(String name, String number, PhoneBook phoneBook) {
        this.name = name;
        this.number = number;
        phoneBook.getContacts().put(number, this);
    }

    public List<String> getContactGroups() {
        return contactGroups;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Contact contact = (Contact) obj;
        return this.number.equals(contact.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        StringBuilder groups = new StringBuilder();
        String ender = ", ";
        for (int i = 0; i < contactGroups.size(); i++) {
            if (i + 1 == contactGroups.size()) {
                ender = ".\n";
            }
            groups.append(contactGroups.get(i)).append(ender);
        }
        return "Имя: " + name + " , номер телефона: " + number + "\nПринадлежит к группам: " + groups;
    }
}
