public class Author {
    String name;
    int bookCount;
    String publishedBooks;
    int lastPublishedYear;
    String lastPublishedTitle;
    {
        this.bookCount = 0;
        this.publishedBooks = "";
        this.lastPublishedYear = 0;
    }

    Author(String name) {
        this.name = name;
    }

    public String lastPublishedBook() {
        return "Крайней изданной книгой автора " + this.name + " является " + "\"" + this.lastPublishedTitle + "\" " + this.lastPublishedYear + " года.";
    }

    public String toString() {
        return "Автор: " + this.name + "\nОпубликованные книги:\n" + this.publishedBooks;
    }

}
