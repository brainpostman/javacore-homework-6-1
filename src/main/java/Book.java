import java.util.Locale;

public class Book {
    private int pages;
    private int readingAge;
    private int publishingYear;
    private double purchasePrice;
    private double rentPrice;
    private double rating;
    private double rateCount = 1.0;
    private String title;
    private String author;
    private String genre;

    Book(int pages, int readingAge, int publishingYear, double purchasePrice, double rentPrice, double rating, String title, Author author, String genre) {
        this.pages = Math.max(pages, 0);
        this.readingAge = Math.max(readingAge, 0);
        this.publishingYear = Math.max(publishingYear, 0);
        this.purchasePrice = Math.max(purchasePrice, 0);
        this.rentPrice = Math.max(rentPrice, 0);
        this.rating = Math.max(Math.min(rating, 10), 0);
        this.title = title;
        this.author = author.name;
        author.bookCount++;
        this.genre = genre;
        if (author.lastPublishedYear < this.publishingYear) {
            author.lastPublishedYear = this.publishingYear;
            author.lastPublishedTitle = this.title;
        }
        author.publishedBooks += "\"" + this.title + "\", " + this.publishingYear + " года, в жанре " + "\"" + this.genre + "\";\n";
    }

    public String toString() {
        return "Книга \"" + this.title + "\", автора " + this.author + ";\nДата публикации: " + this.publishingYear + " год;\nЖанр: " + this.genre + ";\nСтраниц: " +
                this.pages + ", Возрастное ограничение: " + this.readingAge + ";\nОценка пользователей: " + this.rating + ";\n" + "Стоимость приобретения: " +
                this.purchasePrice + " руб., Стоимость проката: " + this.rentPrice + " руб./день.\n";
    }

    public int getPages() {
        return pages;
    }

    public int getReadingAge() {
        return readingAge;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public double getRating() {
        return rating;
    }

    public double getRateCount() {
        return rateCount;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public void rateBook(double addedRating) {
        this.rating = (Math.max(Math.min(addedRating, 10), 0) + this.rating) / ++this.rateCount;
    }

    public String bookRating() {
        return "Текущий рейтинг книги \"" + this.title + "\": " + this.rating + ".\n";
    }

    public String buyOrRent(int pagesPerHour, int hoursPerDay) {
        double fullRentPrice = this.rentPrice * this.pages / pagesPerHour / hoursPerDay;
        if (fullRentPrice < this.purchasePrice) {
            return "Советуем взять книгу \"" + this.title + "\" на прокат.\n";
        } else {
            return "Советуем приобрести книгу \"" + this.title + "\".\n";
        }
    }

    public String familyFriendly(User user) {
        if (user.age < this.readingAge) {
            return "Читателю " + user.name + " " + user.surname + " в данном возрасте не рекомендуется читать книгу \"" + this.title + "\" одному.\n";
        } else {
            return "Читателю " + user.name + " " + user.surname + " в данном возрасте можно читать книгу \"" + this.title + "\".\n";
        }
    }

    public String comparatorMessage(String title1, String title2, String message, Book book, boolean equal) {
        String year1 = "";
        String year2 = "";
        String author1 = "";
        String author2 = "";
        if (title1.equals(title2)) {
            if (this.publishingYear == book.publishingYear) {
                author1 = ", автора" + this.author;
                author2 = ", автора" + book.author;
            }
            year1 = ", " + this.publishingYear + " года" + author1;
            year2 = ", " + book.publishingYear + " года" + author2;
        }
        if (equal) {
            return "Книги \"" + title1 + "\"" + year1 + " и \"" + title2 + "\"" + year2 + " " + message + ".\n";
        } else {
            return "Книга \"" + title1 + "\"" + year1 + " " + message + ", чем книга \"" + title2 + "\"" + year2 + ".\n";
        }
    }

    public String compareRating(Book book) {
        if (this.rating > book.rating) {
            return comparatorMessage(this.title, book.title, "имеет более высокую оценку", book, false);
        } else if (this.rating < book.rating) {
            return comparatorMessage(this.title, book.title, "имеет более низкую оценку", book, false);
        } else {
            return comparatorMessage(this.title, book.title, "имеют одинаковую оценку", book, true);
        }
    }

    public String comparePrice(Book book, String purchaseOrRent) {
        double price1;
        double price2;
        String mes;
        if (purchaseOrRent.toLowerCase(Locale.ROOT).equals("покупка")) {
            price1 = this.purchasePrice;
            price2 = book.purchasePrice;
            mes = "покупки";
        } else if (purchaseOrRent.toLowerCase(Locale.ROOT).equals("прокат")) {
            price1 = this.rentPrice;
            price2 = book.rentPrice;
            mes = "проката";
        } else {
            return "Неизвестная операция";
        }
        if (price1 < price2) {
            return comparatorMessage(this.title, book.title, "дешевле для " + mes, book, false);
        } else if (price1 > price2) {
            return comparatorMessage(this.title, book.title, "дороже для " + mes, book, false);
        } else {
            return comparatorMessage(this.title, book.title, "имеют одинаковую стоимость " + mes, book, true);
        }
    }

    public String compareDate(Book book) {
        if (this.publishingYear < book.publishingYear) {
            return comparatorMessage(this.title, book.title, "была опубликована раньше", book, false);
        } else if (this.rating > book.rating) {
            return comparatorMessage(this.title, book.title, "была опубликована позже", book, false);
        } else {
            return comparatorMessage(this.title, book.title, "вышли в один год", book, true);
        }
    }

    public String compareGenre(Book book) {
        if (this.genre.equals(book.genre)) {
            return comparatorMessage(this.title, book.title, "относятся к одному жанру", book, true);
        } else {
            return comparatorMessage(this.title, book.title, "не относятся к одному жанра", book, true);
        }
    }

    public String compareAuthor(Book book) {
        if (this.author.equals(book.author)) {
            return comparatorMessage(this.title, book.title, "- одного автора", book, true);
        } else {
            return comparatorMessage(this.title, book.title, "- разных авторов", book, true);
        }
    }


}
