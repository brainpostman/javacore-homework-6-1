import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestBook {

    Author fherbert = new Author("Фрэнк Херберт");
    Author jrrtolkien = new Author("Джон Роналд Руэл Толкиен");

    Book dune = new Book(412, 16, 1965, 500.0, 100.0, 9.8, "Дюна", fherbert, "Научная фантастика");
    Book lotrfotr = new Book(423, 10, 1954, 700.0, 140.0, 9.5, "Властелин колец: Братство кольца", jrrtolkien, "Фэнтези");

    @Test
    public void testRateBook() {
        final double argument = 7.6;
        final double expected = 8.7;
        dune.rateBook(argument);
        Assertions.assertEquals(expected, dune.getRating());
    }

    @ParameterizedTest
    @ValueSource(ints = {30, 10})
    public void testBuyOrRent(int pagesPerHour) {
        int hoursPerDay = 5;
        String result = lotrfotr.buyOrRent(pagesPerHour, hoursPerDay);
        if (lotrfotr.getRentPrice() * lotrfotr.getPages() / pagesPerHour / hoursPerDay < lotrfotr.getPurchasePrice()) {
            Assertions.assertEquals("Советуем взять книгу \"Властелин колец: Братство кольца\" на прокат.\n", result);
        } else {
            Assertions.assertEquals("Советуем приобрести книгу \"Властелин колец: Братство кольца\".\n", result);
        }
    }
}

