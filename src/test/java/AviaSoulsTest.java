import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AviaSoulsTest {
    AviaSouls souls = new AviaSouls();

    Ticket ticket1 = new Ticket("Уфа", "Москва", 3_000, 20, 22);
    Ticket ticket2 = new Ticket("Москва", "Самара", 20_000, 12, 20);
    Ticket ticket3 = new Ticket("Самара", "Уфа", 3_000, 14, 14);
    Ticket ticket4 = new Ticket("Москва", "Самара", 35_000, 14, 18);
    Ticket ticket5 = new Ticket("Москва", "Самара", 15_000, 12, 22);

    @BeforeEach
    public void setup() {
        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);
        souls.add(ticket5);
    }

    @Test
    public void testCompareToTicketLess() {

        int expected = -1;
        int actual = ticket1.compareTo(ticket4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCompareToTicketMore() {

        int expected = 1;
        int actual = ticket4.compareTo(ticket2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCompareToTicketEqual() {

        int expected = 0;
        int actual = ticket1.compareTo(ticket3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSearchSortPrice() {

        Ticket[] actual = souls.search("Москва", "Самара");
        Ticket[] expected = {ticket5, ticket2, ticket4};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchDesiredTicket() {

        Ticket[] actual = souls.search("Самара", "Уфа");
        Ticket[] expected = {ticket3};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchNotFoundTicket() {

        Ticket[] actual = souls.search("Уфа", "Самара");
        Ticket[] expected = {};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchAndSortByComparator() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();

        Ticket[] actual = souls.searchAndSortBy("Москва", "Самара", timeComparator);
        Ticket[] expected = {ticket4, ticket2, ticket5};
        Assertions.assertArrayEquals(expected, actual);
    }
}