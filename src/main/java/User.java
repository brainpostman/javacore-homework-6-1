public class User {
    static int usersOnline = 0;
    String name;
    String surname;
    String email;
    int age;

    User(String name, String surname, String email, int age) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = Math.max(age, 0);
        usersOnline++;
    }

    public static String onlineNow() {
        return "Количество пользователей онлайн: " + usersOnline + ".\n";
    }


}
